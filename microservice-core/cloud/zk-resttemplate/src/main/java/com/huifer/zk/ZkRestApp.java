package com.huifer.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Title : ZkRestApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class ZkRestApp {

    public static void main(String[] args) {
        SpringApplication.run(ZkRestApp.class, args);
    }
}
