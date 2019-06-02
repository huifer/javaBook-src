package com.huifer.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebFluxApp {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApp.class, args);
    }
}
