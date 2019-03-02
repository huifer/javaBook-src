package com.huifer.bean;

/**
 * 描述:
 * 老师工厂
 *
 * @author huifer
 * @date 2019-03-02
 */
public class TeacherFactory {
    /**
     * 简单工厂
     *
     * @return
     */
    public Teacher createTeacher() {
        System.out.println("自定义老师工厂 简单工厂");
        return new Teacher();
    }


    /**
     * 静态工厂
     *
     * @return
     */
    public static Teacher createStaticTeacher() {
        System.out.println("静态工厂的创建");
        return new Teacher();
    }
}
