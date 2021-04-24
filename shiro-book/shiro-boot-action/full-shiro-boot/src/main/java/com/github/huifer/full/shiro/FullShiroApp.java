package com.github.huifer.full.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.huifer.full.shiro.dao")
public class FullShiroApp {

  public static void main(String[] args) {
    SpringApplication.run(FullShiroApp.class, args);
  }
}
