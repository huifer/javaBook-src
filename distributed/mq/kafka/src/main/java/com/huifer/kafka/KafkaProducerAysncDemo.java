package com.huifer.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * <p>Title : KafkaProducerDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class KafkaProducerAysncDemo extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final boolean isAysnc;

    public KafkaProducerAysncDemo(String topic, boolean isAysnc) {

        Properties properties = new Properties();
        // 连接到那一台kafka 可以填写多个用","分割
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.108:9092,192.168.1.106:9092,192.168.1.106:9092");
        //
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");// 序列化手段
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");// 序列化手段
        this.producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
        this.isAysnc = isAysnc;
    }

    public static void main(String[] args) {

        KafkaProducerAysncDemo test = new KafkaProducerAysncDemo("test", true);
        test.start();

    }

    @Override
    public void run() {
        int n = 0;
        while (n < 50) {
            String msg = "msg_" + n;
            System.out.println("发送消息" + msg);

            if (isAysnc) {

                producer.send(new ProducerRecord<Integer, String>(topic, msg), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (metadata != null) {
                            System.out.println("异步-offset : " + metadata.offset());
                            System.out.println("异步-partition : " + metadata.partition());
                        }
                    }
                });

            } else {
                try {
                    RecordMetadata metadata = producer.send(new ProducerRecord<>(topic, msg))
                            .get();
                    System.out.println("同步-offset : " + metadata.offset());
                    System.out.println("同步-partition : " + metadata.partition());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }

            n++;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
