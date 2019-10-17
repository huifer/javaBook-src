package com.huifer.kafka.core.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.concurrent.Future;


@Slf4j
@ToString
public class KafkaProducerWithCore {

    @Getter
    @Setter
    private Properties properties;
    @Getter
    @Setter
    private String propertiesFileName;
    @Getter
    @Setter
    private String defaultTopic;
    @Getter
    private KafkaProducer producer;


    /**
     * 初始化
     *
     * @param propertiesFileName 配置文件名称
     * @param defaultTopic       默认topic
     */
    public KafkaProducerWithCore(String propertiesFileName, String defaultTopic) {
        this.propertiesFileName = propertiesFileName;
        this.defaultTopic = defaultTopic;
        init();
    }


    /**
     * 初始化
     *
     * @param properties   配置信息
     * @param defaultTopic 默认topic
     */
    public KafkaProducerWithCore(Properties properties, String defaultTopic) {
        this.properties = properties;
        this.defaultTopic = defaultTopic;
    }

    public KafkaProducerWithCore() {
    }

    protected void init() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            log.error("kafka-producer配置文件不存在,文件名={}", propertiesFileName);
            throw new IllegalArgumentException("配置文件不存在");
        }
        log.info("kafka-producer配置内容={}", properties);
        producer = new KafkaProducer<String, String>(properties);
    }

    public void send(String msg) {
        this.send2Topic(null, msg);
    }

    /**
     * 发送字符串对象
     *
     * @param topic topic
     * @param msg   消息{@link String}
     */
    public void send2Topic(String topic, String msg) {
        if (msg == null) {
            log.error("消息不可为空");
            return;
        }
        if (topic == null) {
            topic = defaultTopic;
        }
        Future<org.apache.kafka.clients.producer.RecordMetadata> send = producer.send(new ProducerRecord<String, String>(topic, msg));
        log.info("发送后返回={}", send);
    }

    public void send(String key, String msg) {
        this.send2Topic(null, key, msg);
    }

    /**
     * 发送键值对消息
     *
     * @param topic
     * @param key
     * @param msg
     */
    public void send2Topic(String topic, String key, String msg) {
        if (msg == null) {
            log.error("消息不可为空");
            return;
        }
        if (topic == null) {
            topic = defaultTopic;
        }
        Future<org.apache.kafka.clients.producer.RecordMetadata> send = producer.send(new ProducerRecord<>(topic, key, msg));
        log.info("发送后返回={}", send);
    }

    public void send(Collection msg) {
        this.send2Topic(null, msg);
    }

    /**
     * 发送collection 消息
     *
     * @param topic
     * @param msg
     */
    public void send2Topic(String topic, Collection msg) {
        if (msg == null) {
            log.error("消息不可为空");
            return;
        }
        if (topic == null) {
            topic = defaultTopic;
        }
        if (msg.isEmpty()) {
            log.error("消息不可为空");
            return;
        }
        Future<org.apache.kafka.clients.producer.RecordMetadata> send = producer.send(new ProducerRecord<>(topic, msg));
        log.info("发送后返回={}", send);
    }

    public void send(Map msg) {
        this.send2Topic(null, msg);
    }

    /**
     * 发送map对象
     *
     * @param topic topic
     * @param msg   消息{@link java.util.Map}
     */
    public void send2Topic(String topic, Map msg) {
        if (msg == null) {
            log.error("消息不可为空");
            return;
        }
        if (topic == null) {
            topic = defaultTopic;
        }

        Future send = producer.send(new ProducerRecord<>(topic, msg));
        log.info("发送后返回={}", send);
    }

    //////////////////Beans
    public <T> void sendBean(T bean) {
        sendBean2Topic(null, bean);
    }

    public <T> void sendBean2Topic(String topic, T bean) {
        send2Topic(topic, JSON.toJSONString(bean));
    }

    public <T> void sendBean2Topic(String topic, String key, T bean) {
        send2Topic(topic, key, JSON.toJSONString(bean));
    }

    ///////////////////////


    //////////////////JsonObject

    public <T> void sendBeans2Topic(String topic, Collection<T> beans) {
        Collection<String> beanStrs = new ArrayList<>();
        for (T bean : beans) {
            beanStrs.add(JSON.toJSONString(bean));
        }
        send2Topic(topic, beanStrs);
    }

    public <T> void sendBeans2topic(Collection<T> beans) {
        Collection<String> beanStrs = new ArrayList<>();
        for (T bean : beans) {
            beanStrs.add(JSON.toJSONString(bean));
        }

        this.send(beanStrs);
    }

    public <T> void sendBeans2topic(String topic, Map<String, T> beans) {
        Map<String, String> beansStr = new HashMap<>();
        for (Map.Entry<String, T> entry : beans.entrySet()) {
            beansStr.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
        }

        this.send2Topic(topic, beansStr);
    }

    public void sendJsonObject(JSONObject jsonObject) {
        this.sendJsonObject2Topic(null, jsonObject);
    }

    public void sendJsonObject2Topic(String topic, JSONObject jsonObject) {
        this.send2Topic(topic, jsonObject.toJSONString());
    }

    public void sendJsonObject2Topic(String topic, String key, JSONObject jsonObject) {
        this.send2Topic(topic, key, jsonObject.toJSONString());
    }

    public void sendObjects(JSONArray jsonArray) {
        sendObjects2Topic(null, jsonArray);
    }

    ///////////////////////

    public void sendObjects2Topic(String topicName, JSONArray jsonArray) {
        send2Topic(topicName, jsonArray.toJSONString());
    }

    public void sendObjects(Map<String, JSONObject> jsonObjects) {
        sendObjects2Topic(null, jsonObjects);
    }

    public void sendObjects2Topic(String topic, Map<String, JSONObject> jsonObjects) {
        Map<String, String> objs = new HashMap<>();
        for (Map.Entry<String, JSONObject> entry : jsonObjects.entrySet()) {
            objs.put(entry.getKey(), entry.getValue().toJSONString());
        }

        send2Topic(topic, objs);
    }

    public void close() {
        producer.close();
    }

}
