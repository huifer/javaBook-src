package com.huifer.design.template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : JdbcTemplate </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Object> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;

    }

    public List<Object> executeQuery(String sql, RowMapper<?> rowMapper, Object[] value) {
        try {

            // 1.获取连接
            Connection conn = this.getConn();
            // 2.创建sql
            PreparedStatement pstm = this.createpstm(sql, conn);
            // 3.执行查询 获得结果
            ResultSet rst = this.executeQuery(pstm, value);
            // 4.解析结果
            List<Object> resList = this.parseResultSet(rst, rowMapper);

            // 5.关闭
            resultsetClose(rst);
            pstmClose(pstm);
            connClose(conn);
            return resList;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    private void connClose(Connection conn) throws SQLException {
        conn.close();
    }

    private void pstmClose(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    private void resultsetClose(ResultSet rst) throws SQLException {
        rst.close();
    }

    private ResultSet executeQuery(PreparedStatement pstm, Object[] value) throws SQLException {
        for (int i = 0; i < value.length; i++) {
            pstm.setObject(i, value[i]);
        }
        return pstm.executeQuery();
    }

    private PreparedStatement createpstm(String sql, Connection conn) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }


}
