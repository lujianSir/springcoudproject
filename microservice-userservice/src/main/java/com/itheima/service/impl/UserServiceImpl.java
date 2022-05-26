package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.model.FileLogHelper;
import com.itheima.model.Order;
import com.itheima.model.RestMessage;
import com.itheima.model.UserModel;
import com.itheima.redis.RedisUtil;
import com.itheima.resource.IOrderResouce;
import com.itheima.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Override
    public UserModel findUserMsgByUserName(String username) throws Exception{
        UserModel userModel =userMapper.selectUser(username);
        redisUtil.setEx("username", userModel.getUsername(),1, TimeUnit.MINUTES);
        RestMessage<List<Order>> restMessage= orderResouce.findOrder(userModel.getUsername());
        if(!restMessage.isSuccess()){
            throw  new Exception(restMessage.getMessage());
        }
        userModel.setOrderList(restMessage.getData());
        System.out.println(redisUtil.get("username"));

        String msg = "hello fanout 你好=========================";
        try {
            //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
            rabbitTemplate.convertAndSend("TestDirectExchange1", "TestDirectRouting", msg);
            //记录单个文件错误
            FileLogHelper.WriteLog("findUserMsgByUserName", "消息:"+msg);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return userModel;
    }

    @Override
    public void testMq() {
        int i=0;
        String msg="";
        while (true){
            if(i>=10){
                break;
            }
             msg = "第"+(i+1)+"次rabbitmq消息队列";
            //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
            rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", msg);
            //记录单个文件错误
            FileLogHelper.WriteLog("testMq", "发送消息:"+msg);
            i++;
        }
    }
}
