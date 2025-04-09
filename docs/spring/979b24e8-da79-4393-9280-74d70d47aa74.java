package com.huifer.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>Title : HystrixApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@SpringBootApplication
@EnableHystrix
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class HystrixApp {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApp.class, args);
    }

}
