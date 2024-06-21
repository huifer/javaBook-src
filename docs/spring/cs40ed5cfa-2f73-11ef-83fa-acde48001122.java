package com.huifer.mybatis.jdbcProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-24
 */
public class SqlSessionFaction {
    public static SqlSession builder(Class classFile) throws Exception {
        SqlSession sqlSession = (SqlSession) classFile.newInstance();
        InvocationHandler invocationHandler = new SqlInvaction(sqlSession);
        SqlSession proxy = (SqlSession) Proxy.newProxyInstance(sqlSession.getClass().getClassLoader(), sqlSession.getClass().getInterfaces(), invocationHandler);
        return proxy;
    }
}
