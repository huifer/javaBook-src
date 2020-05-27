package org.huifer.rbac.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.huifer.rbac.entity.req.UserAddReq;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @PostMapping("/go")
    public Object go(@RequestBody @Validated UserAddReq o) {

        Map<String, Object> res = new HashMap<>();
        res.put("1", "null");
        res.put("2", null);
        res.put("3", new Date());
        res.put("4", o);
        return res;
    }
}
