package com.huifer.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>Title : DemoApplication </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-22
 */
@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

    }

}

