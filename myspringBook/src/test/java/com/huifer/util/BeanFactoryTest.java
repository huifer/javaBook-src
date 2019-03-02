package com.huifer.util;

import com.huifer.bean.Student;
import com.huifer.bean.Teacher;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BeanFactoryTest {


    @Test
    public void beanTest01() {
        // 1.声明 bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("teacher");
        beanDefined.setClassPath("com.huifer.bean.Teacher");
        // 2.将bean放到工厂中
        List<BeanDefined> beanDefinedList = new ArrayList<BeanDefined>();
        beanDefinedList.add(beanDefined);
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.setBeanDefineds(beanDefinedList);
        // 3.从工厂中获取具体实例

        Teacher teacher = (Teacher) beanFactory.getBean("teacher");

        System.out.println();
    }

    @Test
    public void beanTest02() throws Exception {
        // 1.声明 bean
        BeanDefined bean01 = new BeanDefined();
        bean01.setBeanId("teacher");
        bean01.setClassPath("com.huifer.bean.Teacher");
        bean01.setScope("prototype");


        BeanDefined bean02 = new BeanDefined();
        bean02.setBeanId("student");
        bean02.setClassPath("com.huifer.bean.Student");


        // 2.将bean放到工厂中
        List<BeanDefined> beanDefinedList = new ArrayList<BeanDefined>();
        beanDefinedList.add(bean01);
        beanDefinedList.add(bean02);
        BeanFactory beanFactory = new BeanFactory(beanDefinedList);
        // 3.从工厂中获取具体实例

        Teacher teacher1 = (Teacher) beanFactory.getBean("teacher");
        Teacher teacher2 = (Teacher) beanFactory.getBean("teacher");

        System.out.println(teacher1 == teacher2);
        Assert.assertFalse(teacher1==teacher2);
        Student stu = (Student) beanFactory.getBeanScope("student");
        Student st2 = (Student) beanFactory.getBeanScope("student");
        System.out.println(stu == st2);
        Assert.assertTrue(stu == st2);

        System.out.println();
    }



    @Test
    public void beanTest03() throws Exception {
        BeanDefined bean01 = new BeanDefined();
        bean01.setBeanId("teacher");
        bean01.setClassPath("com.huifer.bean.TeacherFactory");
        bean01.setFactoryBean("tFactory");
        bean01.setFactoryMethod("createTeacher");
        bean01.setScope("prototype");



        List<BeanDefined> beanDefinedList = new ArrayList<BeanDefined>();
        beanDefinedList.add(bean01);

        BeanFactory beanFactory = new BeanFactory(beanDefinedList);


        Teacher teacher1 = (Teacher) beanFactory.getBeanFactory("teacher");
        System.out.println(teacher1);

    }

}