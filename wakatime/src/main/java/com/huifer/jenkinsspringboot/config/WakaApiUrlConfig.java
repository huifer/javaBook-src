package com.huifer.jenkinsspringboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * waka API配置
 *
 * @Date: 2019-09-29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wakatime")
public class WakaApiUrlConfig {
    private String baseUrl;
    private String clientId;
    private String secretApiKey;
    private String oauthUrl;
    private String userInfoUrl;
    private String durationUrl;
    private String heartUrl;
    private String projectUrl;
    private String summaryUrl;
    private String projectDetail;
    private String hositorySeven;

}
