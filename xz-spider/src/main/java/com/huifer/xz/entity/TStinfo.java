package com.huifer.xz.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TStinfo implements Serializable {
    private Integer fid;

    private BigDecimal sumDuration;

    private BigDecimal sumDistance;

    private BigDecimal sumElevationGain;

    private BigDecimal countDistance;

    private BigDecimal sumCredits;

    private Integer userId;

    private Integer year;

    private Integer month;
}