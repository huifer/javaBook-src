package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserDurationsPO {
    private Integer uid;


    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * <integer: current cursor column position (optional)>,
     */
    private String cursorpos;

    /**
     * <float: length of time of this duration in seconds>
     */
    private BigDecimal duration;

    private String id;

    /**
     * <integer: current line row number of cursor (optional)>
     */
    private String lineno;

    private String machineNameId;


    /**
     * <string: project name (optional)>
     */
    private String project;

    /**
     * <float: UNIX epoch timestamp; numbers after decimal point are fractions of a second>,
     */
    private BigDecimal time;

    private String userId;

    private String apiKey;

    private String day;
}