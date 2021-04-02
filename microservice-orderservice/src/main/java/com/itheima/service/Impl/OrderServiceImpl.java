package com.itheima.service.Impl;

import com.itheima.mapper.OrderMapper;
import com.itheima.model.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findOrderByUserId(String userid) {
        return orderMapper.selectOrder(userid);
    }
}
