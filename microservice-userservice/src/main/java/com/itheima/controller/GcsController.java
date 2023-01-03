package com.itheima.controller;

import com.itheima.model.RestMessage;
import com.itheima.model.ZxGcsCarryRequestDto;
import com.itheima.model.ZxGcsCarryResponseDto;
import com.itheima.service.GcsCallBackService;
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
public class GcsController {

	@Autowired
	private GcsCallBackService gcsCallBackService;

	@PostMapping("/TaskOrderFromEis")
	public ZxGcsCarryResponseDto findUserMsgByUserName(@RequestBody String json)throws Exception {
		try {
			List<ZxGcsCarryRequestDto> objectList = JsonHelper.getObjectList(json, ZxGcsCarryRequestDto.class);
			String taskId = objectList.get(0).getTaskId();
			gcsCallBackService.receiveGcsTask(taskId);
			return new ZxGcsCarryResponseDto(true,"");
		}catch (Exception e){
			return new ZxGcsCarryResponseDto(false,e.getMessage());
		}

	}
}