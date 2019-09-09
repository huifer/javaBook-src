package com.huifer.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Title : CGLIBZhiLian </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class CGLIBZhiLian {

    public Object getInstance(Class<?> clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println("CGLIB 代理智联");
                Object o1 = methodProxy.invokeSuper(o, args);
                return o1;
            }
        });

        return enhancer.create();

    }
}
