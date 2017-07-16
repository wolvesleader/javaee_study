package com.quincy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.ehcache.config.Configuration;
import org.ehcache.core.EhcacheManager;
import org.ehcache.xml.XmlConfiguration;

import com.quincy.servlet.UserServlet;
import com.quincy.utils.CacheUtil;

public class EhcacheFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain arg2) throws IOException, ServletException {
		  Configuration xmlConfig = new XmlConfiguration(UserServlet.class.getResource("/ehcache.xml"));
		  EhcacheManager cacheManager=CacheUtil.cacheManager;
		  if(cacheManager == null){
			  cacheManager = new EhcacheManager(xmlConfig);
			  cacheManager.init();
			  CacheUtil.cacheManager = cacheManager;
		  }
		  arg2.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
