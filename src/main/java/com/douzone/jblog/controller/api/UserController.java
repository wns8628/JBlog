package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller("userApiController") //스캔할떄 UserController가 두개니깐 에러남  그래서 아디를부여함
@RequestMapping("/user/api")
public class UserController { 

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkId")
	public JSONResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id ){
		
		UserVo vo = userService.existId(id);
		
		boolean exist = vo != null;
		
			return JSONResult.success(exist);
	}
}
