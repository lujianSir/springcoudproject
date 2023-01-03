package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.service.CtuCallBackService;
import com.itheima.service.RcsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rcms/services/rest/hikRpcService")
public class RcsController {

	@Autowired
	private RcsCallBackService rcsCallBackService;

	@PostMapping("/genAgvSchedulingTask")
	public RestMessage<String> findUserMsgByUserName(@RequestBody String json)throws Exception {
		try {
			String taskCode = JsonHelper.getString("taskCode", json);
			rcsCallBackService.receiveRcsTask(taskCode);
			return RestMessage.newInstance(true, "0","成功", null);
		}catch (Exception e){
			return RestMessage.newInstance(false, "99",e.getMessage());
		}

	}
}