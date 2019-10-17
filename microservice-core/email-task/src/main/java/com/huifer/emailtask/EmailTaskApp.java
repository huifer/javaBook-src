package com.huifer.emailtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Title : EmailTaskApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
@SpringBootApplication
@EnableScheduling
public class EmailTaskApp {

    public static void main(String[] args) {
        SpringApplication.run(EmailTaskApp.class, args);
    }

}
