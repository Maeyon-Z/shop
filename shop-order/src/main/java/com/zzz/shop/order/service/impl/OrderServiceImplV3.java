package com.zzz.shop.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzz.shop.bean.model.Order;
import com.zzz.shop.bean.model.OrderItem;
import com.zzz.shop.bean.model.Product;
import com.zzz.shop.bean.model.User;
import com.zzz.shop.bean.params.OrderParams;
import com.zzz.shop.order.mapper.OrderItemMapper;
import com.zzz.shop.order.mapper.OrderMapper;
import com.zzz.shop.order.service.OrderService;
import com.zzz.shop.utils.constants.HttpCode;
import com.zzz.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @author zzz
 * 2022-04-28 19:03
 * 自定义负载均衡
 */
@Service
@Slf4j
public class OrderServiceImplV3 implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    // 注入服务发现客户端
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVER = "server-user";
    private static final String PRODUCT_SERVER = "server-product";

    /**
     * 根据服务名获得服务地址，ip:端口号
     * @param serviceName
     * @return
     */
    private String getServiceUrl(String serviceName){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        Integer index = new Random().nextInt(serviceInstances.size());
        ServiceInstance serviceInstance = serviceInstances.get(index);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        log.info("负载均衡的服务地址为：" + url);
        return url;
    }

    @Override
    public void saveOrder(OrderParams orderParams) {
        if(orderParams.isEmpty()){
            throw new RuntimeException("参数异常：" + JSON.toJSONString(orderParams));
        }

        // 获取用户服务与商品服务的地址
        String userUrl = this.getServiceUrl(USER_SERVER);
        String productUrl = this.getServiceUrl(PRODUCT_SERVER);

        //调用用户微服务获取用户信息
        User user = restTemplate.getForObject("http://" + userUrl + "/user/get/" + orderParams.getUserId(), User.class);
        if(user == null){
            throw new RuntimeException("未获取到用户信息：" + JSON.toJSONString(orderParams));
        }

        //调用商品微服务获取商品信息
        Product product = restTemplate.getForObject("http://" + productUrl + "/product/get/" + orderParams.getProductId(), Product.class);
        if(product == null){
            throw new RuntimeException("未获取到商品信息：" + JSON.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()){
            throw new RuntimeException("商品库存不足：" + JSON.toJSONString(orderParams));
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = restTemplate.getForObject("http://" + productUrl +"/product/updateProductCount/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }
}
