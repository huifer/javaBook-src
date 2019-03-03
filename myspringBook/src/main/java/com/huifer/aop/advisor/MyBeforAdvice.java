package com.huifer.aop.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-02
 */
public class MyBeforAdvice implements MethodBeforeAdvice {
    /***
     * 切面
     * @param method 被监听的方法
     * @param args 方法参数
     * @param o 代理对象
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object o) throws Throwable {


        System.out.println("带纸巾！！！");



    }
}
