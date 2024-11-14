package com.huifer.springboot.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * <p>Title : KafkaConsumerMessageListener </p>
 * <p>Description : kafka 消息监听</p>
 *
 * @author huifer
 * @date 2019-06-19
 */
public class KafkaConsumerMessageListener {

    @KafkaListener(topics = {"hello-spring-boot-kafka"})
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("key = " + record.key() + "\t" + "value = " + record.value());
    }

}
