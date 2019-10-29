package com.huifer.xz.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TWoinfo implements Serializable {
    private Integer fid;

    private BigDecimal heartSource;

    private BigDecimal likeCount;

    private BigDecimal duration;

    private BigDecimal sport;

    private BigDecimal id;
    private Date uploadTime;

    private Integer userId;

    private String uuid;

    private String title;

    private BigDecimal cadenceSource;

    private BigDecimal isValid;

    private BigDecimal commentCount;

    private BigDecimal elevationLoss;

    private BigDecimal hidden;

    private String desc;

    private String threedWorkout;

    private BigDecimal mapId;

    private BigDecimal elevationGain;

    private Long startTime;

    private BigDecimal credits;

    private BigDecimal isSegment;

    private BigDecimal isLike;

    private BigDecimal distance;

    private BigDecimal calories;

    private BigDecimal locSource;

    private BigDecimal mapHidden;

    private Long endTime;

    private BigDecimal avgSpeed;

    private Integer year;

    private Integer month;
}