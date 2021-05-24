package com.lujian.microservicepluservice.test;

import com.itheima.model.User;
import com.lujian.microservicepluservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class Test {

    @Autowired
    private UserMapper userMapper;

   // @org.junit.jupiter.api.Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for(User user:userList) {
            System.out.println(user);
        }

        Map<String, Object> columnMap=new HashMap<>();
        columnMap.put("id",1);
        List<User> users=  userMapper.selectByMap(columnMap);
        users.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    @org.junit.jupiter.api.Test
    public void testAutoFill() {
        User user = new User();
        user.setName("tom");
        user.setAge(20);
        user.setEmail("tom@163.com");
        if (userMapper.insert(user)==1) {
            List<User> userList = userMapper.selectList(null);
            for(User user1:userList) {
                System.out.println(user1);
            }
        } else {
            System.out.println("添加数据失败");
        }
    }
}
