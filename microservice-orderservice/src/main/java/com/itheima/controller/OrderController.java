package com.itheima.controller;
import com.itheima.mapper.OrderMapper;
import com.itheima.model.Order;
import com.itheima.model.RestMessage;
import com.itheima.service.OrderService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/findOrderByUserId")
	public RestMessage<List<Order>> findOrder(@RequestBody String json) {
		String userId=JsonHelper.getString("userId",json);
		List<Order> orders = orderService.findOrderByUserId(userId);
		return RestMessage.newInstance(true, "成功", orders);
	}
}