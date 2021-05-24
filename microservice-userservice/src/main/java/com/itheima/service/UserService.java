package com.itheima.service;

import com.itheima.model.UserModel;

public interface UserService {
    /**
     * 通过名称查询用户的信息
     * @param username
     * @return
     */
    UserModel findUserMsgByUserName(String username)throws Exception;


    void testMq();
}
