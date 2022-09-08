package com.itheima.controller;

import com.itheima.service.LdRcsCallBackService;
import com.itheima.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v4/Api/Transport")
public class LdRcsController {

	@Autowired
	private LdRcsCallBackService ldRcsCallBackService;

	@PostMapping("/Movev1")
	public void findUserMsgByUserName(@RequestBody String json, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		OutputStream out = response.getOutputStream();
		try {
			String taskId = JsonHelper.getString("taskId", json);
			ldRcsCallBackService.receiveLdRcsTask(taskId);
			this.resultLdRCS(true,"",out);
		}catch (Exception e){
			this.resultLdRCS(false,"错误",out);

		}

	}

	private void resultLdRCS(Boolean isSuccessful, String errorMessage, OutputStream out) throws Exception{
		Map<Object,Object> resMap = new HashMap<Object,Object>();
		resMap.put("isSuccessful",isSuccessful);
		resMap.put("errorMessage",errorMessage);
		String resultStr = JsonHelper.toJson(resMap);
		out.write(resultStr.getBytes("UTF-8"));
		out.flush();
		out.close();
	}
}