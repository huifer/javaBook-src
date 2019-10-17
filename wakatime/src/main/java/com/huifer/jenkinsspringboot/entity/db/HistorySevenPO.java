package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HistorySevenPO {
    private Integer uid;

    /**
     * <string: total coding activity in digital clock format>,
     */
    private String digital;

    /**
     * <integer: hours portion of coding activity>,
     */
    private Integer hours;

    /**
     * <integer: minutes portion of coding activity>,
     */
    private Integer minutes;
    /**
     * <string: project name>,
     */
    private String name;

    /**
     * <float: percent of time spent in this project>,
     */
    private BigDecimal percent;

    /**
     * <string: total coding activity in human readable format>,
     */
    private String text;
    /**
     * <float: total coding activity as seconds>,
     */
    private BigDecimal totalSeconds;

    private String apiKey;
}