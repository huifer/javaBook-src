package com.huifer.kafka.transaction;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * <p>Title : Transaction01 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-25
 */
public class Transaction01 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.106:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction-01");

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);
        // 初始化事务
        producer.initTransactions();
        // 开启事务
        producer.beginTransaction();

        try {
            for (int i = 0; i < 10; i++) {
                ProducerRecord<Integer, String> record1 = new ProducerRecord<>("hello-transaction",
                        "msg1");
                producer.send(record1);
            }
            // 提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            // 终止事务
            producer.abortTransaction();
        } finally {
            producer.close();

        }

    }

}
