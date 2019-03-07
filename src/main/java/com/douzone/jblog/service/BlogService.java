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

	
//	public long latestPosts(String id) {
//		//1.userNo구하기 
//		long userNo = blogDao.get(id);
//		//1-1.가장 최신글 가져오기(처음접속시 보이는것)
//		long topNo = postDao.get(userNo);
//		return topNo;
//	}
	
	
	public Map<String,Object> getblog(String id, Long categoryNo , Long postNo) {
		//1.userNo구하기 
		long userNo = blogDao.get(id);

		//2.타이틀 로고가져오기 
		BlogVo blogVo = blogDao.get(userNo);
		//3.카테고리리스트가져오기
		List<CategoryVo> categoryList;
		categoryList= categoryDao.getList(userNo);
		//전체글 세팅하기
		//int sumPost = postDao.getCount(userNo);
		//categoryList.get(0).setPostCount(sumPost);
		
		//4.포스트 가져오기
		PostVo postVo;
//		if(categoryNo== 9999) {
//			postVo = postDao.get(userNo, postNo);			
//		}else {
			postVo = postDao.get(userNo, categoryNo, postNo);			
//		}
		//5.글목록 가져오기
		List<PostVo> postList;
//		if(categoryNo== 9999) {
//		   postList = postDao.getList(userNo);
//		}else {
		   postList = postDao.getList(userNo, categoryNo , postNo);	
//		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postVo", postVo);
		map.put("postList", postList);
	
		return map;
	}
	
	public BlogVo getAdmin(String id) {
		long userNo = blogDao.get(id);
		return blogDao.get(userNo);
	}
	public boolean adminUpdata(long userNo, BlogVo blogVo) {
		return blogDao.update(userNo,blogVo);
	}
	public List<CategoryVo> adminCategory(long userNo){
		List<CategoryVo> categoryList= categoryDao.getList(userNo);
		return categoryList;
	}
	public long adminPutCategory(long userNo,CategoryVo categoryVo){		
		return categoryDao.insert(userNo, categoryVo);
	}
	public boolean adminDeleteCategory(long userNo,long categoryNo){		
		return categoryDao.delete(userNo, categoryNo);
	}
	public boolean write(long categoryNo, PostVo postVo) {
		return postDao.insert(categoryNo,postVo); 
	}
}
