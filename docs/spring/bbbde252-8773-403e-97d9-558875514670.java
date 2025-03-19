package com.huifer.feign;

import com.huifer.feign.annotation.EnableRestClient;
import com.huifer.feign.clients.SayService;
import com.huifer.feign.rest.client.MySayService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Title : FeignClientApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@SpringBootApplication
@EnableFeignClients(clients = {SayService.class})
@EnableDiscoveryClient
@EnableScheduling
@EnableRestClient(clients = {MySayService.class})
class FeignClientApp {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApp.class, args);
    }

}
