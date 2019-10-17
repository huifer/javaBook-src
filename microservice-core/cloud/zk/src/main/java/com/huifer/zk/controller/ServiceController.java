package com.huifer.zk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title : ServiceController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-29
 */
@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 获取所有的服务
     */
    @GetMapping("/services")
    public List<String> getAllServices() {
        return discoveryClient.getServices();
    }

    /**
     * 获取服务实例
     */
    @GetMapping("/services/instances")
    public List<String> getAllServiceInstances() {
        return discoveryClient.getInstances(applicationName).stream().map(
                serviceInstance -> {
                    return serviceInstance.getServiceId() + "-" + serviceInstance.getHost() + ":"
                            + serviceInstance.getPort();
                }
        ).collect(Collectors.toList());
    }


}
