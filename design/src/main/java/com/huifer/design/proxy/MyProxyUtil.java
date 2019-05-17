package com.huifer.design.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
public class MyProxyUtil {

    public static void main(String[] args) {
//        getBaseServiceWithCgLib(new BaseServiceImpl());
        BaseService baseServiceWithJdk = getBaseServiceWithJdk(new BaseServiceImpl());
        baseServiceWithJdk.doSome();
    }

    /**
     * jdk 动态代理
     */
    public static BaseService getBaseServiceWithJdk(BaseService baseService) {

        Object o = Proxy.newProxyInstance(
                // 目标对象的加载器
                baseService.getClass().getClassLoader(),
                // 目标对象的所有接口
                baseService.getClass().getInterfaces(),
                // 代理对象执行处理器
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        // do outher
                        System.out.println("jdk代理");
                        // method.invoke(baseService, args) 一定不能忘记 ， 忘记了目标方法就不执行了
                        return method.invoke(baseService, args);
                    }
                });
        return (BaseService) o;
    }

    /**
     * CgLib 动态代理
     */
    public static BaseService getBaseServiceWithCgLib(BaseService baseService) {

        // 增强器创建
        Enhancer enhancer = new Enhancer();
        // 设置增强类对象
        enhancer.setSuperclass(baseService.getClass());
        // 类似回调方法
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * @param o           代理对象
             * @param method      代理对象的方法
             * @param objects     代理对象方法的参数
             * @param methodProxy 代理对象方法
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects,
                    MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib代理");
                return methodProxy.invokeSuper(o, objects);
            }
        });
        return (BaseService) enhancer.create();

    }

}
