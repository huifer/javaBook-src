package com.github.huifer.full.shiro;

import com.github.huifer.full.shiro.repo.DeletableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages={"com.github.huifer.full"},
    repositoryBaseClass = DeletableRepositoryImpl.class)
@EntityScan(basePackages = {"com.github.huifer.full"})
public class FullShiroApp {

  public static void main(String[] args) {
    SpringApplication.run(FullShiroApp.class, args);
  }

}
