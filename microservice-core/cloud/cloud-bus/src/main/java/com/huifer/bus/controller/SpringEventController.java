package com.huifer.bus.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : SpringEventController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-03
 */
@RestController
public class SpringEventController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @GetMapping("/send/event")
    public String sendEvent(@RequestParam String message) {
        publisher.publishEvent(message);
        return "发送完毕";
    }

    @EventListener
    public void onMessage(PayloadApplicationEvent event) {
        System.out.println( "接收的内容: " +  event.getPayload());
    }


}
