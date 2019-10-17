package com.huifer.zk.inter;

import com.huifer.zk.rpcserver.RpcAnnotation;

/**
 * <p>Title : HelloInInterfaceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
@RpcAnnotation(HelloInInterface.class)
public class HelloInInterfaceImpl2 implements HelloInInterface {

    @Override
    public String hello(String msg) {
        return "Hello2: " + msg;
    }
}
