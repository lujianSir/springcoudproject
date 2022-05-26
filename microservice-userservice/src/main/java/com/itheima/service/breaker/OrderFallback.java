package com.itheima.service.breaker;

import com.itheima.model.Order;
import com.itheima.model.RestMessage;
import com.itheima.resource.IOrderResouce;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFallback implements IOrderResouce {
    @Override
    public RestMessage<List<Order>> findOrder(String userId) {
        return RestMessage.newInstance(false, "【order-service|查询客户订单信息请求服务失败", null);
    }
}
