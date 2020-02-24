package com.huifer.bilibili.redis.entity;

import com.alibaba.fastjson.JSON;
import com.huifer.bilibili.redis.base.StringToEntity;

import java.util.ArrayList;
import java.util.List;

public class UserEntity implements StringToEntity<UserEntity> {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity convert(String s) {
        return JSON.parseObject(s, UserEntity.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
