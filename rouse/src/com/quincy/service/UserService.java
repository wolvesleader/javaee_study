package com.quincy.service;

import java.util.List;

import com.quincy.pojo.Page;
import com.quincy.pojo.User;

public interface UserService {
	
	public void save(User user);

	public void update(User user);
	public void delete(User user) ;
	public void get(User user);

	public List<User> findAll(Class<User> class1) ;

	public List<User> getUserByPage(Class<User> clazz,Page page) ;

}
