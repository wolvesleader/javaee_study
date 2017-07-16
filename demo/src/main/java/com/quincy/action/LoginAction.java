package com.quincy.action;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ResponseBody;

import com.quincy.domain.User;
import com.quincy.service.UserService;


public class LoginAction extends BaseAction<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1418633646840613480L;
	//private User user = new User();
	@Resource
	private UserService userService;


	
	public String login(){
		System.out.println("login invoke....");
		System.out.println(t.getUsername());
		userService.save(t);
		return SUCCESS;
	}
	
	
	public void returnjson(){
		User user = new User();
		user.setUid(12);
		user.setUsername("quincy");
		user.setPassword("123456");
		
		writeJson(user);
		
	}
	

}
