package com.huifer.design.proxy.staticproxy;

/**
 * <p>Title : StataicProxyTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class StataicProxyTest {

    public static void main(String[] args) {
        Person p = new Person();
        ZhiLian zhiLian = new ZhiLian(p);
        zhiLian.findWork();
    }

}
