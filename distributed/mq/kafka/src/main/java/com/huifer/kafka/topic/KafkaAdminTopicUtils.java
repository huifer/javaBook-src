package com.huifer.kafka.topic;

import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * <p>Title : KafkaAdminTopicUtils </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-25
 */
public class KafkaAdminTopicUtils {

    public static void main(String[] args) {
//        createTopic("192.168.1.106:9092", "test-admin-topic");
        describeTopic("192.168.1.106:9092", "test-admin-topic");
//        deleteTopic("192.168.1.106:9092", "test-admin-topic");
    }


    public static void deleteTopic(String server, String topic) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(properties);
        DeleteTopicsResult deleteTopicsResult = client.deleteTopics(Collections.singleton(topic));
        try {
            deleteTopicsResult.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }


    /**
     * 创建主题
     */
    public static void createTopic(String server, String topic) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(properties);

        // 分区数，副本因子
        NewTopic newTopic = new NewTopic(topic, 4, (short) 1);

        CreateTopicsResult topics = client.createTopics(Collections.singleton(newTopic));
        try {

            Void aVoid = topics.all().get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }


    /**
     * 查看topic的相关信息
     */
    public static void describeTopic(String server, String topic) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        AdminClient client = AdminClient.create(properties);
        DescribeTopicsResult describeTopicsResult = client
                .describeTopics(Collections.singleton(topic));
        try {
            Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all()
                    .get();
            TopicDescription topicDescription = stringTopicDescriptionMap.get(topic);
            stringTopicDescriptionMap.forEach((k, v) -> {
                System.out.println(k + " : " + v);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }


}
