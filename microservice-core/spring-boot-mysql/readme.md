# spring-boot 多源数据
> 案例目标：jpa和JdbcTemplate 操作多个数据库
- 配置文件
**其中的`spring.jpa.hibernate.ddl-auto=create`无效,数据库表需要手动创建**
```properties
spring.jpa.show-sql=true
##############
spring.datasource.one.url=jdbc:mysql://localhost:3306/java?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8
spring.datasource.one.username=root
spring.datasource.one.password=root
spring.datasource.one.driver-class-name=com.mysql.cj.jdbc.Driver
##############
spring.datasource.two.url=jdbc:mysql://localhost:3306/jpa?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8
spring.datasource.two.username=root
spring.datasource.two.password=root
spring.datasource.two.driver-class-name=com.mysql.cj.jdbc.Driver

```

- ` @Primary` 主要注解，在多个相同类型的bean中选择主要的，同类bean只能添加一个
假设以one为主数据库需要进行如下配置
```java
package com.huifer.springboot.mysql.conf;

import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>Title : DataSourceConfig </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@Configuration
@Slf4j
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.huifer.springboot.mysql.repo.one"}) 
@EnableTransactionManagement
public class OneDataSourceConfig {

    @Autowired
    @Qualifier("oneDataSource")
    public DataSource primaryDataSource;
    @Resource
    private Properties jpaProperties;

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    /**
     * 设置实体类所在位置
     */
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
            EntityManagerFactoryBuilder builder
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(primaryDataSource)
                .packages("com.huifer.springboot.mysql.pojo.one")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

    @Primary
    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource oneDataSource() {
        log.info("第一个数据源");
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "primaryDataSourceProperties")
    @Qualifier("primaryDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSourceProperties primaryDataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        return dataSourceProperties;
    }

    @Primary
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("oneDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

```

- 第二个数据源的配置如下
```java
package com.huifer.springboot.mysql.conf;

import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>Title : DataSourceConfig </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@Configuration
@Slf4j
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryTwo",
        transactionManagerRef = "transactionManagerTwo",
        basePackages = {"com.huifer.springboot.mysql.repo.two"})
@EnableTransactionManagement
public class TwoDataSourceConfig {

    @Autowired
    @Qualifier("twoDataSource")
    public DataSource twoDataSource;
    @Resource
    private Properties jpaProperties;

    @Bean(name = "entityManagerTwo")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTwo(builder).getObject().createEntityManager();
    }

    /**
     * 设置实体类所在位置
     */
    @Bean(name = "entityManagerFactoryTwo")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTwo(
            EntityManagerFactoryBuilder builder
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(twoDataSource)
                .packages("com.huifer.springboot.mysql.pojo.two")
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean(name = "transactionManagerTwo")
    public PlatformTransactionManager transactionManagerTwo(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTwo(builder).getObject());
    }

    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource twoDataSource() {
        log.info("第二个数据源");
        return twoDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "twoDataSourceProperties")
    @Qualifier("twoDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSourceProperties twoDataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        return dataSourceProperties;
    }



    @Bean(name = "twoJdbcTemplate")
    public JdbcTemplate twoJdbcTemplate(@Qualifier("twoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

```


