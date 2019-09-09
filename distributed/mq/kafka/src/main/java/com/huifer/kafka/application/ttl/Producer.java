package com.huifer.kafka.application.ttl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;

import java.util.Properties;

/**
 * <p>Title : Producer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class Producer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.106:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);

        String topic = "ttl";

        ProducerRecord<Integer, String> rec1 = new ProducerRecord<>(topic, 0,
                System.currentTimeMillis(), null, "正常消息",
                new RecordHeaders().add(new RecordHeader("ttl",
                        BytesUtils.long2byte(20L))));

        ProducerRecord<Integer, String> rec2 = new ProducerRecord<>(topic, 0,
                System.currentTimeMillis() - 5 * 1000, null, "超时消息1",
                new RecordHeaders().add(new RecordHeader("ttl",
                        BytesUtils.long2byte(5L))));

        ProducerRecord<Integer, String> rec3 = new ProducerRecord<>(topic, 0,
                System.currentTimeMillis() - 5 * 1000, null, "超时消息2",
                new RecordHeaders().add(new TTLHeader(5)));

        producer.send(rec1);
        producer.send(rec2);
        producer.send(rec3);
        producer.close();
    }


}
