package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.model.User;
import com.itheima.service.UserService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/findUserMsgByUserName")
	public RestMessage<User> findUserMsgByUserName(@RequestParam String username)throws Exception {
		User user=userService.findUserMsgByUserName(username);
		return RestMessage.newInstance(true, "成功", user);
	}
}