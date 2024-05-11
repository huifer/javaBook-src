package com.huifer.securityuserview.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DoLogAspect {


    @Pointcut("@annotation( com.huifer.securityuserview.aop.AspLog )")
    public void ddlog() {
    }

    @AfterReturning("ddlog()")
    public void save(JoinPoint joinPoint) {
        DoLog doLog = new DoLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 切点方法
        Method method = signature.getMethod();

        // 自定义注解的获取
        AspLog aspLog = method.getAnnotation(AspLog.class);
        String value;
        if (aspLog != null) {
            value = aspLog.value();
            doLog.setAspVal(value);
        }
        // 当前注解的class
        String doClassName = joinPoint.getTarget().getClass().getName();
        // 请求参数
        Object[] args = joinPoint.getArgs();
        // 请求函数名
        String methodName = method.getName();

        doLog.setDoClass(doClassName);
        doLog.setMethodName(methodName);
        doLog.setParams(args);


        System.out.println(doLog);

    }


}