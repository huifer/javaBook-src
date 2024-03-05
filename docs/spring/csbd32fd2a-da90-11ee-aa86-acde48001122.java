package com.huifer.springboot.mysql.conf;

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

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

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
