package com.huifer.stream.controller;

import com.huifer.stream.stream.SimpleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

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
