package com.huifer.spring;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title : SpringBootBuildApplication </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-22
 */
@SpringBootApplication
public class SpringBootBuildApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(
                SpringBootBuildApplication.class);

        Map<String, Object> properties = new HashMap<>();
        // server.port=0 系统随机分发一个未使用的端口
        properties.put("server.port", 0);
        springApplication.setDefaultProperties(properties);


        springApplication.run(args);


    }

}
