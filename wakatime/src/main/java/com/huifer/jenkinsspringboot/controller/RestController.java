package com.huifer.jenkinsspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-09-27
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/")
    public String hello() {
        return "hello ";
    }
}
