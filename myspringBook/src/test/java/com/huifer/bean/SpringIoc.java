package com.huifer.bean;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

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
    public void testIocDemo03(){
        Teacher tea = (Teacher) context.getBean("teacherStatic");

    }

}