package com.yingluo.gateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customizedLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("yingluo-order", r -> r.path("/api/order/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://yingluo-order"))
                .route("yingluo-seata", r -> r.path("/api/seata/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://yingluo-seata"))
                .route("yingluo-seckill", r -> r.path("/api/seckill/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://yingluo-seckill"))
                .route("yingluo-auth", r -> r.path("/api/auth/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://yingluo-auth"))
                .route("yingluo-web", r -> r.path("/api/web/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://yingluo-web"))
                .build();
    }

    /**
     * 修复使用feign client远程调用服务时报HttpMessageConverters缺失问题
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
