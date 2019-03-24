package com.huifer.securityuserview.controller;

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
    public String home() {
        return "home";
    }

    @RequestMapping(value = {"/admin"})
    public String admin() {
        return "admin";
    }


    @RequestMapping(value = {"/vip"})
    public String vip() {
        return "vip";
    }

    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }
    @RequestMapping(value = {"/deny"})
    public String deny(){
        return "deny";
    }

}