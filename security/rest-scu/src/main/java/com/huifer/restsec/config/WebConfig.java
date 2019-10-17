package com.huifer.restsec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private TimeInterceptor timeInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
//    }
//
//    @Bean
//    public FilterRegistrationBean filter() {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(new TimeFilter());
//
//        // 可以单独配置那些url bei被这个TimeFilter 作用
////        List<String> urls = new ArrayList<>()/        urls.add("/*");
////        filterFilterRegistrationBean.setUrlPatterns(urls);
//
//        return filterFilterRegistrationBean;
//    }
}
