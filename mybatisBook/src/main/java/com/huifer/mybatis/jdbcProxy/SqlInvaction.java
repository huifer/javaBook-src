package com.huifer.mybatis.jdbcProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-24
 */
public class SqlInvaction implements InvocationHandler {

    private SqlSession obj;
    Connection connection;
    PreparedStatement preparedStatement;

    public SqlInvaction(SqlSession obj) {
        this.obj = obj;
    }

    @Override
    public ResultSet invoke(Object proxy, Method method, Object[] args) throws Throwable {
        init();
        Field ps = obj.getClass().getDeclaredField("preparedStatement");
        ps.set(obj, preparedStatement);
        ResultSet rs = (ResultSet) method.invoke(obj, args);

        while (rs.next()) {
            int id = rs.getInt("id");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");

            System.out.println(id + "\t" + dname + "\t" + loc);
        }

        end();

        return rs;
    }


    private void init() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dy_java?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8", "root", "root");
        this.preparedStatement = connection.prepareStatement(" ");
    }

    private void end() throws Exception {
        if (this.preparedStatement != null) {
            this.preparedStatement.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
    }

}
