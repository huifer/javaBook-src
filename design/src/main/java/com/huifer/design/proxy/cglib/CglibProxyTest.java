package com.huifer.design.proxy.cglib;

/**
 * <p>Title : CglibProxyTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class CglibProxyTest {

    public static void main(String[] args) throws Exception {


        CGPerson instance = (CGPerson) new CGLIBZhiLian().getInstance(CGPerson.class);
        instance.findWork();

    }

}
