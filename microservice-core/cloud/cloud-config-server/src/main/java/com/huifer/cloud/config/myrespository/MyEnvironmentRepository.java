package com.huifer.cloud.config.myrespository;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title : MyEnvironmentRepository </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-28
 */

@Configuration
public class MyEnvironmentRepository {

    @Bean
    public EnvironmentRepository environmentRepository() {
        EnvironmentRepository environmentRepository = new EnvironmentRepository() {
            @Override
            public Environment findOne(String application, String profile, String label) {
                Environment environment = new Environment("default", profile);
                List<PropertySource> propertySources = environment.getPropertySources();

                Map<String, String> source = new HashMap<>();
                source.put("MyEnvironmentRepository", "hello,world");

                PropertySource propertySource = new PropertySource("new_property", source);

                propertySources.add(propertySource);
                return environment;
            }
        };

        return environmentRepository;
    }


}
