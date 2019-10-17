package com.huifer.dubbo.server.provider;

import com.huifer.dubbo.server.api.DubboHello;

/**
 * <p>Title : DubboHelloImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class DubboHelloImpl implements DubboHello {

    @Override
    public String hello(String msg) {
        return "dubbo : " + msg;
    }

}
