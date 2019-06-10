# kafka

## 基础模型



![1560125642172](assets/1560125642172.png)

## 安装

- 安装包下载

```shell
wget http://mirror.bit.edu.cn/apache/kafka/2.2.1/kafka_2.11-2.2.1.tgz 
tar -xvzf kafka_2.11-2.2.1.tgz      
```

- 修改配置暂时只需要修改zookeeper.connect

  ```shell
  vim config/server.properties 
  ```

  ```
  zookeeper.connect=192.168.1.215:2181
  ```

  ![1560128976529](assets/1560128976529.png)

- 启动

  ```shell
  bin/kafka-server-start.sh config/server.properties  
  ```

- 创建 topic

  ```shell
  bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
  ```

- 查看topic

  ```shell
  bin/kafka-topics.sh --list --bootstrap-server localhost:9092
  ```

- 发消息

  ```shell
  bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
  ```

- 客户端接收消息

  ```shell
  bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
  ```

## kafka-clients

```xml
<dependency>
  <groupId>org.apache.kafka</groupId>
  <artifactId>kafka-clients</artifactId>
  <version>1.0.0</version>
</dependency>
```

### KafkaProducer

- 两种初始化方式均需要传入一个配置

```java
public KafkaProducer(Map<String, Object> configs) {
    this((ProducerConfig)(new ProducerConfig(configs)), (Serializer)null, (Serializer)null);
}
```

```java
public KafkaProducer(Properties properties) {
    this(new ProducerConfig(properties), null, null);
}
```



- 服务发送端

```java
public class KafkaProducerDemo extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;

    public KafkaProducerDemo(
            KafkaProducer<Integer, String> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 连接到那一台kafka 可以填写多个用","分割
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.57.1:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo-java");
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");// 序列化手段
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");// 序列化手段
        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);

        KafkaProducerDemo test = new KafkaProducerDemo(producer, "test");
        test.start();

    }

    @Override
    public void run() {
        int n = 0;
        while (n < 50) {
            String msg = "msg_" + n;
            n++;

            producer.send(new ProducerRecord<Integer, String>(topic, msg));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
```

- 服务消费端