package com.huifer.securityuserview.aop;

import java.lang.annotation.*;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-24
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspLog {
    String value() default "";
}
