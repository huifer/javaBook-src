package com.huifer.aop.aspects.comment;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *  切面类
 * @author huifer
 * @date 2019-03-04
 */
@Aspect
@Component(value = "myAspect")
public class MyAspect {


    @Before(value = "execution(* *..*.*ServiceImpl.*(..))")
    public void asBefor(){
        System.out.println("注解形式的前置通知");
    }



}