package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.respository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public boolean join(UserVo userVo) {		
		return userDao.insert(userVo);
	}
	
	public UserVo login(UserVo userVo) {		
		return userDao.get(userVo);
	}
	
	public UserVo existId(String id) {
		return userDao.get(id); 
	}
}
