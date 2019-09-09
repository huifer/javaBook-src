package com.huifer.alibaba.nacos.springboot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: wang
 * @description: Nacos-spring-boot
 */
@SpringBootApplication
@NacosPropertySource(dataId = "springboot-nacos-config", autoRefreshed = true)
public class NacosSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringBootApp.class, args);
    }

}