package com.huifer.kafka.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * <p>Title : InterceptorConsumer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-25
 */
public class InterceptorProducer {

    public static final String TOPIC = "tttt";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.106:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());

        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                MyProducerInterceptor.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);

        int i = 0;
        while (i < 100) {
            ProducerRecord<Integer, String> r1 = new ProducerRecord(TOPIC, 0,
                    System.currentTimeMillis() - 10000, null, "当前时间-10秒");
            ProducerRecord<Integer, String> r2 = new ProducerRecord(TOPIC, 0,
                    System.currentTimeMillis(), null, "当前时间");


            producer.send(r1);
            producer.send(r2);

            i++;
        }
        producer.close();

    }

}
