package com.huifer.mvc.config;

import com.huifer.mvc.adapter.MyHandlerInterceptorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptorAdapter()).addPathPatterns("/**");
    }

    @Bean
    MyHandlerInterceptorAdapter myHandlerInterceptorAdapter() {
        return new MyHandlerInterceptorAdapter();
    }
}
