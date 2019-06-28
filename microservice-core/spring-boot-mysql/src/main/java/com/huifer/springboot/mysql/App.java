package com.huifer.springboot.mysql;

import com.huifer.springboot.mysql.repo.one.StudentRepoService;
import com.huifer.springboot.mysql.repo.two.StudentTwoRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : App </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@SpringBootApplication
@RestController
public class App {

    @Autowired
    private StudentRepoService studentRepoService;
    @Autowired
    private StudentTwoRepoService studentTwoRepoService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);


    }

    @GetMapping("/")
    public void ss() {
        studentRepoService.insert();
        studentTwoRepoService.ins();

    }
}
