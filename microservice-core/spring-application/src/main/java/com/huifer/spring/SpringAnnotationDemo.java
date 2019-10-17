package com.huifer.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>Title : SpringAnnotationDemo </p>
 * <p>Description : Spring注解驱动</p>
 *
 * @author huifer
 * @date 2019-05-22
 */

public class SpringAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDemo.class);
        context.refresh();

        System.out.println(
                context.getBean("filterRegistrationBean")
        );

        // 输出 Application 的应用类名
        System.out.println(
                context.getClass().getName()
        );

    }

    @Bean
    public String filterRegistrationBean() {
        return "张三";
    }
}
