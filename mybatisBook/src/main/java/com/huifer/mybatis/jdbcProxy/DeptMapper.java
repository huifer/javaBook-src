package com.huifer.mybatis.jdbcProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-24
 */
public class DeptMapper implements SqlSession {
    PreparedStatement preparedStatement;

    @Override
    public Object select(String sql) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        return resultSet;
    }
}
