package com.huifer.utils.entity.demo;

import com.alibaba.fastjson.JSONObject;
import com.huifer.utils.factory.Transform;
import lombok.Data;

import java.util.Map;

@Data
public class HelloJson implements Transform< HelloJson> {
    private String name;
    private Integer age;

    @Override
    public HelloJson transform(String s) {
        return JSONObject.parseObject(s, HelloJson.class);
    }


}
