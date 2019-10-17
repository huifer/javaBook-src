package com.huifer.springmvc.controller;

import com.huifer.springmvc.pojo.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-10
 */
@Controller
@RequestMapping("item")
public class ItemController {
    @ResponseBody
    @GetMapping("/query")
    public ModelAndView query() throws Exception {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("吃的", 3.3, new Date()));
        itemList.add(new Item("玩的", 3.3, new Date()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("/WEB-INF/jsp/item.jsp");
        return modelAndView;
    }

}
