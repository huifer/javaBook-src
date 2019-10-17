package com.huifer.feign.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * <p>Title : RibbonRestController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@Configuration
public class RibbonConfig {


    @Autowired
    @Qualifier(value = "ribbon")
    private RestTemplate restTemplate3;


    @Bean
    @Autowired
    @Qualifier(value = "ribbon") // 固定搜索范围
    public RestTemplate restTemplate3() {
        return new RestTemplate();
    }


    /**
     * 所有的restTemplate 都增加
     */
    @Bean
    @Autowired
    public Object f(@Qualifier(value = "ribbon") Collection<RestTemplate> restTemplates,
                    ClientHttpRequestInterceptor interceptor) {
        restTemplates.forEach(
                restTemplate -> {
                    restTemplate.setInterceptors(Arrays.asList(interceptor));
                }
        );
        return new Object();
    }


}
