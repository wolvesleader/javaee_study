package com.quincy.servlet;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ehcache.Cache;
import org.ehcache.core.EhcacheManager;
import org.junit.Test;

import com.quincy.pojo.Page;
import com.quincy.pojo.User;
import com.quincy.service.UserService;
import com.quincy.service.impl.UserServiceImpl;
import com.quincy.utils.CacheUtil;


/**
 * 学习目的把从数据库中获取到数据通过ehcache缓存下来
 * @author quincy
 *
 */
public class UserServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int count = 0;
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
        
		
		synchronized (resp) {
			
		}
			new ConcurrentHashMap<String, String>();			
		
		
		
		
		/*
		//EhcacheManager cacheManager=(EhcacheManager) req.getSession().getAttribute("cacheManager");
		EhcacheManager cacheManager= CacheUtil.cacheManager;
		Cache<String, User> basicCache = cacheManager.getCache("basicCache", String.class, User.class);
		*/
		String currentPageNumber = request.getParameter("currentPageNumber");
		
		UserService demoService = new UserServiceImpl();
		
		int initcurrentPageNumber = Integer.parseInt(currentPageNumber);
		
		Page page = new Page(initcurrentPageNumber,22);
		List<User> userByPage = demoService.getUserByPage(User.class,page);
		//从新计算其实页数
	    
		//System.out.println(page.getStartPageNumber() + "---" +page.getEndPageNumber());
		page.setLists(userByPage);
		for (User user : userByPage) {
			System.out.println(user);
		}
		
		System.out.println(page.getCurrentPageNumber() + "*************");
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/page.jsp").forward(request, resp);
		
		
		/*
		UserService demoService = new UserService();
		//for(int i = 0; i <1011; i ++){
			User user = new User();
			
			user.setUserid("userids23dd");
			user.setUsername("miaoqings");
			user.setPassword("passwords");
			
			
			demoService.save(user);
		//}
		
		*/
		/*
		
		demoService.update(user);
		
		demoService.delete(user);
		
		demoService.get(user);

	    User value1 = basicCache.get("123");
	    System.out.println(value1);
	    
	
		if(basicCache.containsKey("123")){
			User value = basicCache.get("123");
			System.out.println(value.toString());
		}else{
			 List<User> findAll = demoService.findAll(User.class);
				for (User user2 : findAll) {
					
					basicCache.put(user2.getUserid(), user2);
					User value = basicCache.get(user2.getUserid());
					System.out.println(value.toString());
				}
		}
		
		*/
	}
	
	
	/*
	
	private CacheManager cacheManager;  
	    
	   @Before  
	   public void before() {  
		   final URL myUrl = this.getClass().getResource("/ehcache.xml"); 
		   Configuration xmlConfig = new XmlConfiguration(myUrl);
		   cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
		   cacheManager.init();
	   }  
	    
	*/
	
	@Test
	public void testdemo(){
		
		User user = new User();
		
		user.setUserid("12345vb");
		user.setUsername("miaoqing");
		user.setPassword("123456");
		
		UserService demoService = new UserServiceImpl();
		demoService.save(user);
		
		/*
		demoService.update(user);
		
		demoService.delete(user);
		
		demoService.get(user);
		
	    Cache<String, User> basicCache = cacheManager.getCache("basicCache", String.class, User.class);
	    User value1 = basicCache.get("123");
	    System.out.println(value1);
	    
	
		if(basicCache.containsKey("123")){
			User value = basicCache.get("123");
			System.out.println(value.toString());
		}else{
			 List<User> findAll = demoService.findAll(User.class);
				for (User user2 : findAll) {
					
					basicCache.put(user2.getUserid(), user2);
					User value = basicCache.get(user2.getUserid());
					System.out.println(value.toString());
				}
		}
		     // basicCache.put(1L, "da one!");
		     // String value = basicCache.get(1L);
		      
		      //System.out.println(value);
		     
	}
*/
	}
}
