package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.service.CtuCallBackService;
import com.itheima.service.McsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Interface")
public class McsController {

	@Autowired
	private McsCallBackService mcsCallBackService;

	@PostMapping("/Request")
	public RestMessage<String> findUserMsgByUserName(@RequestBody String json)throws Exception {
		try {
			String taskId = JsonHelper.getString("taskId", json);
			mcsCallBackService.receiveMcsTask(taskId);
			return RestMessage.newInstance(true, "0","成功", null);
		}catch (Exception e){
			return RestMessage.newInstance(false, "99",e.getMessage());
		}

	}
}