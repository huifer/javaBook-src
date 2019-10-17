package com.huifer.kafka.serializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.util.Properties;

/**
 * <p>Title : StandAloneKafkaProducer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class StandAloneKafkaProducer extends Thread {

    private final KafkaProducer<Integer, Student> producer;
    private final String topic;

    public StandAloneKafkaProducer(String topic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.106:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");


        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StudentSerializer.class.getName());


        this.producer = new KafkaProducer(properties);
        this.topic = topic;

    }

    public static void main(String[] args) {

        StandAloneKafkaProducer test = new StandAloneKafkaProducer("interceptor");
        test.start();

    }

    @Override
    public void run() {
        int n = 0;
        while (n < 10) {
            n++;
            Student student = new Student("张三", "李四");
            producer.send(new ProducerRecord(topic, student));

        }
        producer.close();

    }

}
