package com.huifer.secweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-10
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("/add")
    public String add() {
        return "product/add";
    }

    @RequestMapping("/editor")
    public String editor() {
        return "product/editor";
    }

    @RequestMapping("/list")
    public String list() {
        return "product/list";
    }


    @RequestMapping("/del")
    public String del() {
        return "product/del";
    }

}
