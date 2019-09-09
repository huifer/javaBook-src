package com.huifer.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
public class RibbonRestController {


    @Autowired
    @Qualifier(value = "ribbon")
    private RestTemplate restTemplate3;


    @Bean
    @Autowired
    @Qualifier(value = "ribbon") // 固定搜索范围
    public RestTemplate restTemplate3() {
        return new RestTemplate();
    }

    @GetMapping("/lb/{service}/say")
    public String lbSay(@PathVariable String service,
                        @RequestParam String message) {
        return restTemplate3
                .getForObject("http://" + service + "/say?message=" + message, String.class);
    }


    /**
     * 所有的restTemplate 都增加
     *
     * @param restTemplates
     * @param interceptor
     * @return
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
