package com.huifer.springboot.mysql.service.impl;

import com.huifer.springboot.mysql.pojo.one.Student;
import com.huifer.springboot.mysql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>Title : StudentServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void createStudent(String name, Integer age) {
        jdbcTemplate.update(" insert into student (age, name, id) values (?, ?, ?)", age, name, 0);
    }

    @Override
    public void deleteByStudentName(String name) {
        jdbcTemplate.update("delete from student where name = ?", name);
    }

    @Override
    public Integer getAllStudent() {
        return jdbcTemplate.queryForObject("select count(1) from student", Integer.class);
    }

    @Override
    public void deleteAllStudent() {
        jdbcTemplate.update("delete from student");
    }

    @Override
    public Student selectStudentByName(String name) {
        return null;
    }
}
