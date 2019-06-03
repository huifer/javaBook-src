package com.huifer.bus.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * <p>Title : SpringEvent </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-03
 */
public class SpringEvent {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.addApplicationListener((ApplicationListener<ContextRefreshedEvent>) e -> {
            System.out.println(" ContextRefreshedEvent 监听器");
        });

        context.addApplicationListener((ApplicationListener<ContextClosedEvent>) e -> {
            System.out.println(" ContextClosedEvent 监听器");
        });

        context.refresh();
        context.close();
    }

}
