package com.huifer.activermq.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * <p>Title : JMSTopicProducer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-18
 */
public class JMSTopicProducer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://192.168.1.108:61616");
        ((ActiveMQConnectionFactory)connectionFactory).setAlwaysSyncSend(true);
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic testTopic = session.createTopic("testTopic");

        MessageProducer producer = session.createProducer(testTopic);

        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        TextMessage hello_topic = session.createTextMessage("hello topic");
        producer.send(hello_topic);

        session.commit();
        session.close();
        session.rollback();
        connection.close();

    }
}
