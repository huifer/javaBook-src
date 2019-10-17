package com.huifer.stream.controller;

import com.huifer.stream.stream.SimpleClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title : RabbitController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-31
 */
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SimpleClient simpleClient;

    @GetMapping("/rb")
    public String send(@RequestParam String message) {
        rabbitTemplate.convertAndSend(message);
        return "ok  " + message;
    }

    @GetMapping("/stream")
    public boolean sendt(@RequestParam String message) {
        MessageChannel messageChannel = simpleClient.huifer();

        Map<String, Object> headers = new HashMap<>();
        headers.put("charset-encoding", "UTF-8");
        GenericMessage<String> msg = new GenericMessage<>(message, headers);
        boolean hello_ = messageChannel.send(msg);
        return hello_;
    }


}
