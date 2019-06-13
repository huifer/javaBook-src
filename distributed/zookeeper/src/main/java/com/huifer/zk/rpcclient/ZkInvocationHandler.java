package com.huifer.zk.rpcclient;

import com.huifer.zk.rpcserver.ZkRpcRequest;
import com.huifer.zk.zkfind.ZkServerDiscovery;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Title : ZkInvocationHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkInvocationHandler implements InvocationHandler {
    private ZkServerDiscovery discovery;

    public ZkInvocationHandler(ZkServerDiscovery discovery) {
        this.discovery = discovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ZkRpcRequest zkRpcRequest = new ZkRpcRequest();
        zkRpcRequest.setClassName(method.getDeclaringClass().getName());
        zkRpcRequest.setMethodName(method.getName());
        zkRpcRequest.setParameters(args);
        // 通过zk 获取相关内容
        String serviceAddress = discovery.discover(zkRpcRequest.getClassName());
        ZkTransport zkTransport = new ZkTransport(serviceAddress);
        Object send = zkTransport.send(zkRpcRequest);
        return send;
    }
}
