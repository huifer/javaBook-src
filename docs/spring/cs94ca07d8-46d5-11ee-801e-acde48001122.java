package com.huifer.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 描述:
 * 代理工厂
 *
 * @author huifer
 * @date 2019-02-24
 */
public class ProxyFactory {
    /**
     * 代理对象的数据类型由监听行为描述
     *
     * @return {@link BaseMothed}
     */
    public static BaseMothed builder(Class classFile) throws IllegalAccessException, InstantiationException {
        // 监听对象创建
        BaseMothed baseMothed = (BaseMothed) classFile.newInstance();
        // 通知对象
        InvocationHandler invocationHandler = new InformUtil(baseMothed);
        // 代理对象创建
        BaseMothed proxyObj = (BaseMothed) Proxy.newProxyInstance(baseMothed.getClass().getClassLoader(), baseMothed.getClass().getInterfaces(), invocationHandler);
        return proxyObj;
    }
}
