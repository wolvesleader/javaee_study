package com.quincy.test;

import java.net.URL;

/**
 * 类加载器有三种
 * 启动类加载器
 * 
 * @author quincy
 *    // path不以’/'开头时，默认是从此类所在的包下取资源；  
    // path  以’/'开头时，则是从ClassPath根下获取； 
    Java中取资源时,经常用到Class.getResource和ClassLoader.getResource,这里来看看他们在取资源文件时候的路径问题 
 * TestMain.class.getResource("/") == t.getClass().getClassLoader().getResource("")
 */
public class Test {
	
	@org.junit.Test
	public void testDemo(){
		//String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		//System.out.println(path);
		//通过类加载器获取到资源文件
		URL resource = Test.class.getClassLoader().getResource("test.properties");
		System.out.println(resource);
		//通过类的字节吗文件获取到资源文件
		URL resource2 = Test.class.getResource("test1.properties");
		System.out.println(resource2);
		
		URL resource3 = this.getClass().getResource("test1.properties");
		System.out.println(resource3);
		
		
		URL resource4 = this.getClass().getResource("/");
		System.out.println(resource4);
		
		URL resource5 = this.getClass().getResource("/config/test2.properties");
		System.out.println(resource5);
		
		URL resource6 = this.getClass().getClassLoader().getResource("config/test2.properties");
		System.out.println(resource6);
		
		URL resource7 = Thread.currentThread().getContextClassLoader().getResource("/");
		System.out.println(resource7);
		
	}

}
