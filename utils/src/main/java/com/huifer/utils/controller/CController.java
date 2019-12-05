package com.huifer.utils.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/hhh")
public class CController {
    @GetMapping("/dc")
    public Object json() {
        // 假设stuFat是查询出来的
        com.shands.hs.rest.admin.controller.attr.StuFat stuFat = new com.shands.hs.rest.admin.controller.attr.StuFat();
        stuFat.setUsername("zs");
        stuFat.setPwd("zs");
        stuFat.setHcName("zs");
        HashMap mapType = changeAttr(stuFat);
        // basebase 是没有字段的对象直接一个实体
        //{
        //  "hcName": "zs",
        //  "pwd": "zs"
        //}
        BaseBase entity = new BaseBase();
        Object target = com.shands.hs.rest.admin.controller.attr.ReflectUtil.getTarget(entity, mapType);
        return target;
    }

    /**
     * 修改实体返回一个HashMap
     * @return
     */
    private HashMap changeAttr(Object stuFat) {


        // 转换成map 对象,
        String jsonString = JSONObject.toJSONString(stuFat);
        HashMap mapType = JSON.parseObject(jsonString, HashMap.class);
        System.out.println(mapType);
        // 删除一个属性: 从map中删除
        mapType.remove("username");
        return mapType;
    }

    @GetMapping("/db")
    public Object jsonDB() {
        // 假设stuFat是查询出来的
        StuFat stuFat = new StuFat();
        stuFat.setUsername("zs");
        stuFat.setPwd("zs");
        stuFat.setHcName("zs");
        HashMap mapType = changeAttr(stuFat);
        // 通过 json实现 直接构建: 问题删除的对象会变成null，"" 等表示空的符号
        //{
        //  "hcName": "zs",
        //  "pwd": "zs",
        //  "username": ""
        //}
        String jsonString1 = JSONObject.toJSONString(mapType);
        StuFat stuFat1 = JSON.parseObject(jsonString1, StuFat.class);
        return stuFat1;
    }
}
