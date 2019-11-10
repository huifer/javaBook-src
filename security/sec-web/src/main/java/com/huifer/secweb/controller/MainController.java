package com.huifer.secweb.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-10
 */
@Controller
public class MainController {

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/userLogin")
    public String login(){
        return "login";
    }

    /**
     * 错误页面
     * @return
     */
    @RequestMapping("/error")
    public String error(){
        return "error";
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String str = b.encode("123");
        System.out.println("Encoding " + str);
    }
}
