package com.huifer.mybatis.jdbcProxy;

import java.sql.SQLException;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-24
 */
public interface SqlSession {
    public Object select(String sql) throws SQLException;
}
