package com.huifer.rmi.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Title : RemoteInvocationHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 代理对象invoke
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        MyTCPTransport myTCPTransport = new MyTCPTransport(this.host, this.port);

        return myTCPTransport.send(rpcRequest);
    }
}
