//package com.huifer.zk.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//import java.util.concurrent.ConcurrentSkipListSet;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
///**
// * <p>Title : FindServerController </p>
// * <p>Description : 服务查找</p>
// *
// * @author huifer
// * @date 2019-05-29
// */
//@RestController
//@EnableScheduling
//public class FindServerController {
//
//    /**
//     * 用来发送请求
//     */
//    @Autowired
//    private RestTemplate restTemplate2;
//    /**
//     * 服务发现
//     */
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    /**
//     * 查询的服务名称
//     */
//    @Value("${find.server.name}")
//    private String findServerAppName;
//
//    private volatile Set<String> targetUrls = new ConcurrentSkipListSet<>();
//    private volatile HashMap<String, Set<String>> allServerUrls = new HashMap<>();
//
//    /**
//     * 创建restTemplate
//     */
//    @Bean
//    public RestTemplate restTemplate2() {
//        return new RestTemplate();
//    }
//
//    @GetMapping("/getsay")
//    public String getSay(@RequestParam String message) {
//        List<String> targetUrls = new ArrayList<>(this.targetUrls);
//
//        int index = new Random().nextInt(targetUrls.size());
//        String s = targetUrls.get(index);
//        System.out.println("当前请求地址 : " + s);
//        String forObject = restTemplate2.getForObject(s + "/say?message=" + message, String.class);
//
//        return forObject;
//    }
//
//
//    @GetMapping("/{service}/getsay")
//    public String getSayService(
//            @PathVariable String service,
//            @RequestParam String message) {
//        List<String> targetUrls = new ArrayList<>(this.allServerUrls.get(service));
//
//        if (targetUrls.size() == 1) {
//            String forObject = restTemplate2
//                    .getForObject(targetUrls.get(0) + "/say?message=" + message, String.class);
//            return forObject;
//
//        } else {
//
//            int index = new Random().nextInt(targetUrls.size());
//            String s = targetUrls.get(index);
//            System.out.println("当前请求地址 : " + s);
//            String forObject = restTemplate2
//                    .getForObject(s + "/say?message=" + message, String.class);
//
//            return forObject;
//        }
//    }
//
//
//    @Scheduled(fixedRate = 10 * 1000)
//    @Lazy(false)
//    public void updateOtherServer() {
//
//        HashMap<String, Set<String>> oldAllServerUrls = this.allServerUrls;
//        HashMap<String, Set<String>> newAllServerUrls = new HashMap<>();
//
//        discoveryClient.getServices().forEach(
//                s -> {
//                    List<ServiceInstance> instances = discoveryClient.getInstances(s);
//                    Set<String> collect = instances.stream().map(
//                            server ->
//                                    server.isSecure() ? "https://" + server.getHost() + ":" + server
//                                            .getPort() :
//                                            "http://" + server.getHost() + ":" + server.getPort()
//
//                    ).collect(Collectors.toSet());
//                    newAllServerUrls.put(s, collect);
//                }
//        );
//
//        this.allServerUrls = newAllServerUrls;
//        oldAllServerUrls.clear();
//    }
//
//
//    /**
//     * 十秒钟查询一次服务,单一服务
//     */
//    @Scheduled(fixedRate = 10 * 1000)
//    @Lazy(false)
//    public void updateTargetUrls() {
//        System.out.println(System.currentTimeMillis() + "当前总共有 " + targetUrls.size() + "个服务被发现");
//
//        Set<String> oldTargetUrls = this.targetUrls;
//        Set<String> newTargetUrls = new HashSet<>();
//        // 获取 http://host:port 路由地址
//        newTargetUrls.addAll(
//                discoveryClient.getInstances(findServerAppName).stream().map(
//                        serviceInstance ->
//                                serviceInstance.isSecure() ?
//                                        "https://" + serviceInstance.getHost() + ":"
//                                                + serviceInstance
//                                                .getPort()
//                                        : "http://" + serviceInstance.getHost() + ":"
//                                                + serviceInstance
//                                                .getPort()
//                ).collect(Collectors.toSet())
//        );
//
//        this.targetUrls = newTargetUrls;
//        System.out.println("跟新后存在" + targetUrls.size() + "个服务");
//        targetUrls.forEach(
//                s -> {
//                    System.out.println(s);
//                }
//        );
//        oldTargetUrls.clear();
//    }
//
//
//}
