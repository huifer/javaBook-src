package com.huifer.mybatis.proxy;

/**
 * 描述:
 * 主要业务的实现
 *
 * @author huifer
 * @date 2019-02-24
 */
public class Person implements BaseMothed {
    @Override
    public void eat() {
        System.out.println("吃东西了");
    }

    @Override
    public void play() {
        System.out.println("开始玩了");
    }
}
