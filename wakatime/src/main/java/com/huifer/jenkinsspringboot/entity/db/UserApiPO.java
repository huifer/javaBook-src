package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

@Data
public class UserApiPO {
    private Integer id;

    private String name;

    private String apiKey;
}