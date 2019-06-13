# dubbo 分布服务治理

## dubbo是什么

- 一个RPC框架

![](http://dubbo.apache.org/img/architecture.png)

文档: <http://dubbo.apache.org/zh-cn/docs/user/quick-start.html>

## 简单案例

一般情况下dubbo分为如下几个模块

- dubbo-client: 客户端
- dubbo-server: 服务端
  - server-api:接口
  - server-provider: 接口实现
    - 依赖server-api 

项目结构如下

![1560394094623](assets/1560394094623.png)

- server-api 接口

  ```java
  public interface DubboHello {
      String hello(String msg);
  } 
  ```

- server-provider接口实现

  ```java
  public class DubboHelloImpl implements DubboHello {
  
      @Override
      public String hello(String msg) {
          return "dubbo : " + msg;
      }
  
  }
  ```

- dubbo-client客户端

  ```java
  public class HelloClient {
  
      public static void main(String[] args) {
          DubboHello dubboHello = null;
          String helloDubbo = dubboHello.hello("hello dubbo");
          System.out.println(helloDubbo);
      }
  
  }
  ```

  - 这样是没办法进行方法调用，此时client依赖`server-api` ，还需要进行服务注册





### 改进

- server-provider 添加dubbo依赖

  ```xml
  <properties>
      <dubbo.version>2.7.2</dubbo.version>
  </properties>
  <dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo</artifactId>
    <version>${dubbo.version}</version>
  </dependency>
  ```

- dubbo-server.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
    xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd        http://dubbo.apache.org/schema/dubbo        http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
  
    <!-- 提供方应用信息，用于计算依赖关系 -->
    <dubbo:application name="dubbo-server-provider" owner="hufier"/>
  
    <!-- 使用multicast广播注册中心暴露服务地址 -->
    <dubbo:registry address="N/A"/>
  
    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="20880"/>
  
    <!-- 声明需要暴露的服务接口 -->
    <dubbo:service interface="com.huifer.dubbo.server.api.DubboHello" ref="dubboHello"/>
  
    <!-- 和本地bean一样实现服务 id=server:ref-->
    <bean id="dubboHello" class="com.huifer.dubbo.server.provider.DubboHelloImpl"/>
  
  </beans>
  ```

- Bootstrap

  ```java
  public class Bootstrap {
  
      public static void main(String[] args) throws IOException {
          ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                  "dubbo-server.xml");
          context.start();
          System.in.read();
      }
  
  }
  ```

- 启动

  ```
   dubbo://0.0.0.0:20880/com.huifer.dubbo.server.api.DubboHello?anyhost=true&application=dubbo-server-provider&bean.name=com.huifer.dubbo.server.api.DubboHello&bind.ip=0.0.0.0&bind.port=20880&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=com.huifer.dubbo.server.api.DubboHello&methods=hello&owner=hufier&pid=12916&register=true&release=2.7.2&side=provider&timestamp=1560395544486, dubbo version: 2.7.2, current host: 0.0.0.0
  ```

  

- `dubbo-client`也需要加入`dubbo`依赖

- dubbo-client.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
    xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd        http://dubbo.apache.org/schema/dubbo        http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
  
    <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
    <dubbo:application name="dubbo-client"  />
  
    <!-- 使用multicast广播注册中心暴露发现服务地址 -->
    <dubbo:registry address="N/A" />
  
    <!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
    <dubbo:reference id="dubboHello" interface="com.huifer.dubbo.server.api.DubboHello" url="dubbo://0.0.0.0:20880/com.huifer.dubbo.server.api.DubboHello"/>
  </beans>
  ```

- HelloClient

  ```java
  public class HelloClient {
  
      public static void main(String[] args) {
          ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                  "dubbo-client.xml");
          DubboHello dubboHello = (DubboHello) context.getBean("dubboHello");
  
          String helloDubbo = dubboHello.hello("hello dubbo");
          System.out.println(helloDubbo);
      }
  
  }
  ```

  - 运行结果

    `dubbo : hello dubbo`

### 配合zk

- 依赖

  ```xml
  <dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo</artifactId>
    <version>${dubbo.version}</version>
  </dependency>
  
  <dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-dependencies-zookeeper</artifactId>
    <version>${dubbo.version}</version>
  </dependency>
  ```

- 服务端地址配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
  xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd        http://dubbo.apache.org/schema/dubbo        http://dubbo.apache.org/schema/dubbo/dubbo.xsd">

  <!-- 提供方应用信息，用于计算依赖关系 -->
  <dubbo:application name="dubbo-server-provider" owner="hufier"/>

  <dubbo:registry address="zookeeper://192.168.1.107:2181"/>
  <!-- 用dubbo协议在20880端口暴露服务 -->
  <dubbo:protocol name="dubbo" port="20880"/>

  <!-- 声明需要暴露的服务接口 -->
  <dubbo:service interface="com.huifer.dubbo.server.api.DubboHello" ref="dubboHello"/>

  <!-- 和本地bean一样实现服务 id=server:ref-->
  <bean id="dubboHello" class="com.huifer.dubbo.server.provider.DubboHelloImpl"/>

</beans>

```

- 客户端配置

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
    xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd        http://dubbo.apache.org/schema/dubbo        http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
  
    <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
    <dubbo:application name="dubbo-client"  />
  
    <dubbo:registry address="zookeeper://192.168.1.107:2181"/>
  
    <!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
    <dubbo:reference id="dubboHello" interface="com.huifer.dubbo.server.api.DubboHello" />
  </beans>
  ```

  



## dubbo 在zookeeper

启动一个dubbo服务端项目在zookeeper的结构

- 启动一个dubbo服务端，内含包括

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
  xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd        http://dubbo.apache.org/schema/dubbo        http://dubbo.apache.org/schema/dubbo/dubbo.xsd">

  <!-- 提供方应用信息，用于计算依赖关系 -->
  <dubbo:application name="dubbo-server-provider" owner="hufier"/>

  <dubbo:registry address="zookeeper://192.168.1.107:2181"/>
  <!-- 用dubbo协议在20880端口暴露服务 -->
  <dubbo:protocol name="dubbo" port="20880"/>

  <!-- 声明需要暴露的服务接口 -->
  <dubbo:service interface="com.huifer.dubbo.server.api.DubboHello" ref="dubboHello" protocol="dubbo"/>
  <dubbo:service interface="com.huifer.dubbo.server.api.DubboHello2" ref="dubboHello2" protocol="hessian"/>

  <!-- 和本地bean一样实现服务 id=server:ref-->
  <bean id="dubboHello" class="com.huifer.dubbo.server.provider.DubboHelloImpl"/>
  <bean id="dubboHello2" class="com.huifer.dubbo.server.provider.DubboHelloImpl2"/>

</beans>
```

```sequence
\ --> dubbo:

```



