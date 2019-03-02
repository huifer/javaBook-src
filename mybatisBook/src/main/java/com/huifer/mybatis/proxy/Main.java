package com.huifer.mybatis.proxy;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-24
 */
public class Main {
    public static void main(String[] args) throws Exception{

        // 代理对象创建
        BaseMothed wang = ProxyFactory.builder(Person.class);
        wang.eat();

    }
}
