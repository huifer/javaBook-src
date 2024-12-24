package com.huifer.util;

import com.huifer.proxy.impl.BaseServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-02
 */
public class MyBeanPostPro implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化前");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        System.out.println("初始化后");

        Class<?> aClass = bean.getClass();
        if (aClass == BaseServiceImpl.class) {
            Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            // 具体操作内容
                            Object invoke = null;
                            if (method.getName().equals("doSome")) {
                                invoke = (String) method.invoke(bean, args);
                                return ((String) invoke).toUpperCase();
                            } else {
                                return invoke;
                            }

                        }
                    }
            );
            return proxyInstance;
        }
        return bean;
    }
}
