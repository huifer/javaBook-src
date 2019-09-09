package com.huifer.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Title : ZhiLianJdk </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class ZhiLianJdk {

    /**
     * 被代理对象的临时保存结点
     */
    private ZhiYuan target;

    public Object getInstance(ZhiYuan personJdk) {
        this.target = personJdk;
        Class clazz;

        clazz = personJdk.getClass();

        // 重构一个新的对象
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        System.out.println("jdk 代理的智联");

                        Object invoke = method.invoke(target, args);
                        System.out.println("工作找到了");
                        return invoke;
                    }
                });

    }
}
