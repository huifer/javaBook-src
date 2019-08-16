package com.huifer.kafka.core.sample.spring;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerDemo extends Thread {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public KafkaProducerDemo(String topic) {
        Properties properties = new Properties();
        // 连接到那一台kafka 可以填写多个用","分割
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        //
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");// 序列化手段
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");// 序列化手段
        properties.forEach((k, v) -> {
            System.out.println(k + "="+ v);
        });
        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    public static void main(String[] args) {

        KafkaProducerDemo test = new KafkaProducerDemo("test");
        test.start();

    }

    @Override
    public void run() {
        int n = 0;
        while (n < 50) {
            String msg = "msg_" + n;
            n++;
            System.out.println("发送消息" + msg);
            producer.send(new ProducerRecord<>(topic, msg));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}