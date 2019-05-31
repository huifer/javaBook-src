package com.huifer.feign.controller;

import com.huifer.feign.clients.SayService;
import com.huifer.feign.rest.client.MySayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : NewRestController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@RestController
public class NewRestController {

    @Autowired
    private SayService sayService;


    @Autowired
    private MySayService mySayService;


    @GetMapping("/my/say")
    public String mySay(@RequestParam String message) {
        return mySayService.say(message);
    }


    @GetMapping("/feign/say")
    public String say(@RequestParam String message) {
        String say = sayService.say(message);
        return say;
    }


}
