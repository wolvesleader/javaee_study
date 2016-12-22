package com.quincy.rousemvc.web;

import javax.servlet.http.HttpServletRequest;

import com.quincy.rousemvc.annotation.Controller;
import com.quincy.rousemvc.annotation.RequestMapping;
import com.quincy.rousemvc.pojo.User;


/**
 * 如果需要是使用request的时候怎么使用
 * @author quincy
 *页面传入的数据怎么分装
 */

@Controller
public class UserController {
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,User user){
		System.out.println("index my   " + user.getName() + "---" + user.getPassword());
		return "index";
	}
	
	public String home(){
		return "home";
	}

}
