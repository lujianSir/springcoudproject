package com.itheima.service;

import com.itheima.model.Order;

import java.util.List;

public interface OrderService {

    /**
     * 通过用户ID查询对应的订单信息
     * @param userid
     * @return
     */
    List<Order> findOrderByUserId(String userid);
}
