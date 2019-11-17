package com.huifer.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApp {


    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }



}
