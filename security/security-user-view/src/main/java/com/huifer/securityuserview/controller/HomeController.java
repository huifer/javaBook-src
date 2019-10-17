package com.huifer.securityuserview.controller;

import com.huifer.securityuserview.aop.AspLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-21
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/home"})
    @AspLog(value = "这是一个测试")
    public String home() {
        return "home";
    }

    @RequestMapping(value = {"/admin"})
    @AspLog(value = "这是一个测试")
    public String admin() {
        return "admin";
    }


    @RequestMapping(value = {"/vip"})
    @AspLog(value = "这是一个测试")
    public String vip() {
        return "vip";
    }

    @RequestMapping(value = {"/login"})
    @AspLog(value = "这是一个测试")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/deny"})
    @AspLog(value = "这是一个测试")
    public String deny() {
        return "deny";
    }

}