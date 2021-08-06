package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.model.UserModel;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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

	public static void main(String[] args) {
		String[] array = {"a","b","c"};
		//第一种(for循环)
		List<String> resultList = new ArrayList<>();
		for (String  a:array) {
			resultList.add(a);
		}
		resultList.forEach(r->{
			System.out.println(r);
			System.out.println((r.equals("b")?true:false));
		});
		//第二种 Collections.addAll (工具类)
		Collections.addAll(resultList,array);
		System.out.println(resultList);

		//第三种 Arrays.List
		resultList= Arrays.asList(array);
		System.out.println(resultList);
	}
}