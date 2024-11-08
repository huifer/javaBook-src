package com.huifer.springboot.kafka.producer.bean;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerConfigTest {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void testSend() {
        ListenableFuture send = kafkaTemplate
                .send(new ProducerRecord<String, String>("hello-spring-boot-kafka", "123"));
        System.out.println(send);
    }
}
