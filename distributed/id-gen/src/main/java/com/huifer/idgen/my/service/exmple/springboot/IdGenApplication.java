package com.huifer.idgen.my.service.exmple.springboot;

import com.huifer.idgen.my.service.GenIdService;
import com.huifer.idgen.my.service.bean.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 1
 * @description: spring boot
 */
@SpringBootApplication
@RestController
public class IdGenApplication {

    @Autowired
    GenIdService genIdService;


    public static void main(String[] args) {
        SpringApplication.run(IdGenApplication.class, args);
    }

    @GetMapping("/gen_id")
    public long createId() {
        return genIdService.genId();
    }

    @GetMapping("exp")
    public Id expId(@RequestParam("id") long id) {
        return genIdService.expId(id);
    }
}