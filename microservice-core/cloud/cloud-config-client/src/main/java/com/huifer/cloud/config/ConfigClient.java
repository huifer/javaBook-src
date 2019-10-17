package com.huifer.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : ConfigClient </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-28
 */
@SpringBootApplication
@RestController
public class ConfigClient {


    @Value("${name}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
    }

    @GetMapping
    public String index() {
        return message;
    }
}
