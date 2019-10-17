package com.huifer.kafka.core.bean;


import com.huifer.kafka.core.handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@ToString
public class KafkaConsumerWithCore {

    @Getter
    @Setter
    private String propertiesFileName;
    private Properties properties;
    @Getter
    @Setter
    private String topic;


    private MessageHandler handler;

    @Getter
    private KafkaConsumer consumerConnector;

    public KafkaConsumerWithCore(String propertiesFileName, String topic, MessageHandler handler) {
        this.propertiesFileName = propertiesFileName;
        this.topic = topic;
        this.handler = handler;

        init();
    }

    public KafkaConsumerWithCore() {
    }

    public void close() {
        consumerConnector.close();
    }


    protected void init() {
        if (properties == null) {
            properties = new Properties();
        }

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            log.error("kafka-consumer配置文件不存在,文件名={}", propertiesFileName);
            throw new IllegalArgumentException("配置文件不存在");
        }
        log.info("kafka-counsumer={}", properties);
        consumerConnector = new KafkaConsumer(this.properties);
    }


}
