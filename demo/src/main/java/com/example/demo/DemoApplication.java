package com.example.demo;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.quincy.servlet.LoginServlet;



@SpringBootApplication
//@ServletComponentScan(value="com.quincy.servlet")
//@ImportResource("applicationContext.xml")
public class DemoApplication {
  
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		// TODO Auto-generated method stub //myServlet/* 为访问servlet的路径
		return new ServletRegistrationBean(new LoginServlet(), "/login");
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	
}
