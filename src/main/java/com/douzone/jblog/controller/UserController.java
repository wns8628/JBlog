package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService userService; //이게필요함 호출해야하니깐

//---------------------------------------------------------------------------------------------	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/user/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo userVo) {

		boolean success = userService.join(userVo);
		if(success) {
			return "/user/joinsuccess";		
		}		
		
		return "redirect:/main";
	}
//---------------------------------------------------------------------------	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
//----------------------------------------------------------------------------------
	
	
}
