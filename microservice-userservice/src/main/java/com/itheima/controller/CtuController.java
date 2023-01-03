//package com.itheima.controller;
//
//import com.itheima.model.RestMessage;
//import com.itheima.model.UserModel;
//import com.itheima.service.CtuCallBackService;
//import com.itheima.service.UserService;
//import com.itheima.util.JsonHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/rcms/services/rest/hikRpcService")
//public class CtuController {
//
//	@Autowired
//	private CtuCallBackService ctuCallBackService;
//
//	@PostMapping("/genAgvSchedulingTask")
//	public RestMessage<String> findUserMsgByUserName(@RequestBody String json)throws Exception {
//		try {
//			String taskCode = JsonHelper.getString("taskCode", json);
//			ctuCallBackService.receiveCtuTask(taskCode);
//			return RestMessage.newInstance(true, "0","成功", null);
//		}catch (Exception e){
//			return RestMessage.newInstance(false, "99",e.getMessage());
//		}
//
//	}
//}