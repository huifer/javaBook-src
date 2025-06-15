package com.huifer.bigfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>Title : App </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.huifer.bigfile"})
@EnableTransactionManagement
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
