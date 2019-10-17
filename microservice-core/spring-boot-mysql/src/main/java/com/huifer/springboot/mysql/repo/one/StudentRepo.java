package com.huifer.springboot.mysql.repo.one;

import com.huifer.springboot.mysql.pojo.one.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Title : StudentRepo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
public interface StudentRepo extends JpaRepository<Student, Long> {


}
