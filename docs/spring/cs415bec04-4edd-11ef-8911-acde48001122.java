package com.huifer.dubbo.server.provider;

import com.huifer.dubbo.server.api.DubboHello2;

/**
 * <p>Title : DubboHelloImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class DubboHelloImpl2 implements DubboHello2 {

    @Override
    public String hello(String msg) {
        return "DubboHello2 : " + msg;
    }

}
