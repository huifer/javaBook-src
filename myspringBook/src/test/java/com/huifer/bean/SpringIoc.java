package com.huifer.bean;

import com.huifer.proxy.BaseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoc {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("spring_config.xml");

    }

    @Test
    public void testIocDemo01() {
        Student stu = (Student) context.getBean("student");
        Teacher tea = (Teacher) context.getBean("teacher");


        System.out.println();
    }

    @Test
    public void testIocDemo02() {
        Teacher tea = (Teacher) context.getBean("teacher1");

    }

    @Test
    public void testIocDemo03() {
        Teacher tea = (Teacher) context.getBean("teacherStatic");

    }

    @Test
    public void testIocDemo04() {
        BaseService so = (BaseService) context.getBean("tec");
        String s = so.doSome();
        System.out.println(s);
    }


    @Test
    public void testIocDemo05() {
        Di di = (Di) context.getBean("di01");
        System.out.println();
    }


}