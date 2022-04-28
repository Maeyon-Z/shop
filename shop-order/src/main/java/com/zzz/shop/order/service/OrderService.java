package com.zzz.shop.order.service;

import com.zzz.shop.bean.params.OrderParams;

/**
 * @author zzz
 * 2022-04-27 15:31
 */
public interface OrderService {
    void saveOrder(OrderParams orderParams);
}
