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
public class JMSPersistenceTopicReceiver {

    public static final String PS = "Persistence-1";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://192.168.1.108:61616");
        Connection connection = connectionFactory.createConnection();
        connection.setClientID(PS);
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic testTopic = session.createTopic("testTopic");
        MessageConsumer consumer = session.createDurableSubscriber(testTopic, PS);
        TextMessage msg = (TextMessage) consumer.receive();
        System.out.println(msg.getText());

        session.commit();
        session.close();
        consumer.close();
    }
}
