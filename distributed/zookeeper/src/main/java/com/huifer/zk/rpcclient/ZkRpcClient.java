package com.huifer.zk.rpcclient;

import com.huifer.zk.zkfind.ZkServerDiscovery;
import java.lang.reflect.Proxy;

/**
 * <p>Title : ZkRpcClient </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkRpcClient {

    private ZkServerDiscovery discovery;

    public ZkRpcClient(ZkServerDiscovery discovery) {
        this.discovery = discovery;
    }

    public <T> T clientProxy(final Class<T> interfaceCls) {

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls}, new ZkInvocationHandler(discovery));

    }

}
