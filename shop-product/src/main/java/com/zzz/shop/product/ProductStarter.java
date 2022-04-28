package com.zzz.shop.product;

import com.zzz.shop.product.mapper.ProductMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zzz
 * 2022-04-27 15:18
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"com.zzz.shop.product.mapper"})
@EnableDiscoveryClient
public class ProductStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProductStarter.class, args);
    }
}
