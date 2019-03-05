package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.AuthUser;


@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	BlogService blogService;
	@Autowired
	FileuploadService fileuploadService;
//---------------------------------------------------------------------------
	@RequestMapping(value= {"","/{pathNo1}","/{pathNo1}/{pathNo2}"}/*, method=RequestMethod.GET*/)
	public String index(@PathVariable String id,
					    @PathVariable Optional<Long> pathNo1,
					    @PathVariable Optional<Long> pathNo2,
					    Model model){
					// ModelMap modelMap 이걸써봐라
		
		long categoryNo = 1;
		long postNo = 1;
		
//널이아니면-----------------------------------
		if(!(pathNo1.toString() == "Optional.empty")) {
			categoryNo = pathNo1.get();
		}
		if(!(pathNo2.toString() == "Optional.empty")) {
			postNo = pathNo2.get();
		}
//	----------------------------------------
		
		Map<String, Object> blogMain;
		blogMain = blogService.getblog(id,categoryNo,postNo);
			
		model.addAttribute("userId", id);
		model.addAttribute("blogVo",blogMain.get("blogVo"));
		model.addAttribute("categoryList",blogMain.get("categoryList"));
		model.addAttribute("postVo",blogMain.get("postVo"));
		model.addAttribute("postList",blogMain.get("postList"));
		
		return "blog/blog-main";			
	}
	
//--------------------------------------------------------------------
	
	@RequestMapping(value="/admin" , method=RequestMethod.GET)
	public String post(@PathVariable String id,
					   Model model){
		
		BlogVo blogVo = blogService.getAdmin(id);
		model.addAttribute("blogVo", blogVo );
		return "blog/blog-admin-basic";			
	}
	
	@RequestMapping(value="/admin" , method=RequestMethod.POST)
	public String post(@AuthUser UserVo authUser, 
					   @RequestParam(value="logo-file") MultipartFile multipartFile,
					   BlogVo blogVo){
		
		System.out.println(authUser.toString());
		
		if(multipartFile.isEmpty() == false) {
			String logo = fileuploadService.restore(multipartFile);
			blogVo.setLogo(logo);
			blogService.adminUpdata(authUser.getNo(),blogVo);
		}
		
		return "blog/blog-admin-basic";			
	}
	
//---------------------------------------------------------------------
	
//	@RequestMapping(value="/admin/category" ,method=RequestMethod.GET)
//	public String category(@AuthUser UserVo authUser, 
//						   Model model){
//		
//		
//		List<CategoryVo> categoryList = blogService.adminCategory(authUser.getNo());
//		System.out.println(categoryList);
//		model.addAttribute("categoryList",categoryList);
//		return "blog/blog-admin-category";			
//	}
	
	@RequestMapping(value="/admin/write" ,method=RequestMethod.GET)
	public String write(){
		
		return "blog/blog-admin-write";			
	}
		
}
