package com.huifer.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : Controller </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-23
 */
@RestController
public class Controller {

    @GetMapping(name = "/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("hello");
    }


    @PostMapping(value = "/string")
    public String readString(@RequestBody String string) {
        return "测试数据" + string + "'";
    }

}
