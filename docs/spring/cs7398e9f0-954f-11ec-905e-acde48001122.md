
## spring-boot 整合

### 消息提供端

- `application.properties`

  ```properties
  server.port=8999
  
  
  
  kafka.producer.bootstrap-servers=192.168.1.108:9092,192.168.1.106:9092,192.168.1.106:9092
  kafka.producer.client.id=spring-boot-kafka-demo
  kafka.producer.acks=-1
  kafka.producer.key.serializer=org.apache.kafka.common.serialization.IntegerSerializer
  kafka.producer.value.serializer=org.apache.kafka.common.serialization.StringSerializer
  ```

- KafkaProducerConfig

  ```java
  package com.huifer.springboot.kafka.producer.bean;
  
  import java.util.HashMap;
  import java.util.Map;
  import org.apache.kafka.clients.producer.ProducerConfig;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.kafka.annotation.EnableKafka;
  import org.springframework.kafka.core.DefaultKafkaProducerFactory;
  import org.springframework.kafka.core.KafkaTemplate;
  import org.springframework.kafka.core.ProducerFactory;
  
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
  ```

- 测试类

  ```java
  package com.huifer.springboot.kafka.producer.bean;
  
  import org.apache.kafka.clients.producer.ProducerRecord;
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.kafka.core.KafkaTemplate;
  import org.springframework.test.context.junit4.SpringRunner;
  import org.springframework.util.concurrent.ListenableFuture;
  
  @RunWith(SpringRunner.class)
  @SpringBootTest
  public class KafkaProducerConfigTest {
      @Autowired
      private KafkaTemplate kafkaTemplate;
  
      @Test
      public void testSend() {
          ListenableFuture send = kafkaTemplate
                  .send(new ProducerRecord<String, String>("hello-spring-boot-kafka", "hhh"));
          System.out.println(send);
      }
  }
  
  ```



### 消息消费端

- `application.properties`

```properties
server.port=9000



kafka.consumer.bootstrap-servers=192.168.1.108:9092,192.168.1.106:9092,192.168.1.106:9092
kafka.consumer.group.id=spring-boot-kafka-consumer
kafka.consumer.enable.auto.commit=true
kafka.consumer.auto.commit.interval.ms=1000
kafka.consumer.key.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer
kafka.consumer.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
kafka.consumer.auto.offset.reset=earliest

```

- `MessageListener`

  ```java
  public class KafkaConsumerMessageListener {
  
      @KafkaListener(topics = {"hello-spring-boot-kafka"})
      public void listen(ConsumerRecord<?, ?> record) {
          System.out.println("key = " + record.key() + "\t" + "value = " + record.value());
      }
  
  }
  ```

- `KafkaCounsumerConfig`

  ```java
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
  ```



### 测试

```java
@RestController
public class ProucerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/test")
    public String sendMsg() {
        kafkaTemplate.send("hello-spring-boot-kafka", "hello");
        return "ok";
    }

}
```
