package com.huifer.kafka.thread;

import com.huifer.kafka.utils.KafkaConsumerInit;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * <p>Title : KafkaConsumerThread01 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-25
 */
public class KafkaConsumerThread01 {

    public static void main(String[] args) {
        int threadNum = 3;

        for (int i = 0; i < threadNum; i++) {

            new KafkaConsumerThread(KafkaConsumerInit.conf(), "tttt").start();

        }

    }

    public static class KafkaConsumerThread extends Thread {

        private KafkaConsumer consumer;

        public KafkaConsumerThread(Properties properties, String topic) {
            consumer = new KafkaConsumer(properties);
            consumer.subscribe(Collections.singletonList(topic));
        }

        @Override
        public void run() {
            try {

                while (true) {
                    System.out.println("准备处理消息");
                    ConsumerRecords<Integer, String> records = consumer.poll(1000);
                    for (ConsumerRecord<Integer, String> record : records) {
                        System.out
                                .println(Thread.currentThread().getName() + " --->" + record
                                        .value());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                consumer.close();
            }
        }
    }


}
