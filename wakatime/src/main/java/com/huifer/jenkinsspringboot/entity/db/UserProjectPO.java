package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class UserProjectPO {
    private Integer uid;

    private Date createdAt;

    private String htmlEscapedName;

    private String id;

    private String name;

    private String repository;

    private String url;

    private String apiKey;
}