package com.huifer.springboot.mysql.repo.two;

import com.huifer.springboot.mysql.pojo.two.StudentTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title : StudentTwoRepoServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@Service
public class StudentTwoRepoServiceImpl implements StudentTwoRepoService {

    @Autowired
    private StudentTwoRepo studentTwoRepo;

    @Override
    public StudentTwo ins() {
        return studentTwoRepo.save(StudentTwo.builder().address("beijing").build());
    }
}
