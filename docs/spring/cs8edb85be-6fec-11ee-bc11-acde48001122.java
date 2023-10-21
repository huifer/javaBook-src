package com.huifer.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Title : Jdk58 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class Jdk58 {

    private ZhiYuan target;


    public Object getInstance(ZhiYuan z) {
        this.target = z;

        Class clazz = null;
        clazz = z.getClass();
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        System.out.println("58同城为你服务");

                        Object invoke = method.invoke(target, args);

                        return invoke;
                    }

                });
        return o;

    }

}
