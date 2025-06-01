package com.huifer.springboot.mysql.repo.one;

import com.huifer.springboot.mysql.pojo.one.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title : StudentRepoService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@Service
public class StudentRepoServiceImpl implements StudentRepoService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student insert() {
        return studentRepo.save(Student.builder().name("zhangsan").age(11).build());
    }
}
