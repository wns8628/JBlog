package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/{id}")
public class CategoryController {
	@Autowired
	private BlogService blogService;
	
	@Auth
	@RequestMapping(value="/admin/category" ,method=RequestMethod.GET)
	public String category(){
		return "blog/blog-admin-category";		
	}
	@Auth
	@ResponseBody
	@RequestMapping(value="/admin/category/list" ,method=RequestMethod.GET)
	public Object category(@AuthUser UserVo authUser){
		
		List<CategoryVo> categoryList = blogService.adminCategory(authUser.getNo());
		
		return JSONResult.success(categoryList);		
	}
	@Auth
	@ResponseBody
	@RequestMapping(value="/admin/category/list" ,method=RequestMethod.POST)
	public Object category(@AuthUser UserVo authUser,
						   CategoryVo categoryVo){
		System.out.println(categoryVo);
		long no = blogService.adminPutCategory(authUser.getNo(),categoryVo);
		categoryVo.setNo(no);
		
		return JSONResult.success(categoryVo);		
	}
	@Auth
	@ResponseBody
	@RequestMapping(value="/admin/category/list/delete" ,method=RequestMethod.POST)
	public Object categorydelete(@AuthUser UserVo authUser,
								 CategoryVo categoryVo){

		boolean success= blogService.adminDeleteCategory(authUser.getNo(),categoryVo.getNo());
		
		return JSONResult.success(success);		
	}
}
