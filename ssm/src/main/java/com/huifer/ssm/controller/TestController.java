package com.huifer.ssm.controller;

import com.huifer.ssm.pojo.Item;
import com.huifer.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-11
 */
@Controller
@RequestMapping(value = "item", produces = "application/json;charset=utf8")
public class TestController {
    @Autowired
    private ItemService service;

    @GetMapping("queryItem")
    @ResponseBody
    public ResponseEntity<List<Item>> queryItem() {
        List<Item> items = service.queryItemList();

        return ResponseEntity.ok().body(items);
    }


}
