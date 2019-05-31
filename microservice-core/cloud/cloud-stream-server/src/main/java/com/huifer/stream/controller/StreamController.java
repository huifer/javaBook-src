package com.huifer.stream.controller;

import com.huifer.stream.stream.SimpleServer;
import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * <p>Title : StreamController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-31
 */
@Component
public class StreamController {

    @Autowired
    private SimpleServer simpleServer;


    @PostConstruct
    public void init() {
        // SubscribableChannel获取
        SubscribableChannel huifer = simpleServer.huifer();
        huifer.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                MessageHeaders headers = message.getHeaders();
                String o = (String) headers.get("charset-encoding");
                try {
                    System.out.println("消息内容为 " + new String((byte[]) message.getPayload(), o));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @StreamListener("huifer")
    public void huiferMessage(String msg) {
        System.out.println("huiferMessage : " + msg);
    }


}
