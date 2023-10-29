package com.huifer.springboot.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : ProucerController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-19
 */
@RestController
public class ProucerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/test")
    public String sendMsg() {
        kafkaTemplate.send("hello-spring-boot-kafka", "hello");
        return "ok";
    }

}
