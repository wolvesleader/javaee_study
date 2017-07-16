package com.quincy.servlet;

import org.junit.Test;

import com.quincy.pojo.User;
import com.quincy.rouse.core.RouseProxy;
import com.quincy.service.UserService;
import com.quincy.service.impl.UserServiceImpl;

/**
 * 测试开启事务的提交
 * @author quincy
 *
 */
public class TestDemo {
	
	@Test
	public void testdemo(){
		
		User user = new User();
		
		user.setUserid("12345v11");
		user.setUsername("miaoqing");
		user.setPassword("123456");
		//做Service层的代理
		UserService demoService = (UserService) new RouseProxy(new UserServiceImpl()).newProxyInstance();
		demoService.save(user);

		//demoService.update(user);
	
	}

}
