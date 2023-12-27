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
public class JMSQueueListenerReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://192.168.1.108:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue testQueue = session.createQueue("testQueue");

        MessageConsumer consumer = session.createConsumer(testQueue);
        MessageListener messageListener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };

        while (true) {
            consumer.setMessageListener(messageListener);
        }

    }
}
