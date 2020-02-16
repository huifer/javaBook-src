package com.huifer.utils.entity.demo;

import lombok.Data;

@Data
public class EntityJsonDemo {
    @JsonAnn(clazz = HelloJson.class)
    private String jsonStr;


}
