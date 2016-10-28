package com.quincy.test;

import java.net.URL;

public class Demo {
	
	public static void main(String[] args) {
		String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		System.out.println(path);
		URL resource = Demo.class.getClassLoader().getResource("/config/test.properties");
		System.out.println(resource);
	}

}
