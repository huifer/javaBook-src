package com.huifer.jenkinsspringboot.entity.db;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryCatePO {
    private Integer id;

    private String apiKey;

    private String day;

    private String digital;

    private BigDecimal hours;

    private BigDecimal minutes;

    private String name;

    private BigDecimal percent;

    private BigDecimal seconds;

    private String text;

    private BigDecimal totalSeconds;
}