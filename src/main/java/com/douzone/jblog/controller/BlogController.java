package com.douzone.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;


@Controller
@RequestMapping("/{name}")
public class BlogController {
	@Autowired
	BlogService blogService;
	
//---------------------------------------------------------------------------
	@RequestMapping(value="/{categoryNo}/{postNo}")
	public String list(@PathVariable String name,
					   @PathVariable long categoryNo,
					   @PathVariable long postNo,
					   Model model){
		
		Map<String, Object> blogMain;
		blogMain = blogService.getblog(name,categoryNo,postNo);
			
		model.addAttribute("blogVo",blogMain.get("blogVo"));
		model.addAttribute("categoryList",blogMain.get("categoryList"));
		model.addAttribute("postVo",blogMain.get("postVo"));
		model.addAttribute("postList",blogMain.get("postList"));
		
		return "blog/blog-main";			
	}
//--------------------------------------------------------------------여까지함	
	
	@RequestMapping(value="/admin")
	public String post(){
		
		return "blog/blog-admin-basic";			
	}
	
	@RequestMapping(value="/{categoryNo}")
	public String category(){
		
		return "blog/blog-admin-category";			
	}
	
	@RequestMapping(value="/write")
	public String write(){
		
		return "blog/blog-admin-write";			
	}
		
}
