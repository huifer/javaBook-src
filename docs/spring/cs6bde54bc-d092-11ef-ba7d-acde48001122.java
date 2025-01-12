//package com.github.huifer.full.shiro.rest;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class LoginController {
//    @CrossOrigin
//    @PostMapping(value = "/user/login")
//    @ResponseBody
//    public Map login() {
//        HashMap<String, Object> response = new HashMap<>();
//        HashMap<String, Object> responseData = new HashMap<>();
//        responseData.put("token",1);
//        response.put("code",20000);
//        response.put("msg","登录成功");
//        response.put("data",responseData);
//        return response;
//    }
//
//    @CrossOrigin
//    @GetMapping(value = "/user/info")
//    @ResponseBody
//    public Map info() {
//        HashMap<String, Object> responseInfo = new HashMap<>();
//        HashMap<String, Object> responseData = new HashMap<>();
//        responseData.put("roles","admin");
//        responseData.put("name","Super admin");
//        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        responseInfo.put("code",20000);
//        responseInfo.put("msg","登录成功");
//        responseInfo.put("data",responseData);
//        return responseInfo;
//    }
//}