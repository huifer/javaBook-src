package com.huifer.webflux.repository;

import com.huifer.webflux.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
public interface StudentRepository
        extends ReactiveMongoRepository<Student, String> {


    /***
     * 根据年龄上下限查询 ，都不包含边界
     * @param below 下限
     * @param top 上限
     * @return
     */
    Flux<Student> findByAgeBetween(int below, int top);


}
