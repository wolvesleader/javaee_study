package com.quincy.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JsonpDemoServlet extends HttpServlet{
	
	
	private volatile String str;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		System.out.println("jsonp");
		try { 
		    response.setContentType("text/plain"); 
		    response.setHeader("Pragma", "No-cache"); 
		    response.setHeader("Cache-Control", "no-cache"); 
		    response.setDateHeader("Expires", 0); 
		    Map<String,String> map = new HashMap<String,String>();  
		    map.put("result", "content"); 
		    PrintWriter out = response.getWriter();    
		    String resultJSON = JsonUtil.builderJsonFromJavaBean(map); //根据需要拼装 
		    String jsonpCallback = request.getParameter("callbackparam");//客户端请求参数 
		    out.println(jsonpCallback+"("+resultJSON+")");//返回jsonp格式数据 
		    out.flush(); 
		    out.close(); 
		   } catch (IOException e) { 
		    e.printStackTrace(); 
		   }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
