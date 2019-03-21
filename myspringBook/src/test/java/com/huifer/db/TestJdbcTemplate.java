package com.huifer.db;

import com.huifer.bean.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_db_config.xml"})
public class TestJdbcTemplate {


    @Test
    public void testInsert(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dy_java?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("insert  into dept value (null,?,?) ", "oc", "afkj");
        System.out.println();
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate(){
        jdbcTemplate.update("insert  into dept value (null,?,?) ", "oc", "afkj");
        System.out.println();
    }

    @Test
    public void testJdbcTemplateSelect(){
        List<Dept> maps = jdbcTemplate.query("select * from dept", new RowMapperTest());
        System.out.println(maps);
    }



}

class RowMapperTest implements RowMapper {
    @Override
    public Dept mapRow(ResultSet resultSet, int i) throws SQLException {
        Dept d = new Dept();
        d.setId(resultSet.getInt("id"));
        d.setDname(resultSet.getString("dname"));
        d.setLoc(resultSet.getString("loc"));

        return d;
    }
}