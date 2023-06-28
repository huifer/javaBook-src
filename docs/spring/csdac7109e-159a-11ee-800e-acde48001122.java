package com.huifer.kafka.core.sample.spring;


import com.huifer.kafka.core.bean.KafkaProducerWithCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class KafkaProducerTest {

    public static void main(String[] args) {
        ApplicationContext ctx_producer = new ClassPathXmlApplicationContext(
                "kafka-producer.xml");

        KafkaProducerWithCore bean = (KafkaProducerWithCore) ctx_producer.getBean("kafka-producer");
        bean.setDefaultTopic("111");
        bean.send2Topic("afa", "f");
        System.out.println();


    }


}
