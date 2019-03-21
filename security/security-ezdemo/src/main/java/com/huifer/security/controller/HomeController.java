package com.huifer.security.controller;

import com.huifer.security.entity.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-21
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
}
