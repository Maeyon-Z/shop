package com.zzz.shop.user.service.impl;

import com.zzz.shop.bean.model.User;
import com.zzz.shop.user.mapper.UserMapper;
import com.zzz.shop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzz
 * 2022-04-26 21:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }
}
