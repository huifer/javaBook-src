package com.huifer.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>Title : BusApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-03
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BusApp {

    public static void main(String[] args) {
        SpringApplication.run(BusApp.class, args);
    }

}
