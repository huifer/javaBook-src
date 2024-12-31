package com.huifer.feign.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FeignClientsRegistart.class)
public @interface EnableRestClient {

    /**
     * 指定@RestClient 接口
     */
    Class<?>[] clients() default {};

}
