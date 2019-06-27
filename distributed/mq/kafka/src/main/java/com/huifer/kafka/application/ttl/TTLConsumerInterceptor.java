package com.huifer.kafka.application.ttl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

/**
 * <p>Title : DelayedConsumerInterceptor </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class TTLConsumerInterceptor implements ConsumerInterceptor<Integer, String> {

    @Override
    public ConsumerRecords<Integer, String> onConsume(ConsumerRecords<Integer, String> records) {
        long now = System.currentTimeMillis();

        Map<TopicPartition, List<ConsumerRecord<Integer, String>>> newRecords
                = new HashMap<>();

        for (TopicPartition tp : records.partitions()) {
            List<ConsumerRecord<Integer, String>> tpRecords = records.records(tp);
            List<ConsumerRecord<Integer, String>> newTpRecords = new ArrayList<>();
            for (ConsumerRecord<Integer, String> record : tpRecords) {
                Headers headers = record.headers();
                long ttl = -1;
                for (Header header : headers) {
                    if ("ttl".equalsIgnoreCase(header.key())) {
                        ttl = BytesUtils.byte2long(header.value());
                    }
                }
                if (ttl > 0 && now - record.timestamp() <= ttl * 1000) {
                    System.out.println("正常消息:\t" + record.value());
                    newTpRecords.add(record);
                } else {
                    System.out.println("超时消息 ,不接收:\t" + record.value());
//                    newTpRecords.add(record);
                }
            }
            if (!newTpRecords.isEmpty()) {
                newRecords.put(tp, newTpRecords);
            }
        }
        return new ConsumerRecords<Integer, String>(newRecords);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
