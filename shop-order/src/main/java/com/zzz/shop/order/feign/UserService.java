package com.zzz.shop.order.feign;

import com.zzz.shop.bean.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzz
 * 2022-04-28 19:21
 */
@FeignClient("server-user")
public interface UserService {

    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
