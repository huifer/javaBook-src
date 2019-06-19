# 数据类型kafka

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

## 集群搭建

- 注册到同一个zookeeper上，修改`server.properties`，zookeeper如果是集群使用逗号(`,`)分割

  ```properties
  zookeeper.connect=192.168.1.108:2181 #填写zookeeper地址
  broker.id=2 # 集群中唯一
  listeners=PLAINTEXT://192.168.1.108:9092# 填写本机ip地址
  ```

  启动

  ` bin/kafka-server-start.sh -daemon config/server.properties `

  ![1560907993793](assets/1560907993793.png)

  - leader选举最早启动的`brokerid=1`

    ![1560908068419](assets/1560908068419.png)





##  KafkaOffsetMonitor

> **KafkaOffsetMonitor** 是有由Kafka开源社区提供的一款Web管理界面，这个应用程序用来实时监控Kafka服务的Consumer以及它们所在的Partition中的Offset，可以浏览当前的消费者组，查看每个Topic的所有Partition的当前消费情况，浏览查阅Topic的历史消费信息等

github：<https://github.com/quantifind/KafkaOffsetMonitor>

```shell
java -cp KafkaOffsetMonitor-assembly-0.2.1.jar \
     com.quantifind.kafka.offsetapp.OffsetGetterWeb \
     --offsetStorage kafka
     --zk zk-server1,zk-server2 \
     --port 8080 \
     --refresh 10.seconds \
     --retain 2.days
```

参数说明

- **offsetStorage** ：在`zookeeper`, `kafka` , `storm`选择
- **zk**：zookeeper主机地址，多个地址用逗号(`,`)分割
- **port**：启动后占用端口
- **refresh**：应用程序在数据库中刷新和存储点的频率 
- **retain**： 在db中保留多长时间 
- **dbName** ：数据库名称 ，默认`offsetapp`

### 脚本编写

```shell
#! /bin/bash
java -Xms128M -Xmx256M -Xss1024K -XX:PermSize=128m -XX:MaxPermSize=256m -cp ./KafkaOffsetMonitor-assembly-0.2.1.jar com.quantifind.kafka.offsetapp.OffsetGetterWeb --zk 127.0.0.1:2181 --port 8086 --refresh 10.seconds --retain 2.days 1> kakfa.offset.monitor.logs.stdout.log 2>kafka.offset.monitor.logs.stderr.log &

```

![1560905017527](assets/1560905017527.png)







## 术语

### 消息生产者

> 英文：produce，消息的产生源头，负责生产消息并发送给Kafka

### 消息消费者

> 英文：Consumer，消息的使用方，负责消费Kafka服务器上的消息

### 主题

> 英文：Topic，由用户定义并配置Kafka服务器，用于建立生产者和消息者之间的订阅关系。

### 订阅关系

> 生产者发送消息到指定的主题(Topic)，消息消费者从这个主题(Topic)下消费消息(获取)

### 消息分区

> 英文：Partition，一个Topic下分多个分区。

### Broker

> Kafka的服务器，用户存储消息，Kafka集群中的一台或堕胎服务器统称Broker

### 消费者分组

> 英文：Group，用户归组同类消费者，在Kafka中，多个消费者可以消费共同一个主题(Topic)下的消息，每个消费者消费其中的消息，这些消费者组成一个分组，并且拥有一个分组名称。

### Offset

> 消息存储在Kafka的Broker上，消费者拉取消息数据的过程需要直到消息在文件中的偏移量。





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

- 配置相关`org.apache.kafka.clients.producer.ProducerConfig`

  相关文档:<http://kafka.apache.org/documentation.html#producerconfigs>

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

  ```java
  public class KafkaConsumerDemo extends Thread {
  
      private final KafkaConsumer kafkaConsumer;
      private final String topic;
      public KafkaConsumerDemo(String topic) {
          Properties properties = new Properties();
          properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.57.1:9092");
          properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo-java");
          properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
          properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
          properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                  "org.apache.kafka.common.serialization.IntegerDeserializer");
          properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                  "org.apache.kafka.common.serialization.StringDeserializer");
  
          kafkaConsumer = new KafkaConsumer<>(properties);
          kafkaConsumer.subscribe(Collections.singletonList(topic));
          this.topic = topic;
      }
  
      public static void main(String[] args) {
          new KafkaConsumerDemo("test").start();
      }
  
      @Override
      public void run() {
          while (true) {
              ConsumerRecords<Integer, String> poll = kafkaConsumer.poll(1000);
              for (ConsumerRecord<Integer, String> record : poll) {
                  System.out.println("接收消息：" + record.value());
              }
  
          }
      }
  
  }
  ```

## `ProducerConfig`

- `org.apache.kafka.clients.producer.ProducerConfig`
- 相关文档:<http://kafka.apache.org/documentation.html#producerconfigs>

- 默认配置

```java
static {
    CONFIG = new ConfigDef().define(BOOTSTRAP_SERVERS_CONFIG, Type.LIST, Importance.HIGH, CommonClientConfigs.BOOTSTRAP_SERVERS_DOC)
                            .define(BUFFER_MEMORY_CONFIG, Type.LONG, 32 * 1024 * 1024L, atLeast(0L), Importance.HIGH, BUFFER_MEMORY_DOC)
                            .define(RETRIES_CONFIG, Type.INT, 0, between(0, Integer.MAX_VALUE), Importance.HIGH, RETRIES_DOC)
                            .define(ACKS_CONFIG,
                                    Type.STRING,
                                    "1",
                                    in("all", "-1", "0", "1"),
                                    Importance.HIGH,
                                    ACKS_DOC)
                            .define(COMPRESSION_TYPE_CONFIG, Type.STRING, "none", Importance.HIGH, COMPRESSION_TYPE_DOC)
                            .define(BATCH_SIZE_CONFIG, Type.INT, 16384, atLeast(0), Importance.MEDIUM, BATCH_SIZE_DOC)
                            .define(LINGER_MS_CONFIG, Type.LONG, 0, atLeast(0L), Importance.MEDIUM, LINGER_MS_DOC)
                            .define(CLIENT_ID_CONFIG, Type.STRING, "", Importance.MEDIUM, CommonClientConfigs.CLIENT_ID_DOC)
                            .define(SEND_BUFFER_CONFIG, Type.INT, 128 * 1024, atLeast(-1), Importance.MEDIUM, CommonClientConfigs.SEND_BUFFER_DOC)
                            .define(RECEIVE_BUFFER_CONFIG, Type.INT, 32 * 1024, atLeast(-1), Importance.MEDIUM, CommonClientConfigs.RECEIVE_BUFFER_DOC)
                            .define(MAX_REQUEST_SIZE_CONFIG,
                                    Type.INT,
                                    1 * 1024 * 1024,
                                    atLeast(0),
                                    Importance.MEDIUM,
                                    MAX_REQUEST_SIZE_DOC)
                            .define(RECONNECT_BACKOFF_MS_CONFIG, Type.LONG, 50L, atLeast(0L), Importance.LOW, CommonClientConfigs.RECONNECT_BACKOFF_MS_DOC)
                            .define(RECONNECT_BACKOFF_MAX_MS_CONFIG, Type.LONG, 1000L, atLeast(0L), Importance.LOW, CommonClientConfigs.RECONNECT_BACKOFF_MAX_MS_DOC)
                            .define(RETRY_BACKOFF_MS_CONFIG, Type.LONG, 100L, atLeast(0L), Importance.LOW, CommonClientConfigs.RETRY_BACKOFF_MS_DOC)
                            .define(MAX_BLOCK_MS_CONFIG,
                                    Type.LONG,
                                    60 * 1000,
                                    atLeast(0),
                                    Importance.MEDIUM,
                                    MAX_BLOCK_MS_DOC)
                            .define(REQUEST_TIMEOUT_MS_CONFIG,
                                    Type.INT,
                                    30 * 1000,
                                    atLeast(0),
                                    Importance.MEDIUM,
                                    REQUEST_TIMEOUT_MS_DOC)
                            .define(METADATA_MAX_AGE_CONFIG, Type.LONG, 5 * 60 * 1000, atLeast(0), Importance.LOW, METADATA_MAX_AGE_DOC)
                            .define(METRICS_SAMPLE_WINDOW_MS_CONFIG,
                                    Type.LONG,
                                    30000,
                                    atLeast(0),
                                    Importance.LOW,
                                    CommonClientConfigs.METRICS_SAMPLE_WINDOW_MS_DOC)
                            .define(METRICS_NUM_SAMPLES_CONFIG, Type.INT, 2, atLeast(1), Importance.LOW, CommonClientConfigs.METRICS_NUM_SAMPLES_DOC)
                            .define(METRICS_RECORDING_LEVEL_CONFIG,
                                    Type.STRING,
                                    Sensor.RecordingLevel.INFO.toString(),
                                    in(Sensor.RecordingLevel.INFO.toString(), Sensor.RecordingLevel.DEBUG.toString()),
                                    Importance.LOW,
                                    CommonClientConfigs.METRICS_RECORDING_LEVEL_DOC)
                            .define(METRIC_REPORTER_CLASSES_CONFIG,
                                    Type.LIST,
                                    "",
                                    Importance.LOW,
                                    CommonClientConfigs.METRIC_REPORTER_CLASSES_DOC)
                            .define(MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
                                    Type.INT,
                                    5,
                                    atLeast(1),
                                    Importance.LOW,
                                    MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION_DOC)
                            .define(KEY_SERIALIZER_CLASS_CONFIG,
                                    Type.CLASS,
                                    Importance.HIGH,
                                    KEY_SERIALIZER_CLASS_DOC)
                            .define(VALUE_SERIALIZER_CLASS_CONFIG,
                                    Type.CLASS,
                                    Importance.HIGH,
                                    VALUE_SERIALIZER_CLASS_DOC)
                            /* default is set to be a bit lower than the server default (10 min), to avoid both client and server closing connection at same time */
                            .define(CONNECTIONS_MAX_IDLE_MS_CONFIG,
                                    Type.LONG,
                                    9 * 60 * 1000,
                                    Importance.MEDIUM,
                                    CommonClientConfigs.CONNECTIONS_MAX_IDLE_MS_DOC)
                            .define(PARTITIONER_CLASS_CONFIG,
                                    Type.CLASS,
                                    DefaultPartitioner.class,
                                    Importance.MEDIUM, PARTITIONER_CLASS_DOC)
                            .define(INTERCEPTOR_CLASSES_CONFIG,
                                    Type.LIST,
                                    null,
                                    Importance.LOW,
                                    INTERCEPTOR_CLASSES_DOC)
                            .define(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
                                    Type.STRING,
                                    CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL,
                                    Importance.MEDIUM,
                                    CommonClientConfigs.SECURITY_PROTOCOL_DOC)
                            .withClientSslSupport()
                            .withClientSaslSupport()
                            .define(ENABLE_IDEMPOTENCE_CONFIG,
                                    Type.BOOLEAN,
                                    false,
                                    Importance.LOW,
                                    ENABLE_IDEMPOTENCE_DOC)
                            .define(TRANSACTION_TIMEOUT_CONFIG,
                                    Type.INT,
                                    60000,
                                    Importance.LOW,
                                    TRANSACTION_TIMEOUT_DOC)
                            .define(TRANSACTIONAL_ID_CONFIG,
                                    Type.STRING,
                                    null,
                                    new ConfigDef.NonEmptyString(),
                                    Importance.LOW,
                                    TRANSACTIONAL_ID_DOC);
}
```

- 配置函数

```java
/**
 * 定义配置默认值
 * @param name          配置参数名称
 * @param type          配置类型
 * @param importance    该参数的重要性
 * @param documentation 配置文档
 * @return This ConfigDef so you can chain calls
 */
public ConfigDef define(String name, Type type, Importance importance, String documentation) {
    return define(name, type, NO_DEFAULT_VALUE, null, importance, documentation);
}
```

- 类型

  ```java
  public enum Type {
      BOOLEAN, STRING, INT, SHORT, LONG, DOUBLE, LIST, CLASS, PASSWORD
  }
  ```

- 重要性

  ```java
  public enum Importance {
      HIGH, MEDIUM, LOW
  }
  ```

- 下面为重要性高的几个参数说明

| kafka关键字          | ProducerConfig关键字                         | 含义                        | 数据类型 | 默认值                                          |
| -------------------- | :------------------------------------------- | --------------------------- | -------- | ----------------------------------------------- |
| bootstrap.servers    | `ProducerConfig#BOOTSTRAP_SERVERS_CONFIG`    | kafka集群列表               | list     |                                                 |
| metadata.max.age.ms  | ProducerConfig#METADATA_MAX_AGE_CONFIG       | 元数据最大生存时间          | long     | 5 * 60 * 1000（5分钟）                          |
| batch.size           | ProducerConfig#BATCH_SIZE_CONFIG             | 消息记录batch(批)大小限制   | int      | 16384子节                                       |
| acks                 | ProducerConfig#ACKS_CONFIG                   | 应答数设置                  | string   | "1"                                             |
| **key.serializer**   | ProducerConfig#KEY_SERIALIZER_CLASS_CONFIG   | 消息记录key的序列化类。     | CLASS    | 从`org.apache.kafka.common.serialization`下选择 |
| **value.serializer** | ProducerConfig#VALUE_SERIALIZER_CLASS_CONFIG | 消息记录中value的序列化类。 | CLASS    | 从`org.apache.kafka.common.serialization`下选择 |

## `ConsumerConfig`



- `org.apache.kafka.clients.consumer.ConsumerConfig`
- 相关文档:<http://kafka.apache.org/documentation.html#consumerconfigs>

- 默认配置

  ```java
  static {
      CONFIG = new ConfigDef().define(BOOTSTRAP_SERVERS_CONFIG,
                                      Type.LIST,
                                      Importance.HIGH,
                                      CommonClientConfigs.BOOTSTRAP_SERVERS_DOC)
                              .define(GROUP_ID_CONFIG, Type.STRING, "", Importance.HIGH, GROUP_ID_DOC)
                              .define(SESSION_TIMEOUT_MS_CONFIG,
                                      Type.INT,
                                      10000,
                                      Importance.HIGH,
                                      SESSION_TIMEOUT_MS_DOC)
                              .define(HEARTBEAT_INTERVAL_MS_CONFIG,
                                      Type.INT,
                                      3000,
                                      Importance.HIGH,
                                      HEARTBEAT_INTERVAL_MS_DOC)
                              .define(PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                                      Type.LIST,
                                      Collections.singletonList(RangeAssignor.class),
                                      Importance.MEDIUM,
                                      PARTITION_ASSIGNMENT_STRATEGY_DOC)
                              .define(METADATA_MAX_AGE_CONFIG,
                                      Type.LONG,
                                      5 * 60 * 1000,
                                      atLeast(0),
                                      Importance.LOW,
                                      CommonClientConfigs.METADATA_MAX_AGE_DOC)
                              .define(ENABLE_AUTO_COMMIT_CONFIG,
                                      Type.BOOLEAN,
                                      true,
                                      Importance.MEDIUM,
                                      ENABLE_AUTO_COMMIT_DOC)
                              .define(AUTO_COMMIT_INTERVAL_MS_CONFIG,
                                      Type.INT,
                                      5000,
                                      atLeast(0),
                                      Importance.LOW,
                                      AUTO_COMMIT_INTERVAL_MS_DOC)
                              .define(CLIENT_ID_CONFIG,
                                      Type.STRING,
                                      "",
                                      Importance.LOW,
                                      CommonClientConfigs.CLIENT_ID_DOC)
                              .define(MAX_PARTITION_FETCH_BYTES_CONFIG,
                                      Type.INT,
                                      DEFAULT_MAX_PARTITION_FETCH_BYTES,
                                      atLeast(0),
                                      Importance.HIGH,
                                      MAX_PARTITION_FETCH_BYTES_DOC)
                              .define(SEND_BUFFER_CONFIG,
                                      Type.INT,
                                      128 * 1024,
                                      atLeast(-1),
                                      Importance.MEDIUM,
                                      CommonClientConfigs.SEND_BUFFER_DOC)
                              .define(RECEIVE_BUFFER_CONFIG,
                                      Type.INT,
                                      64 * 1024,
                                      atLeast(-1),
                                      Importance.MEDIUM,
                                      CommonClientConfigs.RECEIVE_BUFFER_DOC)
                              .define(FETCH_MIN_BYTES_CONFIG,
                                      Type.INT,
                                      1,
                                      atLeast(0),
                                      Importance.HIGH,
                                      FETCH_MIN_BYTES_DOC)
                              .define(FETCH_MAX_BYTES_CONFIG,
                                      Type.INT,
                                      DEFAULT_FETCH_MAX_BYTES,
                                      atLeast(0),
                                      Importance.MEDIUM,
                                      FETCH_MAX_BYTES_DOC)
                              .define(FETCH_MAX_WAIT_MS_CONFIG,
                                      Type.INT,
                                      500,
                                      atLeast(0),
                                      Importance.LOW,
                                      FETCH_MAX_WAIT_MS_DOC)
                              .define(RECONNECT_BACKOFF_MS_CONFIG,
                                      Type.LONG,
                                      50L,
                                      atLeast(0L),
                                      Importance.LOW,
                                      CommonClientConfigs.RECONNECT_BACKOFF_MS_DOC)
                              .define(RECONNECT_BACKOFF_MAX_MS_CONFIG,
                                      Type.LONG,
                                      1000L,
                                      atLeast(0L),
                                      Importance.LOW,
                                      CommonClientConfigs.RECONNECT_BACKOFF_MAX_MS_DOC)
                              .define(RETRY_BACKOFF_MS_CONFIG,
                                      Type.LONG,
                                      100L,
                                      atLeast(0L),
                                      Importance.LOW,
                                      CommonClientConfigs.RETRY_BACKOFF_MS_DOC)
                              .define(AUTO_OFFSET_RESET_CONFIG,
                                      Type.STRING,
                                      "latest",
                                      in("latest", "earliest", "none"),
                                      Importance.MEDIUM,
                                      AUTO_OFFSET_RESET_DOC)
                              .define(CHECK_CRCS_CONFIG,
                                      Type.BOOLEAN,
                                      true,
                                      Importance.LOW,
                                      CHECK_CRCS_DOC)
                              .define(METRICS_SAMPLE_WINDOW_MS_CONFIG,
                                      Type.LONG,
                                      30000,
                                      atLeast(0),
                                      Importance.LOW,
                                      CommonClientConfigs.METRICS_SAMPLE_WINDOW_MS_DOC)
                              .define(METRICS_NUM_SAMPLES_CONFIG,
                                      Type.INT,
                                      2,
                                      atLeast(1),
                                      Importance.LOW,
                                      CommonClientConfigs.METRICS_NUM_SAMPLES_DOC)
                              .define(METRICS_RECORDING_LEVEL_CONFIG,
                                      Type.STRING,
                                      Sensor.RecordingLevel.INFO.toString(),
                                      in(Sensor.RecordingLevel.INFO.toString(), Sensor.RecordingLevel.DEBUG.toString()),
                                      Importance.LOW,
                                      CommonClientConfigs.METRICS_RECORDING_LEVEL_DOC)
                              .define(METRIC_REPORTER_CLASSES_CONFIG,
                                      Type.LIST,
                                      "",
                                      Importance.LOW,
                                      CommonClientConfigs.METRIC_REPORTER_CLASSES_DOC)
                              .define(KEY_DESERIALIZER_CLASS_CONFIG,
                                      Type.CLASS,
                                      Importance.HIGH,
                                      KEY_DESERIALIZER_CLASS_DOC)
                              .define(VALUE_DESERIALIZER_CLASS_CONFIG,
                                      Type.CLASS,
                                      Importance.HIGH,
                                      VALUE_DESERIALIZER_CLASS_DOC)
                              .define(REQUEST_TIMEOUT_MS_CONFIG,
                                      Type.INT,
                                      305000, // chosen to be higher than the default of max.poll.interval.ms
                                      atLeast(0),
                                      Importance.MEDIUM,
                                      REQUEST_TIMEOUT_MS_DOC)
                              /* default is set to be a bit lower than the server default (10 min), to avoid both client and server closing connection at same time */
                              .define(CONNECTIONS_MAX_IDLE_MS_CONFIG,
                                      Type.LONG,
                                      9 * 60 * 1000,
                                      Importance.MEDIUM,
                                      CommonClientConfigs.CONNECTIONS_MAX_IDLE_MS_DOC)
                              .define(INTERCEPTOR_CLASSES_CONFIG,
                                      Type.LIST,
                                      null,
                                      Importance.LOW,
                                      INTERCEPTOR_CLASSES_DOC)
                              .define(MAX_POLL_RECORDS_CONFIG,
                                      Type.INT,
                                      500,
                                      atLeast(1),
                                      Importance.MEDIUM,
                                      MAX_POLL_RECORDS_DOC)
                              .define(MAX_POLL_INTERVAL_MS_CONFIG,
                                      Type.INT,
                                      300000,
                                      atLeast(1),
                                      Importance.MEDIUM,
                                      MAX_POLL_INTERVAL_MS_DOC)
                              .define(EXCLUDE_INTERNAL_TOPICS_CONFIG,
                                      Type.BOOLEAN,
                                      DEFAULT_EXCLUDE_INTERNAL_TOPICS,
                                      Importance.MEDIUM,
                                      EXCLUDE_INTERNAL_TOPICS_DOC)
                              .defineInternal(LEAVE_GROUP_ON_CLOSE_CONFIG,
                                              Type.BOOLEAN,
                                              true,
                                              Importance.LOW)
                              .define(ISOLATION_LEVEL_CONFIG,
                                      Type.STRING,
                                      DEFAULT_ISOLATION_LEVEL,
                                      in(IsolationLevel.READ_COMMITTED.toString().toLowerCase(Locale.ROOT), IsolationLevel.READ_UNCOMMITTED.toString().toLowerCase(Locale.ROOT)),
                                      Importance.MEDIUM,
                                      ISOLATION_LEVEL_DOC)
                              // security support
                              .define(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
                                      Type.STRING,
                                      CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL,
                                      Importance.MEDIUM,
                                      CommonClientConfigs.SECURITY_PROTOCOL_DOC)
                              .withClientSslSupport()
                              .withClientSaslSupport();
  
  }
  ```

  - 内容与`ProducerConfig`类似



| kafka关键字             | ConsumerConfig关键字                           | 含义                                                         | 数据类型 | 默认值 |
| ----------------------- | ---------------------------------------------- | ------------------------------------------------------------ | -------- | ------ |
| bootstrap.servers       | ConsumerConfig#BOOTSTRAP_SERVERS_CONFIG        | kafka集群列表                                                | LIST     |        |
| enable.auto.commit      | ConsumerConfig#ENABLE_AUTO_COMMIT_CONFIG       | 如果设为true，消费者的偏移量会定期在后台提交。               | BOOLEAN  | true   |
| auto.commit.interval.ms | ConsumerConfig#AUTO_COMMIT_INTERVAL_MS_CONFIG  | 自动提交offset到zookeeper的时间间隔                          | INT      | 5000   |
| key.deserializer        | ConsumerConfig#KEY_DESERIALIZER_CLASS_CONFIG   | 实现了Deserializer的key的反序列化类                          | CLASS    |        |
| value.deserializer      | ConsumerConfig#VALUE_DESERIALIZER_CLASS_CONFIG | 实现了Deserializer的value的反序列化类                        | CLASS    |        |
| auto.offset.reset       | ConsumerConfig#AUTO_OFFSET_RESET_CONFIG        | 当kafka的初始偏移量没了，或者当前的偏移量不存在的情况下，应该怎么办？下面有几种策略：earliest（将偏移量自动重置为最初的值）、latest（自动将偏移量置为最新的值）、none（如果在消费者组中没有发现前一个偏移量，就向消费者抛出一个异常）、anything else（向消费者抛出异常） | STRING   | latest |





