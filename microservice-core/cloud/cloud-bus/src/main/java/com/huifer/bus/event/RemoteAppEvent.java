package com.huifer.bus.event;

import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationEvent;

/**
 * <p>Title : RemoteAppEvent </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-03
 */
public class RemoteAppEvent extends ApplicationEvent {


    /**
     * 应用名称
     */
    private String appName;

    /**
     * 发送内容
     */
    private String sender;

    public RemoteAppEvent(Object source , String appName, String sender,
            List<ServiceInstance> serviceInterceptors) {
        super(source);
        this.appName = appName;
        this.sender = sender;
        this.serviceInterceptors = serviceInterceptors;
    }

    /**
     * 应用实例
     */
    private List<ServiceInstance> serviceInterceptors;

    public String getSender() {
        return sender;
    }


    public String getAppName() {
        return appName;
    }

    public List<ServiceInstance> getServiceInterceptors() {
        return serviceInterceptors;
    }
}
