package com.huifer.xz.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(
            @Qualifier("ClientHttpRequestFactory") ClientHttpRequestFactory httpRequestFactory
    ) {
        return new RestTemplate(httpRequestFactory);
    }

    @Bean(name = "ClientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setReadTimeout(10000);
        clientHttpRequestFactory.setConnectTimeout(10000);
        return clientHttpRequestFactory;
    }
}
