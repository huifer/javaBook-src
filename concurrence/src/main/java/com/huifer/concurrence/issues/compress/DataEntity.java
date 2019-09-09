package com.huifer.concurrence.issues.compress;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataEntity {
    private Long id;
    /**
     * 温度
     */
    private BigDecimal wd;
    /**
     * 湿度
     */
    private BigDecimal sd;
    /**
     * 压力
     */
    private BigDecimal yl;

    private Long time;


}
