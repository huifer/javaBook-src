package com.huifer.springboot.kafka.consumer.bean;

import com.huifer.springboot.kafka.consumer.service.KafkaConsumerMessageListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaConsumerConfigTest {

    @Autowired
    private KafkaConsumerMessageListener listener;


    @Test
    public void testListener() {
    }


}
