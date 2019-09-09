package com.huifer.rmi.rpc.client;

import java.lang.reflect.Proxy;

/**
 * <p>Title : RpcClientProxy </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class RpcClientProxy {

    /**
     * Rpc客户端
     *
     * @param interfaceCls 具体接口 {@link com.huifer.rmi.rpc.HelloService}
     * @param host         host
     * @param port         port
     * @return HelloServer 代理对象
     */
    public <T> T clientProxy(final Class<T> interfaceCls,
                             final String host,
                             final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }

}
