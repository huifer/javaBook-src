package com.huifer.activermq.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * <p>Title : JMSQueueReceiver </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-17
 */
public class JMSTopicReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://192.168.1.108:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("testTopic");
        MessageConsumer consumer = session.createConsumer(destination);
        TextMessage msg = (TextMessage) consumer.receive();
        System.out.println(msg.getText());

        session.commit();
        session.close();
        consumer.close();
    }
}
