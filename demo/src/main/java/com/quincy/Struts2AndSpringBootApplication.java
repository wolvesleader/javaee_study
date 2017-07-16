package com.quincy;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication

//@ImportResource("applicationContext.xml")也可以不加默认的是从这里开始的
//@EntityScan(value="com.quincy.*")
@ComponentScan(basePackages={"com.quincy","com.example"}) 
public class Struts2AndSpringBootApplication {
		public static void main(String[] args) {
			SpringApplication.run(Struts2AndSpringBootApplication.class, args);
		}
		
		@Bean
		public FilterRegistrationBean myFilterRegistration() {
		    FilterRegistrationBean registration = new FilterRegistrationBean();
		    registration.setFilter(new org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter());
		    registration.setOrder(2);
		    registration.addUrlPatterns("/*");

		    return registration;
		}
		
	    @Bean
	    public HibernateJpaSessionFactoryBean sessionFactory() {
	        return new HibernateJpaSessionFactoryBean();
	    }
	    
		/**
		 * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
		 * @return
		 */
		@Bean
		public HttpMessageConverters fastJsonHttpMessageConverters() {
			// 1、需要先定义一个 convert 转换消息的对象;
			FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
			
			//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
			FastJsonConfig fastJsonConfig = new FastJsonConfig();
			fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
			
			//3、在convert中添加配置信息.
			fastConverter.setFastJsonConfig(fastJsonConfig);
			
			
			HttpMessageConverter<?> converter = fastConverter;
			return new HttpMessageConverters(converter);
		}
		
		

}
