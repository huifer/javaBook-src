package com.huifer.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * <p>Title : Tcontroller </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-22
 */
@Controller
public class Tcontroller {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "hello");

        return "h1";
    }

    @ModelAttribute(name = "upp")
    public String upp(){
        return "jkll";
    }


}
