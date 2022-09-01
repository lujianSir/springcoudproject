package com.itheima.controller;
import com.itheima.model.Order;
import com.itheima.model.RestMessage;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/findOrderByUserId")
	public RestMessage<List<Order>> findOrder(@RequestParam(value="userId") String userId) {
		List<Order> orders = orderService.findOrderByUserId(userId);


		return RestMessage.newInstance(true, "成功", orders);
	}

	public static void main(String[] args) {
		Test test=new Test.Builder().setStuName("zhangsan").setWeight(1).setStuAge(20).build();

		Test1 test1=new Test1.Test1Builder().stuName("lisi").build();
	}
}