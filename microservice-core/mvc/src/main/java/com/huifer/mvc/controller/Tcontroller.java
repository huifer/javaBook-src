package com.huifer.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Title : Tcontroller </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-22
 */
@Controller
public class Tcontroller {

    @GetMapping("/adapter")
    public String adapter(
            @RequestParam("user_id") long userId,
            @RequestParam("hc") String hc
    ) {
        return "kljl";
    }


    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "hello");

        return "h1";
    }

    @ModelAttribute(name = "upp")
    public String upp() {
        return "jkll";
    }


}
