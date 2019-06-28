package com.huifer.springboot.mysql.conf;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TwoDataSourceConfigTest {

    @Qualifier("twoJdbcTemplate")
    @Autowired
    private JdbcTemplate twoJdbcTemplate;


    @Test
    public void create() {
        twoJdbcTemplate.update("create table t_2(id int (10) , name varchar (100))");
    }

}
