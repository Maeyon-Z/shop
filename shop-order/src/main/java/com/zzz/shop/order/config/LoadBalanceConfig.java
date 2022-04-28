package com.zzz.shop.order.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zzz
 * 2022-04-27 15:51
 */
@Configuration
@ComponentScan(value = "com.zzz.shop.utils")
public class LoadBalanceConfig {

    @Bean
    @LoadBalanced   // 此注解为使用Ribbon实现负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
