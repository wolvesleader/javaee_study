package com.quincy.dao;

import java.net.URL;

import com.quincy.test.Test;

public class DemoDao {
	
	public void add(){
		//获取到服务器端的资源文件的路径
		URL resource = Test.class.getClassLoader().getResource("test.properties");
		System.out.println(resource);
		//通过类的字节吗文件获取到资源文件
		URL resource2 = Test.class.getResource("test1.properties");
		System.out.println(resource2);
		
		URL resource3 = this.getClass().getResource("test1.properties");
		System.out.println("resource3: " + resource3);
		
		
		URL resource4 = this.getClass().getResource("/");
		System.out.println(resource4);
		
		URL resource5 = this.getClass().getResource("/config/test2.properties");
		System.out.println(resource5);
		
		URL resource6 = this.getClass().getClassLoader().getResource("config/test2.properties");
		System.out.println(resource6);
		
		URL resource7 = Thread.currentThread().getContextClassLoader().getResource("/");
		System.out.println("resource7: " + resource7);
		
	}

}
