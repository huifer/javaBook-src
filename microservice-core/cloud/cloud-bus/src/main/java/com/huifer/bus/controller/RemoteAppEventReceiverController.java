package com.huifer.bus.controller;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Title : RemoteAppEventReceiverController </p>
 * <p>Description : 远程事件接收器</p>
 *
 * @author huifer
 * @date 2019-06-03
 */
@RestController
public class RemoteAppEventReceiverController implements
        ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @PostMapping("/receive/remote/event")
    public String receive(@RequestBody Map<String, Object> data) {
        // 事件发送者
        Object sender = data.get("sender");
        // 事件的数据内容
        String msg = (String) data.get("value");
        // 事件类型
        String type = (String) data.get("type");
        // 接收成功后 返回到本地

        publisher.publishEvent(new SenderRemoteAppEvent(sender, msg));
        return "接收成功";
    }

    @EventListener
    public void onEvent(SenderRemoteAppEvent event) {
        System.out.println("接收事件源" + event + ", 具体消息： " + event.getSender());
    }

    private static class SenderRemoteAppEvent extends ApplicationEvent {

        private String sender;

        public SenderRemoteAppEvent(Object source, String sender) {
            super(source);
            this.sender = sender;
        }

        public String getSender() {
            return sender;
        }
    }

}
