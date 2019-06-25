package com.huifer.kafka.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

/**
 * <p>Title : MyConsumerInterceptor </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class MyConsumerInterceptor implements ConsumerInterceptor {


    public static final long MISSING_TIME = 10 * 1000;

    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public ConsumerRecords onConsume(ConsumerRecords records) {
        long startTime = System.currentTimeMillis();
        Map<TopicPartition, List<ConsumerRecord<Integer, String>>> newRecords = new HashMap<>();
        for (Object partition : records.partitions()) {
            TopicPartition tp = (TopicPartition) partition;

            List<ConsumerRecord<Integer, String>> tpRecords = records.records(tp);
            List<ConsumerRecord<Integer, String>> newTpRecords = new ArrayList<>();
            for (ConsumerRecord<Integer, String> record : tpRecords) {
                if (startTime - record.timestamp() < MISSING_TIME) {
                    newTpRecords.add(record);
                }
            }
            if (!newTpRecords.isEmpty()) {
                newRecords.put(tp, newTpRecords);
            }
            return new ConsumerRecords(newRecords);


        }
        return null;
    }

    @Override
    public void onCommit(Map offsets) {
        offsets.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

    }

    @Override
    public void close() {

    }
}
