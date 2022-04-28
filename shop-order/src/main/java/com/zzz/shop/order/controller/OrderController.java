package com.zzz.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzz.shop.bean.model.Order;
import com.zzz.shop.bean.params.OrderParams;
import com.zzz.shop.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzz
 * 2022-04-27 15:50
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderServiceImplV5;

    @GetMapping(value = "/submitOrder")
    public String submitOrder(OrderParams orderParams){
        log.info("提交订单时传递的参数:{}", JSONObject.toJSONString(orderParams));
        orderServiceImplV5.saveOrder(orderParams);
        return "success";
    }
}
