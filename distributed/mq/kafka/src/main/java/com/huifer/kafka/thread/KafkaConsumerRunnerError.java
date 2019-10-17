package com.huifer.kafka.thread;

import com.huifer.kafka.utils.KafkaConsumerInit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerRunnerError implements Runnable {

    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer consumer;
    private final String top;

    public KafkaConsumerRunnerError(KafkaConsumer consumer, String top) {
        this.consumer = consumer;
        this.top = top;
    }

    public static void main(String[] args) {

        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(KafkaConsumerInit.conf());

        for (int i = 0; i < 3; i++) {
            new Thread(new KafkaConsumerRunnerError(kafkaConsumer, "tttt"), "thread-" + i).start();
        }
    }

    @Override
    public void run() {
        try {

            consumer.subscribe(Collections.singleton(this.top));
            while (!closed.get()) {
                ConsumerRecords<Integer, String> poll = consumer.poll(1000);
                if (poll.isEmpty()) {

                    for (ConsumerRecord<Integer, String> record : poll) {
                        System.out
                                .println(Thread.currentThread().getName() + " 接收消息：" + record
                                        .value());
                    }
                }
            }
        } catch (WakeupException e) {
            if (!closed.get()) {
                throw e;
            }
        } finally {
            consumer.close();
        }
    }

}
