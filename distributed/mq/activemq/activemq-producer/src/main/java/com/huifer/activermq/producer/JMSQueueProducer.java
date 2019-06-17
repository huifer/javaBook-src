package com.huifer.activermq.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * <p>Title : JMSQueueProducer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-17
 */
public class JMSQueueProducer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://192.168.1.108:61616");
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue testQueue = session.createQueue("testQueue");
        MessageProducer producer = session.createProducer(testQueue);
        TextMessage message = session.createTextMessage("hello activemq");
        producer.send(message);

        connection.close();

    }

}
