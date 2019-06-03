package com.huifer.bus.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title : HttpRemoteAppEventListener </p>
 * <p>Description : 将事件数据发送HTTP请求</p>
 *
 * @author huifer
 * @date 2019-06-03
 */
@Component
public class HttpRemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {


    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public void onApplicationEvent(RemoteAppEvent event) {

        Object source = event.getSource();
        // 元数据信息
        List<ServiceInstance> serviceInterceptors = event.getServiceInterceptors();
        String appName = event.getAppName();

        for (ServiceInstance server : serviceInterceptors) {
            String rootURL = server.isSecure() ?
                    "https://" + server.getHost() + ":" + server.getPort() :
                    "http://" + server.getHost() + ":" + server.getPort();

            String url = rootURL + "/receive/remote/event";
            Map<String, Object> data = new HashMap<>();
            data.put("sender", event.getSender());
            data.put("value", source);
            data.put("type", RemoteAppEvent.class.getSimpleName());
            // 发送具体消息
            String postForObject = restTemplate.postForObject(url, data, String.class);

            System.out.println("onApplicationEvent :" + postForObject);

        }
    }
}
