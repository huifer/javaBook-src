package com.huifer.jenkinsspringboot.entity.wakarest;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * waka time 接口信息
 *
 * @Date: 2019-09-29
 */
@Data
public class HeartRest {
    private String branch;

    private String category;

    private Date createdAt;

    private String cursorpos;

    private String[] dependencies;

    private String entity;

    private String id;

    private String isWrite;

    private String language;

    private String lineno;

    private String lines;

    private String machineNameId;

    private String project;

    private BigDecimal time;

    private String type;

    private String userAgentId;

    private String userId;

}
