package com.quincy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.quincy.dao.UserDao;
import com.quincy.domain.User;
import com.quincy.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public void save(User user) {
		
		userDao.save(user);
	}

}
