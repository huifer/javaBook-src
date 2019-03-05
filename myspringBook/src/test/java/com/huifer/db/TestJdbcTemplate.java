package com.huifer.db;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-05
 */
public class TestJdbcTemplate {

    @Test
    public void testSelect(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dy_java?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.query("select * from dept",);
        jdbcTemplate.update("insert  into dept value (null,?,?) ", "oc", "afkj");
        System.out.println();
    }


}
