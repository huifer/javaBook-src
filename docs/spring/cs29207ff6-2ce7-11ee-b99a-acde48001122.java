package com.huifer.feign.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestClient {

    /**
     * rest 服务名称
     */
    String name();

}
