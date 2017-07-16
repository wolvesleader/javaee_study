package com.quincy.service.impl;

import java.util.List;

import com.quincy.dao.UserDao;
import com.quincy.dao.impl.UserDaoImpl;
import com.quincy.pojo.Page;
import com.quincy.pojo.User;
import com.quincy.rouse.core.RouseProxy;
import com.quincy.service.UserService;

public class UserServiceImpl implements UserService{
	public void save(User user){
		
		//try {
			
			//RouseUtil.openTransaction();
			UserDao dao = new UserDaoImpl();
			//RouseProxy proxy = new RouseProxy(dao);
			//UserDao newProxyInstance = (UserDao) proxy.newProxyInstance();
			
			//newProxyInstance.save(user);
			dao.save(user);
			//int i = 1/0;
			//System.out.println(i);
			user.setUserid("userids23d0");
			user.setUsername("miaoqing");
			user.setPassword("123456");
			
			dao.save(user);
			//newProxyInstance.save(user);
			//RouseUtil.commitTransaction();
	}

	public void update(User user) {
		UserDao dao = new UserDaoImpl();
		RouseProxy proxy = new RouseProxy(dao);
		UserDao newProxyInstance = (UserDao) proxy.newProxyInstance();
		newProxyInstance.save(user);
	}

	public void delete(User user) {
		UserDao dao = new UserDaoImpl();
		dao.delete(user);
	}
	
	public void get(User user){
		UserDao dao = new UserDaoImpl();
		dao.get(user);
	}

	public List<User> findAll(Class<User> class1) {
		UserDao dao = new UserDaoImpl();
		return dao.findAll(class1);
	}

	public List<User> getUserByPage(Class<User> clazz,Page page) {
		UserDao dao = new UserDaoImpl();
		return dao.getByPage(clazz, page);
		
	}

}
