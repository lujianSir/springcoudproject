package com.itheima.resource;

import com.itheima.model.Order;
import com.itheima.model.RestMessage;
import com.itheima.service.breaker.OrderFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value="order-service",fallback= OrderFallback.class)
public interface IOrderResouce {

    @PostMapping("/order/findOrderByUserId")
    public RestMessage<List<Order>> findOrder(@RequestParam(value="userId") String userId);

}
