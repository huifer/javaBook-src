package com.huifer.hystrix.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Fusing {

    /**
     * 超时时间
     *
     * @return
     */
    int timeout() default 100;

}
