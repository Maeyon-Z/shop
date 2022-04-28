package com.zzz.shop.order;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author zzz
 * 2022-04-27 15:52
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"com.zzz.shop.order.mapper"})
@EnableDiscoveryClient  // 开启服务发现客户端
@EnableFeignClients // 开启 Feign 客户端
public class OrderStarter {
    public static void main(String[] args) {
        SpringApplication.run(OrderStarter.class, args);
    }
}
