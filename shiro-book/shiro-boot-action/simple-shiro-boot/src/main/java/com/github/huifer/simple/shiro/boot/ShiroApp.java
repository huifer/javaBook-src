package com.github.huifer.simple.shiro.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
 @EnableJpaRepositories(basePackages = {"com.github.huifer.simple.shiro.boot.repo"})
@EntityScan(basePackages = {"com.github.huifer.simple.shiro.boot.entity"})
public class ShiroApp {

  public static void main(String[] args) {
    SpringApplication.run(ShiroApp.class);
  }

}
