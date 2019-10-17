package com.huifer.redis.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Title : Student </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class Student implements Serializable {


    private static final long serialVersionUID = 4128119411324040794L;
    private String name;
    private Integer age;
    private String clazz = Student.class.getName();

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName()) &&
                Objects.equals(getAge(), student.getAge()) &&
                Objects.equals(getClazz(), student.getClazz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getClazz());
    }
}
