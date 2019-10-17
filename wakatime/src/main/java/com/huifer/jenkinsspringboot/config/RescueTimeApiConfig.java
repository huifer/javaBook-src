package com.huifer.jenkinsspringboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 2019-09-30
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rescuetime")
public class RescueTimeApiConfig {
    /**
     * 基础url
     */
    private String base_url;
    /**
     * 分析api
     */
    private String analytic_url;
    /**
     * 每日摘要信息
     */
    private String daily_summary_url;
    /**
     * 警报api
     */
    private String alert_url;
    /**
     * 重点api
     */
    private String highlight_url;
    /**
     * 重点介绍api
     */
    private String highlight_info_url;
}
