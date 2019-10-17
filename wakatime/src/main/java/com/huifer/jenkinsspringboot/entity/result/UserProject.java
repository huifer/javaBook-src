package com.huifer.jenkinsspringboot.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述:
 * 个人项目时间
 *
 * @author huifer
 * @date 2019-10-01
 */
@Data
@NoArgsConstructor
public class UserProject {
    /**
     * 用户id
     */
    private String id;
    /**
     * name
     */
    private String name;
    /**
     * 日期
     */
    private String day;

    private String userName;

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
