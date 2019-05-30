package com.huifer.hystrix.aop;

import com.huifer.hystrix.annotation.Fusing;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <p>Title : MyHystrixControllerAspect </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@Aspect
@Component
public class MyHystrixControllerAspect {

    private final ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    @Around(" execution(*  com.huifer.hystrix.controller.MyHystrixController.v3(..)  )  && args(msg)")
    public Object timeout(ProceedingJoinPoint joinPoint, String msg) throws Throwable {
        String s = doInvoke(joinPoint, msg, 100, msg);
        return s;

    }


    @Around("execution(*  com.huifer.hystrix.controller.MyHystrixController.v4(..)  ) && args(msg) && @annotation( fusing )")
    public Object versionFour(ProceedingJoinPoint joinPoint, String msg, Fusing fusing)
            throws Throwable {
        int timeout = fusing.timeout();
        String s = doInvoke(joinPoint, msg, timeout, "注解形式 " + msg);
        return s;

    }

    private String doInvoke(ProceedingJoinPoint joinPoint, String msg, int timeout, String s2)
            throws InterruptedException, java.util.concurrent.ExecutionException {
        Future<Object> submit = executorService.submit(() -> {
            Object resout = null;
            try {
                resout = joinPoint.proceed(new String[]{msg});
            } catch (Throwable throwable) {
            }
            return resout;
        });
        String s;
        try {
            s = (String) submit.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException t) {
            s = errorMsg(s2);
        }
        return s;
    }


    private String errorMsg(String msg) {
        return "AOP 熔断 " + msg;
    }


}
