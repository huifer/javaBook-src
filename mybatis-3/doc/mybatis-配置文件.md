# mybatis 配置文件
> 通常情况下使用`mybatis`的时候我们会添加一个`mybatis-config.xml`的文件. 这个文件是如何解析的呢?
## 解析
- 先编辑一个`mybatis-config.xml`文件放在`src\resources`目录下,具体内容如下
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <settings>
    <setting name="lazyLoadingEnabled" value="true"/>
  </settings>

  <typeAliases>
    <!--    <package name="com.huifer.mybatis.entity"/>-->
    <typeAlias type="com.huifer.mybatis.entity.Person" alias="Person"/>
  </typeAliases>

  <!-- 定义数据库的信息，默认使用development数据库构建环境 -->
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
      </dataSource>
    </environment>
  </environments>
  <!-- 定义映射器 -->
  <mappers>
    <mapper resource="com/huifer/mybatis/mapper/PersonMapper.xml"/>
  </mappers>
</configuration>

```
- 目前已知的条件: 这是一个配置文件,应该有一个对应的JAVA类来存储`org.apache.ibatis.session.Configuration`
```xml
  <settings>
    <setting name="lazyLoadingEnabled" value="true"/>
  </settings>
```
看上述的xml文件标签内容是否在`org.apache.ibatis.session.Configuration`存在,搜索`lazyLoadingEnabled`属性
```java
protected boolean lazyLoadingEnabled = false;
```
- 其他的一些属性应该也会有对应的比如查询超时`defaultStatementTimeout`等等.