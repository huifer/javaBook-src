package com.huifer.springboot.mysql.conf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OneDataSourceConfigTest {
    @Qualifier("primaryJdbcTemplate")
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;


    @Test
    public void create() {
        primaryJdbcTemplate.update("create table t_1(id int (10) , name varchar (100))");
    }
}
