package com.huifer.gateway.config;

import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title : RouteConfig </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-31
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder) {
        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
        config.setParts(1);
      return   builder.routes()
                .route("host_route", r -> r.path("/c/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:9001"))
                .route("host_route", r -> r.path("/d/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:9000"))
                .build();
    }


}
