# kafka整合log4j
- 依赖
```xml
 <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
    <kafka.version>1.0.0</kafka.version>
    <log4j.version>1.2.17</log4j.version>
    <junit.version>4.12</junit.version>
  </properties>


  <dependencies>
    <dependency>
      <groupId>org.apache.kafka</groupId>
      <artifactId>kafka-clients</artifactId>
      <version>${kafka.version}</version>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>${log4j.version}</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.kafka</groupId>
      <artifactId>kafka-log4j-appender</artifactId>
      <version>2.2.1</version>
    </dependency>
  </dependencies>
```

- log4j配置文件
```properties
log4j.rootLogger=INFO,console
# for package com.demo.kafka, log would be sent to kafka appender.
log4j.logger.com.huifer.kafka.integration=DEBUG,kafka
# appender kafka
log4j.appender.kafka=org.apache.kafka.log4jappender.KafkaLog4jAppender
log4j.appender.kafka.topic=log4jtest
# multiple brokers are separated by comma ",".
log4j.appender.kafka.brokerList=192.168.1.106:9092
log4j.appender.kafka.compressionType=none
log4j.appender.kafka.syncSend=true
log4j.appender.kafka.layout=org.apache.log4j.PatternLayout
log4j.appender.kafka.layout.ConversionPattern=%d [%-5p] [%t] - [%l] %m%n
 =
# appender console
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.target=System.out
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d [%-5p] [%t] - [%l] %m%n

```
- demo
```java
public class Rn {

    public static final Logger LOGGER = Logger.getLogger(Rn.class);

    public static void main(String[] args) {
        LOGGER.info("123123123123 ");
//        LOGGER.error("123123123123 ");
//        LOGGER.warn("123123123123 ");
//        LOGGER.debug("123123123123 ");
    }

}

```
