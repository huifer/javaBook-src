package com.huifer.mybatis.jdbcProxy;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-24
 */
public class Main {
    public static void main(String[] args) throws Exception {

        SqlSession builder = SqlSessionFaction.builder(DeptMapper.class);
        Object obj = builder.select("select * from dept");


        System.out.println();
    }
}
