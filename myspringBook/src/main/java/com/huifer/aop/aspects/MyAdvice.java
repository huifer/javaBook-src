package com.huifer.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 描述:
 * 通知类
 *
 * @author huifer
 * @date 2019-03-03
 */
public class MyAdvice {

    public void log(){
        System.out.println("log");
    }


    /**
     * 环绕通知的使用: 事务管理的时候使用
     * @param joinPoint
     * @throws Throwable
     */
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知start \t 开启事务");
        // 提交事务
        Object proceed = joinPoint.proceed();
        System.out.println("环绕通知end \t 结束事务");
    }

    public void throwAdvice(){
        System.out.println("异常通知");
    }

}
