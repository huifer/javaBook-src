# spring-cloud-bus

## spring 事件

<https://docs.spring.io/spring/docs/5.1.7.RELEASE/spring-framework-reference/core.html#context-functionality-events>



### 内建事件

- `ContextRefreshedEvent`
  - `org.springframework.context.ConfigurableApplicationContext#refresh`

- `ContextStartedEvent`
  - `org.springframework.context.Lifecycle#start`

- `ContextStoppedEvent`
  - `org.springframework.context.Lifecycle#stop`

- `ContextClosedEvent`
  - `org.springframework.context.ConfigurableApplicationContext#close`

- `RequestHandledEvent`





```java
public class SpringEvent {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.addApplicationListener((ApplicationListener<ContextRefreshedEvent>) e -> {
            System.out.println(" ContextRefreshedEvent 监听器");
        });

        context.addApplicationListener((ApplicationListener<ContextClosedEvent>) e -> {
            System.out.println(" ContextClosedEvent 监听器");
        });

        context.refresh();
        context.close();
    }

}
```



```java
public class SpringAnnotationEvent {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationEvent.class);

        context.refresh();
        context.publishEvent(new MyEvent("hello "));

        context.close();

    }

    @EventListener
    public void onMessage(MyEvent event) {
        System.out.println("监听到事件 " + event.getSource());
    }

    private static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }

}
```

```java
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
```

## spring cloud 整合事件

- 配置

  ```properties
  server.port=9010
  spring.application.name=cloud-bus
  ```

- ApplicationEvent

  ```java
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
  ```

- ApplicationListener

  ```java
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
  
  ```

- 发送消息的controller

  ```java
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
  ```

- 接收消息的controller

  ```java
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
  ```

- 启动项

  ```java
  @SpringBootApplication
  @EnableDiscoveryClient
  public class BusApp {
  
      public static void main(String[] args) {
          SpringApplication.run(BusApp.class, args);
      }
  
  }
  ```



```sequence
用户1 --> RemoteAppEventController:发送响应 
RemoteAppEventController --> HttpRemoteAppEventListener: 接收响应内容，转发给具体的操作类
HttpRemoteAppEventListener --> RemoteAppEventReceiverController: 处理完成具体的数据返回
```





