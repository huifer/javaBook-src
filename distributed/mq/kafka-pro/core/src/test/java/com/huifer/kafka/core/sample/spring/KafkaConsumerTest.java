package com.huifer.kafka.core.sample.spring;


import com.huifer.kafka.core.bean.KafkaConsumerWithCore;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;


public class KafkaConsumerTest {

    public static void main(String[] args) {
        ApplicationContext ctx_consumer = new ClassPathXmlApplicationContext(
                "kafka-consumer.xml");
        KafkaConsumerWithCore bean1 = (KafkaConsumerWithCore) ctx_consumer.getBean("kafka-consumer");
        KafkaConsumer kafkaConsumer = bean1.getConsumerConnector();

        kafkaConsumer.subscribe(Collections.singletonList("test"));

        while (true) {
            ConsumerRecords<Integer, String> poll = kafkaConsumer.poll(1000);
            for (ConsumerRecord<Integer, String> record : poll) {
                System.out.println("接收消息：" + record.value());
            }

        }

    }


}
