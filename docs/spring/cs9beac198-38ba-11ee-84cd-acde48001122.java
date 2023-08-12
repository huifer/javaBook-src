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

import org.apache.ibatis.builder.*;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.InputStream;
import java.io.Reader;
import java.util.*;

/**
 * @author Clinton Begin
 * @author Kazuki Shimizu
 */
public class XMLMapperBuilder extends BaseBuilder {

    private final XPathParser parser;
    private final MapperBuilderAssistant builderAssistant;
    /**
     * sql 标签数据
     */
    private final Map<String, XNode> sqlFragments;
    private final String resource;

    @Deprecated
    public XMLMapperBuilder(Reader reader, Configuration configuration, String resource, Map<String, XNode> sqlFragments, String namespace) {
        this(reader, configuration, resource, sqlFragments);
        this.builderAssistant.setCurrentNamespace(namespace);
    }

    @Deprecated
    public XMLMapperBuilder(Reader reader, Configuration configuration, String resource, Map<String, XNode> sqlFragments) {
        this(new XPathParser(reader, true, configuration.getVariables(), new XMLMapperEntityResolver()),
                configuration, resource, sqlFragments);
    }

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource, Map<String, XNode> sqlFragments, String namespace) {
        this(inputStream, configuration, resource, sqlFragments);
        this.builderAssistant.setCurrentNamespace(namespace);
    }

    /**
     * @param inputStream   mapper.xml 文件流
     * @param configuration 全局的配置文件
     * @param resource      mapper.xml 文件路径/com/../..
     * @param sqlFragments  配置里面的内容
     */
    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource, Map<String, XNode> sqlFragments) {
        this(new XPathParser(inputStream, true, configuration.getVariables(), new XMLMapperEntityResolver()),
                configuration, resource, sqlFragments);
    }

    private XMLMapperBuilder(XPathParser parser, Configuration configuration, String resource, Map<String, XNode> sqlFragments) {
        super(configuration);
        this.builderAssistant = new MapperBuilderAssistant(configuration, resource);
        this.parser = parser;
        this.sqlFragments = sqlFragments;
        this.resource = resource;
    }

    public void parse() {
        if (!configuration.isResourceLoaded(resource)) {
            // 解析 mapper 标签下的内容
            configurationElement(parser.evalNode("/mapper"));
            //  <mappers>
            //<!--    <mapper resource="com/huifer/mybatis/mapper/PersonMapper.xml"/>-->
            //    <mapper resource="com/huifer/mybatis/mapper/HsSellMapper.xml"/>
            //  </mappers>
            // 将  resource 属性值添加
            configuration.addLoadedResource(resource);
            // mapper 和 namespace 绑定
            bindMapperForNamespace();
        }

        // // TODO: 2019/12/17  
        parsePendingResultMaps();
        parsePendingCacheRefs();
        parsePendingStatements();
    }

    public XNode getSqlFragment(String refid) {
        return sqlFragments.get(refid);
    }

    /**
     * 解析 mapper 标签
     * <mapper resource="com/huifer/mybatis/mapper/PersonMapper.xml"/>
     * <mapper namespace="com.huifer.mybatis.mapper.PersonMapper">
     * <p>
     * <cache-ref namespace="com.huifer.mybatis.mapper.PersonMapper"/>
     * <resultMap id="base" type="com.huifer.mybatis.entity.Person">
     * <id column="ID" jdbcType="VARCHAR" property="id"/>
     * <result column="age" jdbcType="INTEGER" property="age"/>
     * </resultMap>
     *
     * <parameterMap id="hc" type="com.huifer.mybatis.entity.PersonQuery">
     * <parameter property="name" resultMap="base"/>
     * </parameterMap>
     * <sql id="Base_List">
     * name,age,phone,email,address
     * </sql>
     * <insert id="insert" parameterType="Person" keyProperty="id"
     * useGeneratedKeys="true">
     * INSERT INTO person (name, age, phone, email, address)
     * VALUES(#{name},#{age},#{phone},#{email},#{address})
     * </insert>
     * </mapper>
     *
     * @param context
     */
    private void configurationElement(XNode context) {
        try {
            // 获取 namespace 属性
            String namespace = context.getStringAttribute("namespace");
            if (namespace == null || namespace.equals("")) {
                throw new BuilderException("Mapper's namespace cannot be empty");
            }
            // 直接设置 namespace 属性不做其他操作普通的 setter 方法
            builderAssistant.setCurrentNamespace(namespace);
            //   <cache-ref namespace="com.huifer.mybatis.mapper.PersonMapper"/>
            cacheRefElement(context.evalNode("cache-ref"));
//              <cache blocking="" eviction="" flushInterval="" readOnly="" size="" type=""/>
            cacheElement(context.evalNode("cache"));
            //   <parameterMap id="hc" type="com.huifer.mybatis.entity.PersonQuery">
            parameterMapElement(context.evalNodes("/mapper/parameterMap"));
            //   <resultMap id="base" type="com.huifer.mybatis.entity.Person">
            resultMapElements(context.evalNodes("/mapper/resultMap"));
            //   <sql id="Base_List">
            //name,age,phone,email,address
            //</sql>
            sqlElement(context.evalNodes("/mapper/sql"));
            // 解析 select|insert|update|delete 标签
            buildStatementFromContext(context.evalNodes("select|insert|update|delete"));
        } catch (Exception e) {
            throw new BuilderException("Error parsing Mapper XML. The XML location is '" + resource + "'. Cause: " + e, e);
        }
    }

    /**
     * select|insert|update|delete 标签
     *
     * @param list select|insert|update|delete 标签
     */
    private void buildStatementFromContext(List<XNode> list) {
        if (configuration.getDatabaseId() != null) {
            buildStatementFromContext(list, configuration.getDatabaseId());
        }
        buildStatementFromContext(list, null);
    }

    /**
     * <insert keyProperty="id" parameterType="Person" useGeneratedKeys="true" id="insert">
     * INSERT INTO person (name, age, phone, email, address)
     * VALUES(#{name},#{age},#{phone},#{email},#{address})
     * </insert>
     *
     * <select resultMap="base" id="list">
     * <include refid="Base_List"/>
     * <if test="iid != null">
     * and id = #{iid,jdbcType=INTEGER}
     * </if>
     * </select>
     *
     * @param list
     * @param requiredDatabaseId
     */
    private void buildStatementFromContext(List<XNode> list, String requiredDatabaseId) {
        for (XNode context : list) {
            // 动态 sql 解析
            final XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, builderAssistant, context, requiredDatabaseId);
            try {
                // 解析
                statementParser.parseStatementNode();
            } catch (IncompleteElementException e) {
                configuration.addIncompleteStatement(statementParser);
            }
        }
    }

    private void parsePendingResultMaps() {
        Collection<ResultMapResolver> incompleteResultMaps = configuration.getIncompleteResultMaps();
        synchronized (incompleteResultMaps) {
            Iterator<ResultMapResolver> iter = incompleteResultMaps.iterator();
            while (iter.hasNext()) {
                try {
                    iter.next().resolve();
                    iter.remove();
                } catch (IncompleteElementException e) {
                    // ResultMap is still missing a resource...
                }
            }
        }
    }

    private void parsePendingCacheRefs() {
        Collection<CacheRefResolver> incompleteCacheRefs = configuration.getIncompleteCacheRefs();
        synchronized (incompleteCacheRefs) {
            Iterator<CacheRefResolver> iter = incompleteCacheRefs.iterator();
            while (iter.hasNext()) {
                try {
                    iter.next().resolveCacheRef();
                    iter.remove();
                } catch (IncompleteElementException e) {
                    // Cache ref is still missing a resource...
                }
            }
        }
    }

    private void parsePendingStatements() {
        Collection<XMLStatementBuilder> incompleteStatements = configuration.getIncompleteStatements();
        synchronized (incompleteStatements) {
            Iterator<XMLStatementBuilder> iter = incompleteStatements.iterator();
            while (iter.hasNext()) {
                try {
                    iter.next().parseStatementNode();
                    iter.remove();
                } catch (IncompleteElementException e) {
                    // Statement is still missing a resource...
                }
            }
        }
    }

    /**
     * 解析 <cache-ref namespace=""/> 标签的属性
     *
     * @param context
     */
    private void cacheRefElement(XNode context) {
        if (context != null) {
            // 向全局配置设置 CacheRef
            configuration.addCacheRef(builderAssistant.getCurrentNamespace(), context.getStringAttribute("namespace"));
            // 构造
            CacheRefResolver cacheRefResolver = new CacheRefResolver(builderAssistant, context.getStringAttribute("namespace"));
            try {
//                解析
                Cache cache = cacheRefResolver.resolveCacheRef();
            } catch (IncompleteElementException e) {
                // 初始化是没有内容走这一步
                configuration.addIncompleteCacheRef(cacheRefResolver);
            }
        }
    }

    /**
     * 解析
     * <cache blocking="" eviction="" flushInterval="" readOnly="" size="" type=""/>
     * 具体缓存策略查看{@link org.apache.ibatis.cache.decorators} 下的类
     * 获取每个属性构造成 {@link  Cache}
     *
     * @param context
     */
    private void cacheElement(XNode context) {
        if (context != null) {
            // org.apache.ibatis.cache.impl.PerpetualCache 默认缓存实现
            String type = context.getStringAttribute("type", "PERPETUAL");
            // 添加到别名,或者从别名中获取
            Class<? extends Cache> typeClass = typeAliasRegistry.resolveAlias(type);
            // 緩存策略 LRU 最近最少使用的：移除最长时间不被使用的对象。
            String eviction = context.getStringAttribute("eviction", "LRU");
            Class<? extends Cache> evictionClass = typeAliasRegistry.resolveAlias(eviction);
            // 刷新时间 单位 毫秒
            Long flushInterval = context.getLongAttribute("flushInterval");
            // 引用数量
            Integer size = context.getIntAttribute("size");
            // 只读
            boolean readWrite = !context.getBooleanAttribute("readOnly", false);
            //
            boolean blocking = context.getBooleanAttribute("blocking", false);
            // <properties> 下级属性
            Properties props = context.getChildrenAsProperties();
            // 构造 cache
            builderAssistant.useNewCache(typeClass, evictionClass, flushInterval, size, readWrite, blocking, props);
        }
    }

    /**
     * 解析  parameterMap 标签的内容
     * <parameterMap id="hc" type="com.huifer.mybatis.entity.PersonQuery">
     * <parameter property="name" resultMap="base"/>
     * </parameterMap>
     *
     * @param list
     */
    private void parameterMapElement(List<XNode> list) {
        for (XNode parameterMapNode : list) {
            // 获取标签属性值
            String id = parameterMapNode.getStringAttribute("id");
            String type = parameterMapNode.getStringAttribute("type");
            // 到别名map中注册
            Class<?> parameterClass = resolveClass(type);
            // 获取下级标签 parameter 的属性值
            List<XNode> parameterNodes = parameterMapNode.evalNodes("parameter");
            List<ParameterMapping> parameterMappings = new ArrayList<>();
            for (XNode parameterNode : parameterNodes) {
                // 遍历下级节点获取属性
                String property = parameterNode.getStringAttribute("property");
                String javaType = parameterNode.getStringAttribute("javaType");
                String jdbcType = parameterNode.getStringAttribute("jdbcType");
                String resultMap = parameterNode.getStringAttribute("resultMap");
                String mode = parameterNode.getStringAttribute("mode");
                String typeHandler = parameterNode.getStringAttribute("typeHandler");
                Integer numericScale = parameterNode.getIntAttribute("numericScale");
                ParameterMode modeEnum = resolveParameterMode(mode);
                Class<?> javaTypeClass = resolveClass(javaType);
                JdbcType jdbcTypeEnum = resolveJdbcType(jdbcType);
                Class<? extends TypeHandler<?>> typeHandlerClass = resolveClass(typeHandler);
                ParameterMapping parameterMapping = builderAssistant.buildParameterMapping(parameterClass, property, javaTypeClass, jdbcTypeEnum, resultMap, modeEnum, typeHandlerClass, numericScale);
                parameterMappings.add(parameterMapping);
            }
            builderAssistant.addParameterMap(id, parameterClass, parameterMappings);
        }
    }

    /**
     * 解析 resultMap 标签
     *
     * @param list
     * @throws Exception
     */
    private void resultMapElements(List<XNode> list) throws Exception {
        for (XNode resultMapNode : list) {
            try {
                resultMapElement(resultMapNode);
            } catch (IncompleteElementException e) {
                // ignore, it will be retried
            }
        }
    }

    private ResultMap resultMapElement(XNode resultMapNode) throws Exception {
        return resultMapElement(resultMapNode, Collections.emptyList(), null);
    }

    /**
     * reslutMapper 下级标签解析
     * <resultMap type="com.huifer.mybatis.entity.Person" id="base">
     * <id jdbcType="VARCHAR" column="ID" property="id"/>
     * <result jdbcType="INTEGER" column="age" property="age"/>
     * </resultMap>
     *
     * @param resultMapNode            resultMap 节点
     * @param additionalResultMappings
     * @param enclosingType
     * @return
     * @throws Exception
     */
    private ResultMap resultMapElement(XNode resultMapNode, List<ResultMapping> additionalResultMappings, Class<?> enclosingType) throws Exception {
        ErrorContext.instance().activity("processing " + resultMapNode.getValueBasedIdentifier());
        String type = resultMapNode.getStringAttribute("type",
                resultMapNode.getStringAttribute("ofType",
                        resultMapNode.getStringAttribute("resultType",
                                resultMapNode.getStringAttribute("javaType"))));
        // 别名注册
        Class<?> typeClass = resolveClass(type);
        if (typeClass == null) {
            typeClass = inheritEnclosingType(resultMapNode, enclosingType);
        }
        Discriminator discriminator = null;
        List<ResultMapping> resultMappings = new ArrayList<>();
        // 粗暴
        resultMappings.addAll(additionalResultMappings);
        // 下级标签
        List<XNode> resultChildren = resultMapNode.getChildren();
        for (XNode resultChild : resultChildren) {
            if ("constructor".equals(resultChild.getName())) {
                processConstructorElement(resultChild, typeClass, resultMappings);
            } else if ("discriminator".equals(resultChild.getName())) {
                discriminator = processDiscriminatorElement(resultChild, typeClass, resultMappings);
            } else {
                List<ResultFlag> flags = new ArrayList<>();
                if ("id".equals(resultChild.getName())) {
                    // 获取 id 属性
                    flags.add(ResultFlag.ID);
                }
                resultMappings.add(buildResultMappingFromContext(resultChild, typeClass, flags));
            }
        }
        // 解析xml 中的resultMap标签
        // mapper_resultMap[base]_collection[name]
        String id = resultMapNode.getStringAttribute("id",
                resultMapNode.getValueBasedIdentifier());
        String extend = resultMapNode.getStringAttribute("extends");
        Boolean autoMapping = resultMapNode.getBooleanAttribute("autoMapping");
        ResultMapResolver resultMapResolver = new ResultMapResolver(builderAssistant, id, typeClass, extend, discriminator, resultMappings, autoMapping);
        try {
            return resultMapResolver.resolve();
        } catch (IncompleteElementException e) {
            configuration.addIncompleteResultMap(resultMapResolver);
            throw e;
        }
    }

    /**
     * @param resultMapNode
     * @param enclosingType
     * @return
     */
    protected Class<?> inheritEnclosingType(XNode resultMapNode, Class<?> enclosingType) {
        if ("association".equals(resultMapNode.getName()) && resultMapNode.getStringAttribute("resultMap") == null) {
            String property = resultMapNode.getStringAttribute("property");
            if (property != null && enclosingType != null) {
                MetaClass metaResultType = MetaClass.forClass(enclosingType, configuration.getReflectorFactory());
                return metaResultType.getSetterType(property);
            }
        } else if ("case".equals(resultMapNode.getName()) && resultMapNode.getStringAttribute("resultMap") == null) {
            return enclosingType;
        }
        return null;
    }

    /**
     * 解析 constructor 标签
     *
     * @param resultChild
     * @param resultType
     * @param resultMappings
     * @throws Exception
     */
    private void processConstructorElement(XNode resultChild, Class<?> resultType, List<ResultMapping> resultMappings) throws Exception {
        List<XNode> argChildren = resultChild.getChildren();
        for (XNode argChild : argChildren) {
            List<ResultFlag> flags = new ArrayList<>();
            flags.add(ResultFlag.CONSTRUCTOR);
            if ("idArg".equals(argChild.getName())) {
                flags.add(ResultFlag.ID);
            }
            resultMappings.add(buildResultMappingFromContext(argChild, resultType, flags));
        }
    }

    /**
     * <discriminator javaType="">
     * <case value=""></case>
     * </discriminator>
     *
     * @param context
     * @param resultType
     * @param resultMappings
     * @return
     * @throws Exception
     */
    private Discriminator processDiscriminatorElement(XNode context, Class<?> resultType, List<ResultMapping> resultMappings) throws Exception {
        // 简单的数据获取 赋值
        String column = context.getStringAttribute("column");
        String javaType = context.getStringAttribute("javaType");
        String jdbcType = context.getStringAttribute("jdbcType");
        String typeHandler = context.getStringAttribute("typeHandler");
        Class<?> javaTypeClass = resolveClass(javaType);
        Class<? extends TypeHandler<?>> typeHandlerClass = resolveClass(typeHandler);
        JdbcType jdbcTypeEnum = resolveJdbcType(jdbcType);
        Map<String, String> discriminatorMap = new HashMap<>();
        for (XNode caseChild : context.getChildren()) {
            String value = caseChild.getStringAttribute("value");
            String resultMap = caseChild.getStringAttribute("resultMap", processNestedResultMappings(caseChild, resultMappings, resultType));
            discriminatorMap.put(value, resultMap);
        }
        return builderAssistant.buildDiscriminator(resultType, column, javaTypeClass, jdbcTypeEnum, typeHandlerClass, discriminatorMap);
    }

    /**
     * 解析 sql 标签
     * <sql id="Base_List">
     * name,age,phone,email,address
     * </sql>
     *
     * @param list
     */
    private void sqlElement(List<XNode> list) {
        if (configuration.getDatabaseId() != null) {
            sqlElement(list, configuration.getDatabaseId());
        }
        sqlElement(list, null);
    }

    /**
     * 解析 sql 标签
     * <sql id="Base_List">
     * name,age,phone,email,address
     * </sql>
     *
     * @param list
     */
    private void sqlElement(List<XNode> list, String requiredDatabaseId) {
        for (XNode context : list) {
            String databaseId = context.getStringAttribute("databaseId");
            // 获取 id
            String id = context.getStringAttribute("id");
            id = builderAssistant.applyCurrentNamespace(id, false);
            if (databaseIdMatchesCurrent(id, databaseId, requiredDatabaseId)) {
                sqlFragments.put(id, context);
            }
        }
    }

    private boolean databaseIdMatchesCurrent(String id, String databaseId, String requiredDatabaseId) {
        if (requiredDatabaseId != null) {
            return requiredDatabaseId.equals(databaseId);
        }
        if (databaseId != null) {
            return false;
        }
        if (!this.sqlFragments.containsKey(id)) {
            return true;
        }
        // skip this fragment if there is a previous one with a not null databaseId
        XNode context = this.sqlFragments.get(id);
        return context.getStringAttribute("databaseId") == null;
    }

    /**
     * 创建一个 {@link  ResultMapping}
     * <resultMap id="base" type="com.huifer.mybatis.entity.Person">
     * <id column="ID" jdbcType="VARCHAR" property="id"/>
     * <result column="age" jdbcType="INTEGER" property="age"/>
     * <collection property="name" jdbcType="VARCHAR"/>
     * </resultMap>
     *
     * @param context
     * @param resultType
     * @param flags
     * @return
     * @throws Exception
     */
    private ResultMapping buildResultMappingFromContext(XNode context, Class<?> resultType, List<ResultFlag> flags) throws Exception {
        String property;
        if (flags.contains(ResultFlag.CONSTRUCTOR)) {
            property = context.getStringAttribute("name");
        } else {
            property = context.getStringAttribute("property");
        }
        // 获取标签属性
        String column = context.getStringAttribute("column");
        String javaType = context.getStringAttribute("javaType");
        String jdbcType = context.getStringAttribute("jdbcType");
        String nestedSelect = context.getStringAttribute("select");
        String nestedResultMap = context.getStringAttribute("resultMap",
                processNestedResultMappings(context, Collections.emptyList(), resultType));
        String notNullColumn = context.getStringAttribute("notNullColumn");
        String columnPrefix = context.getStringAttribute("columnPrefix");
        String typeHandler = context.getStringAttribute("typeHandler");
        String resultSet = context.getStringAttribute("resultSet");
        String foreignColumn = context.getStringAttribute("foreignColumn");
        boolean lazy = "lazy".equals(context.getStringAttribute("fetchType", configuration.isLazyLoadingEnabled() ? "lazy" : "eager"));
        Class<?> javaTypeClass = resolveClass(javaType);
        Class<? extends TypeHandler<?>> typeHandlerClass = resolveClass(typeHandler);
        JdbcType jdbcTypeEnum = resolveJdbcType(jdbcType);
        // 构造一个 resultMap
        return builderAssistant.buildResultMapping(resultType, property, column, javaTypeClass, jdbcTypeEnum, nestedSelect, nestedResultMap, notNullColumn, columnPrefix, typeHandlerClass, flags, resultSet, foreignColumn, lazy);
    }

    private String processNestedResultMappings(XNode context, List<ResultMapping> resultMappings, Class<?> enclosingType) throws Exception {
        if ("association".equals(context.getName())
                || "collection".equals(context.getName())
                || "case".equals(context.getName())) {
            if (context.getStringAttribute("select") == null) {
                validateCollection(context, enclosingType);
                ResultMap resultMap = resultMapElement(context, resultMappings, enclosingType);
                return resultMap.getId();
            }
        }
        return null;
    }

    protected void validateCollection(XNode context, Class<?> enclosingType) {
        if ("collection".equals(context.getName()) && context.getStringAttribute("resultMap") == null
                && context.getStringAttribute("javaType") == null) {
            MetaClass metaResultType = MetaClass.forClass(enclosingType, configuration.getReflectorFactory());
            String property = context.getStringAttribute("property");
            if (!metaResultType.hasSetter(property)) {
                throw new BuilderException(
                        "Ambiguous collection type for property '" + property + "'. You must specify 'javaType' or 'resultMap'.");
            }
        }
    }

    /**
     *  mapper 和 namespace 绑定
     */
    private void bindMapperForNamespace() {
        String namespace = builderAssistant.getCurrentNamespace();
        if (namespace != null) {
            Class<?> boundType = null;
            try {
                // 获取 mapper.xml 的namespace 属性值对应的字节码
                boundType = Resources.classForName(namespace);
            } catch (ClassNotFoundException e) {
                //ignore, bound type is not required
            }
            if (boundType != null) {
                if (!configuration.hasMapper(boundType)) {
                    // Spring may not know the real resource name so we set a flag
                    // to prevent loading again this resource from the mapper interface
                    // look at MapperAnnotationBuilder#loadXmlResource
                    // 添加操作
                    configuration.addLoadedResource("namespace:" + namespace);
                    configuration.addMapper(boundType);
                }
            }
        }
    }

}
