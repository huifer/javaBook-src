package com.huifer.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : CloudApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-28
 */
@SpringBootApplication
@RestController
public class CloudApp {

    @Autowired
    @Qualifier(value = "message")
    private String message;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setId("huifer’s context");

        context.registerBean("message", String.class, "this is message");

        context.refresh();

        new SpringApplicationBuilder(CloudApp.class)
                .parent(context)
                .run(args)
        ;


    }

    @GetMapping("/index")
    public String index() {
        return message;
    }


}
