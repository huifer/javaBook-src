package com.huifer.rmi.rpc.client;

import com.huifer.rmi.rpc.HelloService;

/**
 * <p>Title : ClientDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class ClientDemo {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        HelloService helloService = rpcClientProxy
                .clientProxy(HelloService.class, "localhost", 8888);
        String jkl = helloService.hello("hello-rpc");
        System.out.println(jkl);
    }

}
