package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.model.Order;
import com.itheima.model.RestMessage;
import com.itheima.model.User;
import com.itheima.redis.RedisUtil;
import com.itheima.resource.IOrderResouce;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IOrderResouce orderResouce;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User findUserMsgByUserName(String username) throws Exception{
        User user=userMapper.selectUser(username);
        redisUtil.setEx("username",user.getUsername(),1, TimeUnit.MINUTES);
        RestMessage<List<Order>> restMessage= orderResouce.findOrder(user.getUsername());
        if(!restMessage.isSuccess()){
            throw  new Exception(restMessage.getMessage());
        }
        user.setOrderList(restMessage.getData());
        System.out.println(redisUtil.get("username"));
        return user;
    }
}
