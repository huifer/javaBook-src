package com.huifer.jenkinsspringboot.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述:
 * 按项目人员统计
 *
 * @author huifer
 * @date 2019-10-01
 */
@Data
@NoArgsConstructor
public class ProjectWithUser {
    /**
     * 项目名称
     */
    private String projectNanme;
    private String day;
    /**
     * 开发小时数
     */
    private BigDecimal hours;
    /**
     * 开发分钟数
     */
    private BigDecimal minutes;
    /**
     * hours 小时 + minutes 分钟
     */
    private String text;

    private BigDecimal totalSeconds;

    private long timestamp;

}
