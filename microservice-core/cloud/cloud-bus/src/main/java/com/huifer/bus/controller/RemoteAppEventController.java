package com.huifer.bus.controller;

import com.huifer.bus.event.RemoteAppEvent;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : RemoteAppEventController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-03
 */
@RestController
public class RemoteAppEventController implements ApplicationEventPublisherAware {


    @Value("${spring.application.name}")
    public String currentAppName;

    private ApplicationEventPublisher publisher;
    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/send/remote/event")
    public String sendEvent(@RequestParam String message) {
        publisher.publishEvent(message);
        return "发送完毕";
    }


    /**
     * 对一个集群发送消息
     * http://localhost:9010/send/remote/event/cloud-bus?message=22222
     */
    @PostMapping("/send/remote/event/{appName}")
    public String sendAppCluster(@PathVariable("appName") String appName,
            @RequestParam String message
    ) {
        List<ServiceInstance> instances = discoveryClient.getInstances(appName);
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(message, currentAppName, appName,
                instances);
        // 发送事件给自己
        publisher.publishEvent(remoteAppEvent);
        return "ok";
    }

    /**
     * 给具体的服务发消息
     * http://localhost:9010/send/remote/event/cloud-bus/192.168.1.215/9010?data=1
     */
    @PostMapping("/send/remote/event/{appName}/{ip}/{port}")
    public String sendAppInterceptors(@PathVariable("appName") String appName,
            @PathVariable("ip") String ip,
            @PathVariable("port") int port,
            @RequestParam String data) {
        ServiceInstance instances = new DefaultServiceInstance(appName, ip,
                port, false);

        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data, currentAppName, appName,
                Arrays.asList(instances));

        publisher.publishEvent(remoteAppEvent);
        return "sendAppInterceptors  ok";
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


}
