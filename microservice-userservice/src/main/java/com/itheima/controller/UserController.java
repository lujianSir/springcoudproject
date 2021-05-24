package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.model.UserModel;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/findUserMsgByUserName")
	public RestMessage<UserModel> findUserMsgByUserName(@RequestParam String username)throws Exception {
		UserModel userModel =userService.findUserMsgByUserName(username);
		return RestMessage.newInstance(true, "成功", userModel);
	}

	/**
	 * 测试mq
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/testMq")
	public RestMessage<String> testMq()throws Exception {
		userService.testMq();
		return RestMessage.newInstance(true, "成功", null);
	}
}