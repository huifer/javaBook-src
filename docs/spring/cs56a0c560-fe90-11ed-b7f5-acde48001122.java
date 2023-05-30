package com.huifer.stream;

import com.huifer.stream.stream.SimpleClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * <p>Title : StreamApp </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(SimpleClient.class)
public class StreamApp {

    public static void main(String[] args) {
        SpringApplication.run(StreamApp.class, args);
    }
}
