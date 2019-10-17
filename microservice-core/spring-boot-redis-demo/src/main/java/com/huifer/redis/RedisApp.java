package com.huifer.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title : RedisApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
@SpringBootApplication
@MapperScan("com.huifer.redis.*")
public class RedisApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }
}
