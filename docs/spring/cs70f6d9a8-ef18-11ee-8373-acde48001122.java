package com.huifer.kafka.thread;

import com.huifer.kafka.utils.KafkaConsumerInit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title : KafkaConsumerThread01 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-25
 */
public class KafkaConsumerThread02 {

    public static void main(String[] args) {
        int threadNum = 3;

        for (int i = 0; i < threadNum; i++) {

            new KafkaConsumerThread(KafkaConsumerInit.conf(), "tttt", threadNum).start();

        }

    }

    /**
     * consumer 线程
     */
    public static class KafkaConsumerThread extends Thread {

        private KafkaConsumer consumer;

        private ExecutorService executorService;
        private int threadNumber;

        public KafkaConsumerThread(Properties properties, String topic, int threadNumber) {
            this.threadNumber = threadNumber;
            this.executorService = new ThreadPoolExecutor(this.threadNumber, this.threadNumber, 0L,
                    TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy());

            this.consumer = new KafkaConsumer(properties);
            this.consumer.subscribe(Collections.singletonList(topic));
        }

        @Override
        public void run() {
            try {

                while (true) {
                    System.out.println("准备处理消息");
                    ConsumerRecords<Integer, String> records = consumer.poll(1000);
                    executorService.submit(new RecordsHandler(records));
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                consumer.close();
            }
        }

        /**
         * 消息处理类
         */
        private class RecordsHandler implements Runnable {

            public final ConsumerRecords<Integer, String> records;

            public RecordsHandler(
                    ConsumerRecords<Integer, String> records) {
                this.records = records;
            }

            @Override
            public void run() {
                for (ConsumerRecord<Integer, String> record : records) {
                    System.out.println(Thread.currentThread().getName() + "===>" + record.value());
                }

            }
        }
    }

}
