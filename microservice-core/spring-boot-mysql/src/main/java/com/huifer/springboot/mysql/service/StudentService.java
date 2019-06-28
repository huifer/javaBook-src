package com.huifer.springboot.mysql.service;

import com.huifer.springboot.mysql.pojo.one.Student;

/**
 * <p>Title : StudentService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
public interface StudentService {

    void createStudent(String name, Integer age);

    void deleteByStudentName(String name);

    Integer getAllStudent();

    void deleteAllStudent();

    Student selectStudentByName(String name);

}
