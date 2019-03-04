package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.respository.BlogDao;
import com.douzone.jblog.respository.CategoryDao;
import com.douzone.jblog.respository.PostDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;
	@Autowired
	PostDao postDao;
	@Autowired
	CategoryDao categoryDao;

	
	public Map<String,Object> getblog(String name, long categoryNo , long postNo) {
		//1.userNo구하기 
		long userNo = blogDao.get(name);
		
		//2.타이틀 로고가져오기 
		BlogVo blogVo = blogDao.get(userNo);
		//3.카테고리리스트가져오기
		List<CategoryVo> categoryList= categoryDao.getList(userNo);
		//4.포스트 가져오기
		PostVo postVo = postDao.get(userNo, categoryNo, postNo);
		//5.글목록 가져오기
		List<PostVo> postList = postDao.getList(userNo, categoryNo);
				
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postVo", postVo);
		map.put("postList", postList);
				
		return map;
	}
	
}