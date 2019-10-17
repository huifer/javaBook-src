package com.huifer.restsec.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Slf4j
@Aspect
@Component
public class TimeAop {
    @Around("execution ( * com.huifer.restsec.controller.UserController.*(..) )")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("切片");
        Object[] args = joinPoint.getArgs();
        log.info("请求参数={}",args);
        long l = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long workTime = System.currentTimeMillis() - l;
        log.info("耗时={}", workTime);
        return proceed;
    }
}
