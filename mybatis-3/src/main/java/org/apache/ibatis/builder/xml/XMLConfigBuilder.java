/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.builder.xml;

import org.apache.ibatis.builder.BaseBuilder;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * @author Clinton Begin
 * @author Kazuki Shimizu
 */
public class XMLConfigBuilder extends BaseBuilder {

    private final XPathParser parser;
    private final ReflectorFactory localReflectorFactory = new DefaultReflectorFactory();
    private boolean parsed;
    private String environment;

    public XMLConfigBuilder(Reader reader) {
        this(reader, null, null);
    }

    public XMLConfigBuilder(Reader reader, String environment) {
        this(reader, environment, null);
    }

    public XMLConfigBuilder(Reader reader, String environment, Properties props) {
        this(new XPathParser(reader, true, props, new XMLMapperEntityResolver()), environment, props);
    }

    public XMLConfigBuilder(InputStream inputStream) {
        this(inputStream, null, null);
    }

    public XMLConfigBuilder(InputStream inputStream, String environment) {
        this(inputStream, environment, null);
    }

    public XMLConfigBuilder(InputStream inputStream, String environment, Properties props) {
        this(new XPathParser(inputStream, true, props, new XMLMapperEntityResolver()), environment, props);
    }

    private XMLConfigBuilder(XPathParser parser, String environment, Properties props) {
        super(new Configuration());
        ErrorContext.instance().resource("SQL Mapper Configuration");
        this.configuration.setVariables(props);
        this.parsed = false;
        this.environment = environment;
        this.parser = parser;
    }

    public Configuration parse() {
        if (parsed) {
            throw new BuilderException("Each XMLConfigBuilder can only be used once.");
        }
        parsed = true;
        // 加载<configuration> 标签 , Xnode.evalNode("....") 方法的目的就是加载指定标签
        parseConfiguration(parser.evalNode("/configuration"));
        return configuration;
    }

    private void parseConfiguration(XNode root) {
        try {
            //issue #117 read properties first
            // 加载 properties 标签
            propertiesElement(root.evalNode("properties"));
            // 加载 setting 标签
            Properties settings = settingsAsProperties(root.evalNode("settings"));
            // vfs 虚拟文件相关属性
            loadCustomVfs(settings);
            // 日志实现类
            loadCustomLogImpl(settings);
            // 加载 typeAliases 标签
            typeAliasesElement(root.evalNode("typeAliases"));
            // 加载 plugins 标签
            pluginElement(root.evalNode("plugins"));
            // 加载 objectFactory 标签
            objectFactoryElement(root.evalNode("objectFactory"));
            // 加载 objectWrapperFactory 标签
            objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
            // 加载 reflectorFactory 标签
            reflectorFactoryElement(root.evalNode("reflectorFactory"));
            // 设置 settings 标签的属性值放入 configuration 中
            // 不明白为什么放在下面,应该放在 loadCustomLogImpl 方法之后. 可读性会更好
            settingsElement(settings);
            // read it after objectFactory and objectWrapperFactory issue #631
            environmentsElement(root.evalNode("environments"));
            databaseIdProviderElement(root.evalNode("databaseIdProvider"));
            typeHandlerElement(root.evalNode("typeHandlers"));
            mapperElement(root.evalNode("mappers"));
        } catch (Exception e) {
            throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
    }

    /**
     * 加载 setting 标签
     *
     * <settings>
     * <setting name="cacheEnabled" value="true"/>
     * </settings>
     *
     * @param context
     * @return
     */
    private Properties settingsAsProperties(XNode context) {
        if (context == null) {
            // 返回一个空的 Properties
            return new Properties();
        }
        Properties props = context.getChildrenAsProperties();
        // Check that all settings are known to the configuration class
        MetaClass metaConfig = MetaClass.forClass(Configuration.class, localReflectorFactory);
        for (Object key : props.keySet()) {
            if (!metaConfig.hasSetter(String.valueOf(key))) {
                throw new BuilderException("The setting " + key + " is not known.  Make sure you spelled it correctly (case sensitive).");
            }
        }
        return props;
    }

    /**
     * vfs 的子类
     *
     * @param props
     * @throws ClassNotFoundException
     */
    private void loadCustomVfs(Properties props) throws ClassNotFoundException {
        String value = props.getProperty("vfsImpl");
        if (value != null) {
            String[] clazzes = value.split(",");
            for (String clazz : clazzes) {
                if (!clazz.isEmpty()) {
                    @SuppressWarnings("unchecked")
                    Class<? extends VFS> vfsImpl = (Class<? extends VFS>) Resources.classForName(clazz);
                    configuration.setVfsImpl(vfsImpl);
                }
            }
        }
    }

    /**
     * 日志实现
     * <setting name="logImpl" value="LOG4J"/>
     *
     * @param props
     */
    private void loadCustomLogImpl(Properties props) {
        Class<? extends Log> logImpl = resolveClass(props.getProperty("logImpl"));
        configuration.setLogImpl(logImpl);
    }

    /**
     * 别名加载
     * <typeAliases>
     * <!--    <package name="com.huifer.mybatis.entity"/>-->
     * <typeAlias type="com.huifer.mybatis.entity.Person" alias="Person"/>
     * </typeAliases>
     *
     * @param parent
     */
    private void typeAliasesElement(XNode parent) {
        if (parent != null) {
            for (XNode child : parent.getChildren()) {
                if ("package".equals(child.getName())) {
                    // 解析 package 标签
                    String typeAliasPackage = child.getStringAttribute("name");
                    configuration.getTypeAliasRegistry().registerAliases(typeAliasPackage);
                } else {
                    // 解析 typeAliases 标签
                    String alias = child.getStringAttribute("alias");
                    String type = child.getStringAttribute("type");
                    try {
                        // 将 type 中的全类名解析成字节码
                        Class<?> clazz = Resources.classForName(type);
                        // 别名注册
                        if (alias == null) {
                            typeAliasRegistry.registerAlias(clazz);
                        } else {
                            // 向 typeAliases put(别名名称,字节码)
                            typeAliasRegistry.registerAlias(alias, clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new BuilderException("Error registering typeAlias for '" + alias + "'. Cause: " + e, e);
                    }
                }
            }
        }
    }

    /**
     * 记载 plugins 标签内容
     * <plugins>
     * <plugin interceptor="com.huifer.mybatis.plugins.TestPlugin">
     * <property name="testPlugins" value="tPl"/>
     * </plugin>
     * </plugins>
     *
     * @param parent
     * @throws Exception
     */
    private void pluginElement(XNode parent) throws Exception {
        if (parent != null) {
            for (XNode child : parent.getChildren()) {
                //     <plugin interceptor="com.huifer.mybatis.plugins.TestPlugin"> 获取 interceptor 值
                String interceptor = child.getStringAttribute("interceptor");
                // 获取plugin 下面的 property 标签数据
                Properties properties = child.getChildrenAsProperties();
                Interceptor interceptorInstance = (Interceptor) resolveClass(interceptor).getDeclaredConstructor().newInstance();
                interceptorInstance.setProperties(properties);
                configuration.addInterceptor(interceptorInstance);
            }
        }
    }

    /**
     * 解析 objectFactory 标签
     * <objectFactory type="com.huifer.mybatis.factory.TestObjectFactory">
     * <property name="data" value="100"/>
     * </objectFactory>
     *
     * @param context
     * @throws Exception
     */
    private void objectFactoryElement(XNode context) throws Exception {
        if (context != null) {
            // 获取标签 objectFactory 中的 type 属性
            String type = context.getStringAttribute("type");
            Properties properties = context.getChildrenAsProperties();
            // 去别名 MAP 中获取实例
            ObjectFactory factory = (ObjectFactory) resolveClass(type).getDeclaredConstructor().newInstance();
            // 设置属性值
            factory.setProperties(properties);
            // 在 configuration 中放入该工厂
            configuration.setObjectFactory(factory);
        }
    }

    /**
     * 解析 objectWrapperFactory 标签
     * <objectWrapperFactory type="org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory"/>
     *
     * @param context
     * @throws Exception
     */
    private void objectWrapperFactoryElement(XNode context) throws Exception {
        if (context != null) {
            // 获取 objectWrapperFactory 标签 type 的值
            String type = context.getStringAttribute("type");
            // 别名 mao 中获取
            ObjectWrapperFactory factory = (ObjectWrapperFactory) resolveClass(type).getDeclaredConstructor().newInstance();
            // 在 configuration 设置
            configuration.setObjectWrapperFactory(factory);
        }
    }

    /**
     * 解析 reflectorFactory 标签
     * <reflectorFactory type="org.apache.ibatis.reflection.DefaultReflectorFactory"/>
     *
     * @param context
     * @throws Exception
     */
    private void reflectorFactoryElement(XNode context) throws Exception {
        if (context != null) {
            // 获取 reflectorFactory 标签 type 的值
            String type = context.getStringAttribute("type");
            // 别名 mao 中获取
            ReflectorFactory factory = (ReflectorFactory) resolveClass(type).getDeclaredConstructor().newInstance();
            // 在 configuration 设置
            configuration.setReflectorFactory(factory);
        }
    }

    /**
     * 加载 properties 标签内容
     * <properties resource="test.properties">
     * <property name="hello" value="world"/>
     * <property name="k" value="v"/>
     * </properties>
     *
     * @param context
     * @throws Exception
     */
    private void propertiesElement(XNode context) throws Exception {
        if (context != null) {
            // 加载下级标签,解析属性
            Properties defaults = context.getChildrenAsProperties();
            String resource = context.getStringAttribute("resource");
            String url = context.getStringAttribute("url");
            if (resource != null && url != null) {
                // 不会同时设置 resource 和 url 的属性值
                throw new BuilderException("The properties element cannot specify both a URL and a resource based property file reference.  Please specify one or the other.");
            }
            if (resource != null) {
                // 覆盖子节点属性
                defaults.putAll(Resources.getResourceAsProperties(resource));
            } else if (url != null) {
                // 覆盖子节点属性
                defaults.putAll(Resources.getUrlAsProperties(url));
            }
            Properties vars = configuration.getVariables();
            if (vars != null) {
                defaults.putAll(vars);
            }
            parser.setVariables(defaults);
            // 设置到 全局的 configuration 中
            configuration.setVariables(defaults);
        }
    }

    /**
     * 该方法将settings标签内的数据放到 {@link Configuration}
     *
     * @param props settings 标签的内容
     */
    private void settingsElement(Properties props) {
        configuration.setAutoMappingBehavior(AutoMappingBehavior.valueOf(props.getProperty("autoMappingBehavior", "PARTIAL")));
        configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.valueOf(props.getProperty("autoMappingUnknownColumnBehavior", "NONE")));
        configuration.setCacheEnabled(booleanValueOf(props.getProperty("cacheEnabled"), true));
        configuration.setProxyFactory((ProxyFactory) createInstance(props.getProperty("proxyFactory")));
        configuration.setLazyLoadingEnabled(booleanValueOf(props.getProperty("lazyLoadingEnabled"), false));
        configuration.setAggressiveLazyLoading(booleanValueOf(props.getProperty("aggressiveLazyLoading"), false));
        configuration.setMultipleResultSetsEnabled(booleanValueOf(props.getProperty("multipleResultSetsEnabled"), true));
        configuration.setUseColumnLabel(booleanValueOf(props.getProperty("useColumnLabel"), true));
        configuration.setUseGeneratedKeys(booleanValueOf(props.getProperty("useGeneratedKeys"), false));
        configuration.setDefaultExecutorType(ExecutorType.valueOf(props.getProperty("defaultExecutorType", "SIMPLE")));
        configuration.setDefaultStatementTimeout(integerValueOf(props.getProperty("defaultStatementTimeout"), null));
        configuration.setDefaultFetchSize(integerValueOf(props.getProperty("defaultFetchSize"), null));
        configuration.setDefaultResultSetType(resolveResultSetType(props.getProperty("defaultResultSetType")));
        configuration.setMapUnderscoreToCamelCase(booleanValueOf(props.getProperty("mapUnderscoreToCamelCase"), false));
        configuration.setSafeRowBoundsEnabled(booleanValueOf(props.getProperty("safeRowBoundsEnabled"), false));
        configuration.setLocalCacheScope(LocalCacheScope.valueOf(props.getProperty("localCacheScope", "SESSION")));
        configuration.setJdbcTypeForNull(JdbcType.valueOf(props.getProperty("jdbcTypeForNull", "OTHER")));
        configuration.setLazyLoadTriggerMethods(stringSetValueOf(props.getProperty("lazyLoadTriggerMethods"), "equals,clone,hashCode,toString"));
        configuration.setSafeResultHandlerEnabled(booleanValueOf(props.getProperty("safeResultHandlerEnabled"), true));
        configuration.setDefaultScriptingLanguage(resolveClass(props.getProperty("defaultScriptingLanguage")));
        configuration.setDefaultEnumTypeHandler(resolveClass(props.getProperty("defaultEnumTypeHandler")));
        configuration.setCallSettersOnNulls(booleanValueOf(props.getProperty("callSettersOnNulls"), false));
        configuration.setUseActualParamName(booleanValueOf(props.getProperty("useActualParamName"), true));
        configuration.setReturnInstanceForEmptyRow(booleanValueOf(props.getProperty("returnInstanceForEmptyRow"), false));
        configuration.setLogPrefix(props.getProperty("logPrefix"));
        configuration.setConfigurationFactory(resolveClass(props.getProperty("configurationFactory")));
    }

    /**
     * 解析 environments 标签
     * <environments default="development">
     * <environment id="development">
     * <transactionManager type="JDBC"/>
     * <dataSource type="POOLED">
     * <property name="driver" value="com.mysql.jdbc.Driver"/>
     * <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
     * <property name="username" value="root"/>
     * <property name="password" value="root"/>
     * </dataSource>
     * </environment>
     * </environments>
     *
     * @param context
     * @throws Exception
     */
    private void environmentsElement(XNode context) throws Exception {
        if (context != null) {
            if (environment == null) {
                // environment 初始化的是就是空
                environment = context.getStringAttribute("default");
            }
            for (XNode child : context.getChildren()) {
                // 获取 environment 的 id
                String id = child.getStringAttribute("id");
                if (isSpecifiedEnvironment(id)) {
                    // 解析 transactionManager
                    // JdbcTransaction
                    TransactionFactory txFactory = transactionManagerElement(child.evalNode("transactionManager"));
                    // 解析 dataSource
                    DataSourceFactory dsFactory = dataSourceElement(child.evalNode("dataSource"));
                    // 获取数据库
                    DataSource dataSource = dsFactory.getDataSource();
                    // 构建
                    Environment.Builder environmentBuilder = new Environment.Builder(id)
                            .transactionFactory(txFactory)
                            .dataSource(dataSource);
                    // 在 configuration 设置
                    configuration.setEnvironment(environmentBuilder.build());
                }
            }
        }
    }

    /**
     * 解析 databaseIdProvider 标签
     * <databaseIdProvider type="DB_VENDOR">
     * <property name="Oracle" value="oracle"/>
     * <property name="MySQL" value="mysql"/>
     * <property name="DB2" value="d2"/>
     * </databaseIdProvider>
     *
     * @param context
     * @throws Exception
     */
    private void databaseIdProviderElement(XNode context) throws Exception {
        DatabaseIdProvider databaseIdProvider = null;
        if (context != null) {
            String type = context.getStringAttribute("type");
            // awful patch to keep backward compatibility
            if ("VENDOR".equals(type)) {
                type = "DB_VENDOR";
            }
            // 向 VendorDatabaseIdProvider 放入解析标签后的结果
            Properties properties = context.getChildrenAsProperties();
            databaseIdProvider = (DatabaseIdProvider) resolveClass(type).getDeclaredConstructor().newInstance();
            databaseIdProvider.setProperties(properties);
        }
        Environment environment = configuration.getEnvironment();
        if (environment != null && databaseIdProvider != null) {
            // 从 configuration 获取 environment 标签的内容 在通过
            String databaseId = databaseIdProvider.getDatabaseId(environment.getDataSource());
            configuration.setDatabaseId(databaseId);
        }
    }

    /**
     * 解析 transactionManager 标签
     * <transactionManager type="JDBC"/>
     *
     * @param context
     * @return
     * @throws Exception
     */
    private TransactionFactory transactionManagerElement(XNode context) throws Exception {
        if (context != null) {
            // 获取 type 属性
            String type = context.getStringAttribute("type");
            Properties props = context.getChildrenAsProperties();
            TransactionFactory factory = (TransactionFactory) resolveClass(type).getDeclaredConstructor().newInstance();
            factory.setProperties(props);
            return factory;
        }
        throw new BuilderException("Environment declaration requires a TransactionFactory.");
    }

    /**
     * 解析 dataSourceElement 标签
     * <dataSource type="POOLED">
     * <property name="driver" value="com.mysql.jdbc.Driver"/>
     * <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
     * <property name="username" value="root"/>
     * <property name="password" value="root"/>
     * </dataSource>
     *
     * @param context
     * @return
     * @throws Exception
     */
    private DataSourceFactory dataSourceElement(XNode context) throws Exception {
        if (context != null) {
            String type = context.getStringAttribute("type");
            Properties props = context.getChildrenAsProperties();
            //org.apache.ibatis.session.Configuration.Configuration()
            DataSourceFactory factory = (DataSourceFactory) resolveClass(type).getDeclaredConstructor().newInstance();

            // PooledDataSourceFactory -> UnpooledDataSourceFactory
            factory.setProperties(props);
            return factory;
        }
        throw new BuilderException("Environment declaration requires a DataSourceFactory.");
    }

    /**
     * 解析 typeHandler
     * 最终调用方法：{@link TypeHandlerRegistry#register(java.lang.reflect.Type, org.apache.ibatis.type.JdbcType, org.apache.ibatis.type.TypeHandler)}
     *
     *
     * <typeHandlers>
     * <!--      <package name="com.huifer.mybatis.handler"/>-->
     * <typeHandler jdbcType="VARCHAR" javaType="String" handler="com.huifer.mybatis.handler.TestTypeHandler"/>
     * </typeHandlers>
     *
     * @param parent
     */
    private void typeHandlerElement(XNode parent) {
        if (parent != null) {
            for (XNode child : parent.getChildren()) {
                if ("package".equals(child.getName())) {
                    // 获取 package 的 name 属性
                    String typeHandlerPackage = child.getStringAttribute("name");
                    // 注册
                    typeHandlerRegistry.register(typeHandlerPackage);
                } else {
                    // 获取 javaType 属性值
                    String javaTypeName = child.getStringAttribute("javaType");
                    // 获取 jdbcType 属性值
                    String jdbcTypeName = child.getStringAttribute("jdbcType");
                    // 获取 handler 属性值
                    String handlerTypeName = child.getStringAttribute("handler");
                    // org.apache.ibatis.type.TypeAliasRegistry.TypeAliasRegistry 构造方法中有默认的
                    // 从别名中获取
                    Class<?> javaTypeClass = resolveClass(javaTypeName);
                    JdbcType jdbcType = resolveJdbcType(jdbcTypeName);
                    Class<?> typeHandlerClass = resolveClass(handlerTypeName);
                    if (javaTypeClass != null) {
                        if (jdbcType == null) {
                            typeHandlerRegistry.register(javaTypeClass, typeHandlerClass);
                        } else {
                            typeHandlerRegistry.register(javaTypeClass, jdbcType, typeHandlerClass);
                        }
                    } else {
                        typeHandlerRegistry.register(typeHandlerClass);
                    }
                }
            }
        }
    }

    /**
     * 解析 mappers
     *
     * @param parent
     * @throws Exception
     */
    private void mapperElement(XNode parent) throws Exception {
        if (parent != null) {
            for (XNode child : parent.getChildren()) {
                if ("package".equals(child.getName())) {
                    String mapperPackage = child.getStringAttribute("name");
                    configuration.addMappers(mapperPackage);
                } else {
                    // 读取 resource 属性
                    String resource = child.getStringAttribute("resource");
                    // 读取 url 属性
                    String url = child.getStringAttribute("url");
                    // 读取 class 属性
                    String mapperClass = child.getStringAttribute("class");
                    if (resource != null && url == null && mapperClass == null) {
                        // 加载 resource 内容
                        ErrorContext.instance().resource(resource);
                        InputStream inputStream = Resources.getResourceAsStream(resource);
                        // 解析 mapper.xml 文件
                        // 构造在解析
                        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
                        mapperParser.parse();
                    } else if (resource == null && url != null && mapperClass == null) {
                        ErrorContext.instance().resource(url);
                        InputStream inputStream = Resources.getUrlAsStream(url);
                        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
                        mapperParser.parse();
                    } else if (resource == null && url == null && mapperClass != null) {
                        Class<?> mapperInterface = Resources.classForName(mapperClass);
                        configuration.addMapper(mapperInterface);
                    } else {
                        throw new BuilderException("A mapper element may only specify a url, resource or class, but not more than one.");
                    }
                }
            }
        }
    }

    /**
     * 判断 environment 标签的id 和  &lt environments default="development" &gt default 值是否相同
     *
     * @param id environment 的id属性
     * @return
     */
    private boolean isSpecifiedEnvironment(String id) {
        if (environment == null) {
            throw new BuilderException("No environment specified.");
        } else if (id == null) {
            throw new BuilderException("Environment requires an id attribute.");
        } else return environment.equals(id);
    }

}
