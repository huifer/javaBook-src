package com.huifer.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JavaBean implements BeanPostProcessor {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("BeanLive.xml");
        JavaBean javaBean = (JavaBean) ctx.getBean("javaBean");
        javaBean.getName();
        ctx.close();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("前置");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理");
        return bean;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setter 方法");
        this.name = name;
    }

    public JavaBean() {
    }

    public void init() {
        System.out.println("执行初始化方法");
    }


    public void close() {
        System.out.println("执行摧毁方法");
    }
}
