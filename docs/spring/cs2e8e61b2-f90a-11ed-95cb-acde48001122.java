//package com.huifer.zk.controller;
//
//import com.huifer.zk.loadbalance.LoadBalanceRequestInterceptor;
//import java.util.Arrays;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
///**
// * <p>Title : RestTemplateVersionController </p>
// * <p>Description : </p>
// *
// * @author huifer
// * @date 2019-05-29
// */
//@RestController
//public class RestTemplateVersionController {
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//
//    @Bean
//    public ClientHttpRequestInterceptor interceptor() {
//        return new LoadBalanceRequestInterceptor();
//    }
//
//    @Bean
//    @Autowired
//    private RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Arrays.asList(interceptor));
//
//        return restTemplate;
//    }
//
//
//    @GetMapping("/{service}/say")
//    public String say(
//            @PathVariable String service,
//            @RequestParam String message
//    ) {
//        return restTemplate.getForObject("/" + service + "/say?message=" + message, String.class);
//    }
//
//
//}
