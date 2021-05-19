package com.itheima.service;

import com.itheima.model.User;

public interface UserService {
    /**
     * 通过名称查询用户的信息
     * @param username
     * @return
     */
    User findUserMsgByUserName(String username)throws Exception;


    void testMq();
}
