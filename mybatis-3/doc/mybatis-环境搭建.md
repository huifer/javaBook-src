# mybatis 源码环境搭建
## 获取
- 从[github-mybatis](https://github.com/mybatis/mybatis-3.git)下载源码
- 使用IDEA加载项目,IDEA 会自动的下载所需要的依赖.如果没有请执行`mvn install`
## 起步环境
### 引入mysql driver 和 junit
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</arti  factId>
  <version>5.1.48</version>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>test</scope>
</dependency>
```
### 日志配置
```properties
log4j.rootLogger=DEBUG,stdout
log4j.logger.org.mybatis=DUBUG
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.logDailyFile.layout.ConversionPattern = %5p %d %C:%m%n

```
### mybatis-config
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
    <package name="com.huifer.mybatis.entity"/>
  </typeAliases>

  <!-- 定义数据库的信息，默认使用development数据库构建环境 -->
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC" />
      <dataSource type="POOLED">
        <property name="driver" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis" />
        <property name="username" value="root" />
        <property name="password" value="root" />
      </dataSource>
    </environment>
  </environments>
  <!-- 定义映射器 -->
  <mappers>
    <mapper resource="com/huifer/mybatis/mapper/PersonMapper.xml"/>
  </mappers>
</configuration>
```
### 简单实体创建
```sql
CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`age`  int(11) NULL DEFAULT NULL ,
`phone`  varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`email`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`address`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
;


```
```java
package com.huifer.mybatis.entity;

public class Person {
    private int id;
    private String name;
    private int age;
    private String phone;
    private String email;
    private String address;

    
}

```
```java
package com.huifer.mybatis.mapper;

import com.huifer.mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {
    int insert(Person person);
}

```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.huifer.mybatis.mapper.PersonMapper">

  <insert id="insert" parameterType="Person" keyProperty="id"
          useGeneratedKeys="true">
        INSERT INTO person (name, age, phone, email, address)
        VALUES(#{name},#{age},#{phone},#{email},#{address})
    </insert>

</mapper>
```
```java
package com.huifer.mybatis;

import com.huifer.mybatis.entity.Person;
import com.huifer.mybatis.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = null;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            PersonMapper roleMapper = sqlSession.getMapper(PersonMapper.class);
            Person p = new Person();
            p.setName("zs");
            p.setAge(11);
            p.setPhone("123");
            p.setEmail("123@qq.com");
            p.setAddress("china");

            roleMapper.insert(p);


            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
```

- 执行`main`方法即可插入数据
```text
Logging initialized using 'class org.apache.ibatis.logging.slf4j.Slf4jImpl' adapter.
Class not found: org.jboss.vfs.VFS
JBoss 6 VFS API is not available in this environment.
Class not found: org.jboss.vfs.VirtualFile
VFS implementation org.apache.ibatis.io.JBoss6VFS is not valid in this environment.
Using VFS adapter org.apache.ibatis.io.DefaultVFS
Find JAR URL: file:/G:/omg/mybatis-3/target/classes/com/huifer/mybatis/entity
Not a JAR: file:/G:/omg/mybatis-3/target/classes/com/huifer/mybatis/entity
Reader entry: Person.class
Listing file:/G:/omg/mybatis-3/target/classes/com/huifer/mybatis/entity
Find JAR URL: file:/G:/omg/mybatis-3/target/classes/com/huifer/mybatis/entity/Person.class
Not a JAR: file:/G:/omg/mybatis-3/target/classes/com/huifer/mybatis/entity/Person.class
Reader entry: ����   4 4
Checking to see if class com.huifer.mybatis.entity.Person matches criteria [is assignable to Object]
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
Opening JDBC Connection
Thu Dec 05 08:52:07 CST 2019 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Created connection 853993923.
Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@32e6e9c3]
==>  Preparing: INSERT INTO person (name, age, phone, email, address) VALUES(?,?,?,?,?) 
==> Parameters: zs(String), 11(Integer), 123(String), 123@qq.com(String), china(String)
<==    Updates: 1
Committing JDBC Connection [com.mysql.jdbc.JDBC4Connection@32e6e9c3]
Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@32e6e9c3]
Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@32e6e9c3]
Returned connection 853993923 to pool.
```
环境搭建完成咯