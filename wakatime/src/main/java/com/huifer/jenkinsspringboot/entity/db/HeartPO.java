package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HeartPO {
    private Integer proUserId;

    /**
     * <string: branch name (optional)>,
     */
    private String branch;

    /**
     * <string: category for this activity; can be coding, building, indexing, debugging, browsing, running tests, writing tests, manual testing, code reviewing, or designing>
     */
    private String category;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * <integer: current cursor column position (optional)>,
     */
    private String cursorpos;

    /**
     * <string: comma separated list of dependencies detected from entity file (optional)>,
     */
    private String dependencies;

    private String id;

    /**
     * <boolean: whether this heartbeat was triggered from writing to a file (optional)>,
     */
    private String isWrite;
    /**
     * <string: language name (optional)>
     */
    private String language;

    /**
     * <integer: current line row number of cursor (optional)>
     */
    private String lineno;

    /**
     * <integer: total number of lines in the entity (when entity type is file)>,
     */
    private String lines;

    private String machineNameId;

    /**
     * <string: project name (optional)>
     */
    private String project;

    /**
     * <float: UNIX epoch timestamp; numbers after decimal point are fractions of a second>,
     */
    private BigDecimal time;

    /**
     * <string: type of entity; can be file, app, or domain>
     */
    private String type;

    private String userAgentId;

    private String userId;

    private Date updateTime;

    /**
     * <string: entity heartbeat is logging time against, such as an absolute file path or domain>,
     */
    private String entity;

    /**
     * 日期
     */
    private String day;
}