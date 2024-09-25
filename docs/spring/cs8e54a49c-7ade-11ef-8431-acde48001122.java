package com.huifer.zk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : SayController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-29
 */
@RestController
public class SayController {

    @GetMapping("/say")
    public String say(@RequestParam String message) {
        return "接收内容 : " + message;
    }

}
