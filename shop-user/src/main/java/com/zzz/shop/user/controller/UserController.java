package com.zzz.shop.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.zzz.shop.bean.model.User;
import com.zzz.shop.user.service.UserService;
import com.zzz.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzz
 * 2022-04-26 21:37
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get/{uid}")
    public User getUserById(@PathVariable("uid") Long uid){
        User user = userService.getUserById(uid);
        log.info("获取到的用户信息为：{}" + JSONObject.toJSONString(user));
        return user;
    }
}
