package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.model.ZxGcsCarryResponseDto;
import com.itheima.model.ZxMcsCarryRequestDto;
import com.itheima.service.CtuCallBackService;
import com.itheima.service.McsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Interface")
public class McsController {

	@Autowired
	private McsCallBackService mcsCallBackService;

	@PostMapping("/Request")
	public ZxGcsCarryResponseDto findUserMsgByUserName(@RequestBody String json)throws Exception {
		try {
			List<ZxMcsCarryRequestDto> zxMcsCarryRequestDtoList = JsonHelper.getKeyObjectList("carryList", json, ZxMcsCarryRequestDto.class);
			String taskId = zxMcsCarryRequestDtoList.get(0).getTaskId();
			mcsCallBackService.receiveMcsTask(taskId);
			return new ZxGcsCarryResponseDto(true,"");
		}catch (Exception e){
			return new ZxGcsCarryResponseDto(false,e.getMessage());
		}

	}
}