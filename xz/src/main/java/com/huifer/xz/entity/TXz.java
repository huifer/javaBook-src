package com.huifer.xz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TXz implements Serializable {
    private Integer id;

    private Integer cityId;

    private String name;

    private String url;

    private Integer page;

    private Integer userId;
}