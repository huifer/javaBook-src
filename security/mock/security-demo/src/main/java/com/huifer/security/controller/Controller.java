package com.huifer.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@RestController
@RequestMapping("/")
public class Controller {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
