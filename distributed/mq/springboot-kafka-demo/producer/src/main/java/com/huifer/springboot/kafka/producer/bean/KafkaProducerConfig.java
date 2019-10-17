package com.huifer.springboot.kafka.producer.bean;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title : KafkaProducerConfig </p>
 * <p>Description : kafka producer config</p>
 *
 * @author huifer
 * @date 2019-06-19
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.producer.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;
    @Value("${kafka.producer.client.id}")
    private String CLIENT_ID;
    @Value("${kafka.producer.acks}")
    private String ACKS;
    @Value("${kafka.producer.key.serializer}")
    private String KEY_SERIALIZER;
    @Value("${kafka.producer.value.serializer}")
    private String VALUE_SERIALIZER;

    public Map<String, Object> config() {
        Map<String, Object> conf = new HashMap<>();
        conf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        conf.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        conf.put(ProducerConfig.ACKS_CONFIG, ACKS);
        conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER);
        conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER);
        return conf;
    }

    public ProducerFactory<Object, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(config());
    }

    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
