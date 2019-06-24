package com.huifer.kafka.interceptor;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * <p>Title : MyInterceptor </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class MyInterceptor implements ProducerInterceptor {

    private volatile long successCount = 0;
    private volatile long errorCount = 0;

    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        String newValue = "prefix -" + record.value();
        return new ProducerRecord<>(
                record.topic(),
                record.partition(),
                record.timestamp(),
                record.key(),
                newValue,
                record.headers()
        );
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            successCount += 1;
        } else {
            errorCount += 1;
        }
    }

    @Override
    public void close() {
        double ratio = (double) successCount / (successCount + errorCount);
        System.out.println("消息发送成功率 = " + (ratio * 100) + "%");
    }
}
