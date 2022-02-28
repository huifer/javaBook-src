# Nacos
## github
https://github.com/alibaba/nacos
## 安装启动nacos
访问https://github.com/alibaba/nacos/releases,下载nacos-server(本文使用nacos-servedr-1.1.0版本)

```
tar -zxvf nacos-server-1.1.0.tar.gz
cd nacos-server-1.1.0/bin
```

![1564453237848](assets/1564453237848.png)

- windows 启动使用`startup.cmd`
- linux 启动使用`startup.sh`

启动界面

![1564453437135](assets/1564453437135.png)

访问`http://192.168.0.124:8848/nacos/index.html`

![1564453777258](assets/1564453777258.png)

登陆账号密码:nacos，nacos

## conf

在nacos中有一个`conf`文件夹将`nacos-mysql.sql`和`schema.sql`添加到本地数据库中，同时修改`application.properties`



## spring-boot整合

- 依赖文件

  ```xml
   <properties>
      <nacos-config-spring-boot.version>0.2.1</nacos-config-spring-boot.version>
    </properties>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  
      <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-config-spring-boot-starter</artifactId>
        <version>${nacos-config-spring-boot.version}</version>
      </dependency>
  
      <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-config-spring-boot-actuator</artifactId>
        <version>${nacos-config-spring-boot.version}</version>
      </dependency>
    </dependencies>
  ```

- 配置文件

  ```properties
  nacos.config.server-addr=0.0.0.0:8848
  management.endpoints.web.exposure.include=*
  management.endpoint.health.show-details=always
  spring.application.name=springboot-nacos-config
  ```

- 启动类

  ```java
  package com.huifer.alibaba.nacos.springboot;
  
  import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  
  /**
   * @author: wang
   * @description: Nacos-spring-boot
   */
  @SpringBootApplication
  @NacosPropertySource(dataId = "springboot-nacos-config", autoRefreshed = true)
  public class NacosSpringBootApp {
  
  	public static void main(String[] args) {
  		SpringApplication.run(NacosSpringBootApp.class, args);
  	}
  
  }
  ```

  

- controller

  ```java
  package com.huifer.alibaba.nacos.springboot.controller;
  
  import com.alibaba.nacos.api.annotation.NacosInjected;
  import com.alibaba.nacos.api.config.ConfigService;
  import com.alibaba.nacos.api.config.annotation.NacosValue;
  import com.alibaba.nacos.api.exception.NacosException;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.RestController;
  
  /**
   * @author: wang
   * @description:
   */
  @RestController
  @RequestMapping("/config")
  public class ConfigController {
  
  	@NacosValue(value = "${test.string:work}", autoRefreshed = true)
  	private String doString;
  
  	@NacosInjected
  	private ConfigService configService;
  
  	@GetMapping("/get")
  	public String get() {
  		return doString;
  	}
  
  	@GetMapping("/set")
  	public ResponseEntity publish(
  			@RequestParam String dataId,
  			@RequestParam(defaultValue = "DEFAULT_GROUP") String group,
  			@RequestParam String content) throws NacosException {
  		boolean result = configService.publishConfig(dataId, group, content);
  		if (result) {
  			return new ResponseEntity<String>("Success", HttpStatus.OK);
  		}
  		return new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
  
  	}
  
  
  }
  ```

### 注解说明

@NacosPropertySource

- 加载数据源
- dataId
  - 数据集id
- autoRefreshed
  - 是否开启动态更新

@NacosValue

- 读取数据
- value
  - properites形式
- autoRefreshed
  - 是否开启动态更新

​	

### 访问测试

- `localhost:8080/config/get`

  - ![1564468649867](assets/1564468649867.png)

  此时返回的值为默认值work

  ```java
  @NacosValue(value = "${test.string:work}", autoRefreshed = true)
  private String doString;
  ```

- 登录`http://localhost:8848/nacos/index.html`进行设置`test.string`

  ![1564468757370](assets/1564468757370.png)

  ![1564468771218](assets/1564468771218.png)

**data id**：填写启动类上的`springboot-nacos-config`

**grpup**: 填写默认值

配置内容选择**Properties**

`@NacosPropertySource(dataId = "springboot-nacos-config", autoRefreshed = true)`

内容填写如下

```
test.string=workNacos
```

![1564469226006](assets/1564469226006.png)

点击发布

- 再次访问`localhost:8080/config/get`

  ![1564469266446](assets/1564469266446.png)

- 至此我们可以通过nacos进行数据源设置，访问可以获取相关参数。回过头看一看我们的set方法。

```java
	@GetMapping("/set")
	public ResponseEntity publish(
			@RequestParam String dataId,
			@RequestParam(defaultValue = "DEFAULT_GROUP") String group,
			@RequestParam String content
	) throws NacosException {
		boolean result = configService.publishConfig(dataId, group, content);
		if (result) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);

	}
```

该方法可以通过api的形式进行配置修改，设置。

访问 `http://localhost:8080/config/set?dataId=springboot-nacos-config&group=DEFAULT_GROUP&content=test.string:work`

![1564469416053](assets/1564469416053.png)

- 再次访问`localhost:8080/config/get`

  ![1564469449830](assets/1564469449830.png)

### 注

**一条数据对应一个data-id,切勿重复使用data-id**