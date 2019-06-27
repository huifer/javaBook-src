package com.huifer.kafka.application.ttl;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * <p>Title : Consumer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class Consumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.106:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "ttl-group");
        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,
                TTLConsumerInterceptor.class.getName());

        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("ttl"));

        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(1000);
            for (ConsumerRecord<Integer, String> record : records) {
                System.out
                        .println(record.partition() + ":" + record.offset() + ":" + record.value());
            }
        }

    }

}
