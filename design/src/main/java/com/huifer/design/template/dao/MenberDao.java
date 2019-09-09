package com.huifer.design.template.dao;

import com.huifer.design.template.JdbcTemplate;
import com.huifer.design.template.RowMapper;
import com.huifer.design.template.entity.Menber;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * <p>Title : MenberDao </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class MenberDao {

    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDatasource());

    public static void main(String[] args) throws Exception {

        MenberDao menberDao = new MenberDao();
        List<Object> query = menberDao.query();
        System.out.println();
    }

    private static DataSource getDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }


    public List<Object> query() throws Exception {
        String sql = "select * from t_menber";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Menber>() {
            @Override
            public Menber mapRow(ResultSet rs, int rowNum) throws Exception {
                Menber menber = new Menber();
                menber.setName(rs.getString("name"));
                menber.setPwd(rs.getString("pwd"));
                return menber;
            }
        }, new Object[]{});
    }

}
