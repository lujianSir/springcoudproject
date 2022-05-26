package com.lujian.microservicepluservice.service.impl;

import com.itheima.model.User;
import com.lujian.microservicepluservice.mapper.UserMapper;
import com.lujian.microservicepluservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }
}
