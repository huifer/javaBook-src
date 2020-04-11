# Spring ContextLoader
- Author: [HuiFer](https://github.com/huifer)
- 源码阅读仓库: [huifer-spring](https://github.com/huifer/spring-framework-read)
- 路径: `org.springframework.web.context.ContextLoader`
- 测试类: `org.springframework.web.context.ContextLoaderTests`

## 静态代码块
```java
    static {
        // Load default strategy implementations from properties file.
        // This is currently strictly internal and not meant to be customized
        // by application developers.
        try {
            /**
             * 加载 ContextLoader.properties 文件
             * {@code org.springframework.web.context.WebApplicationContext=org.springframework.web.context.support.XmlWebApplicationContext}
             */
            ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH, ContextLoader.class);
            defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Could not load 'ContextLoader.properties': " + ex.getMessage());
        }
    }

```
- 读取文件`ContextLoader.properties`中的内容
```properties
org.springframework.web.context.WebApplicationContext=org.springframework.web.context.support.XmlWebApplicationContext
```
- 后续会使用`defaultStrategies`获取