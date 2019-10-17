package com.huifer.jenkinsspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.huifer.jenkinsspringboot.mapper")
public class JenkinsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsSpringBootApplication.class, args);
    }

}
