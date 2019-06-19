package com.huifer.springboot.kafka.consumer.bean;

import com.huifer.springboot.kafka.consumer.service.KafkaConsumerMessageListener;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/**
 * <p>Title : KafkaConsumerConfig </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-19
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${kafka.consumer.bootstrap-servers}")
    private String bootstrap_servers;
    @Value("${kafka.consumer.group.id}")
    private String group_id;
    @Value("${kafka.consumer.enable.auto.commit}")
    private String enable_auto_commit;
    @Value("${kafka.consumer.auto.commit.interval.ms}")
    private String auto_commit_interval_ms;
    @Value("${kafka.consumer.key.deserializer}")
    private String key_deserializer;
    @Value("${kafka.consumer.value.deserializer}")
    private String value_deserializer;
    @Value("${kafka.consumer.auto.offset.reset}")
    private String reset;

    public Map config() {
        Map<String, Object> conf = new HashMap<>();
        conf.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        conf.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
        conf.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit);
        conf.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, auto_commit_interval_ms);
        conf.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key_deserializer);
        conf.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, value_deserializer);
        conf.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, reset);
        return conf;
    }


    public ConsumerFactory<Object, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(config());
    }

    @Bean
    public KafkaConsumerMessageListener listener() {
        return new KafkaConsumerMessageListener();
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }


}
