//package com.huifer.zk.loadbalance;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.util.StringUtils;
//
///**
// * <p>Title : LoadBalanceRequestInterceptor </p>
// * <p>Description : </p>
// *
// * @author huifer
// * @date 2019-05-29
// */
//public class LoadBalanceRequestInterceptor implements ClientHttpRequestInterceptor {
//
//    private Map<String, Set<String>> targetUrls = new HashMap<>();
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Scheduled(fixedRate = 10 * 1000)
//    @Lazy(false)
//    public void updateOtherServer() {
//
//        Map<String, Set<String>> oldAllServerUrls = this.targetUrls;
//        Map<String, Set<String>> newAllServerUrls = new HashMap<>();
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
//        this.targetUrls = newAllServerUrls;
//        oldAllServerUrls.clear();
//    }
//
//
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
//            ClientHttpRequestExecution execution) throws IOException {
//        // uri = {application.name}/uri
//        // "/zk-Discovery/say?messagehello"
//        URI requestURI = request.getURI();
//        String requestURIPath = requestURI.getPath();
//        String[] strings = StringUtils.split(requestURIPath.substring(1), "/");
//
//        String appname = strings[0];
//        String uri = strings[1];
//
//        List<String> targetUrls = new ArrayList<String>(this.targetUrls.get(appname));
//        int index = new Random().nextInt(targetUrls.size());
//        String targetURL = targetUrls.get(index);
//
//        // 最终服务器地址
//        String resURL = targetURL + "/" + uri + "?" + requestURI.getQuery();
//        // 发送请求，虚假响应内容
//        URL url = new URL(resURL);
//        InputStream responseBody = url.openConnection().getInputStream();
//        HttpHeaders headers = new HttpHeaders();
//        SimpleClientHttpResponse clientHttpResponse = new SimpleClientHttpResponse(responseBody,
//                headers);
//        return clientHttpResponse;
//    }
//
//
//    private static class SimpleClientHttpResponse implements ClientHttpResponse {
//
//        private InputStream body;
//        private HttpHeaders headers;
//
//        public SimpleClientHttpResponse() {
//        }
//
//        public SimpleClientHttpResponse(InputStream body, HttpHeaders headers) {
//            this.body = body;
//            this.headers = headers;
//        }
//
//        @Override
//        public HttpStatus getStatusCode() throws IOException {
//            return HttpStatus.OK;
//        }
//
//        @Override
//        public int getRawStatusCode() throws IOException {
//            return 200;
//        }
//
//        @Override
//        public String getStatusText() throws IOException {
//            return "200";
//        }
//
//        @Override
//        public void close() {
//
//        }
//
//        @Override
//        public InputStream getBody() throws IOException {
//            return body;
//        }
//
//        @Override
//        public HttpHeaders getHeaders() {
//            return headers;
//        }
//    }
//
//
//}
