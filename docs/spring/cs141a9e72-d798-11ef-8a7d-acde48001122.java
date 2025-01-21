package com.github.huifer.tuc;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TucApplication {

    static Map<Class<?>, String> aaa = new HashMap<>();
    public static void main(String[] args) throws ClassNotFoundException {
        ConfigurableApplicationContext run = SpringApplication.run(TucApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if ("rbna".equals(beanDefinitionName)) {
                String beanName = beanDefinitionName;
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                String beanClassName = beanDefinition.getBeanClassName();
                Class<?> aClass = Thread.currentThread().getContextClassLoader()
                        .loadClass(beanClassName);
                aaa.put(aClass, beanClassName);
                System.out.println();
            }

        }
    }

}
