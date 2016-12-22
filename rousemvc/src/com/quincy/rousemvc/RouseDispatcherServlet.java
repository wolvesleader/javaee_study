package com.quincy.rousemvc;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quincy.rousemvc.annotation.Controller;
import com.quincy.rousemvc.annotation.RequestMapping;
import com.quincy.rousemvc.handler.Handler;
import com.quincy.rousemvc.pojo.User;

/**
 * 需要做的事情是，在请求来之前需要把所有的Controller对象给获取到存储在一个集合中
 * 等到请求过来之后解析请求的路径，找到与之相匹配的Controller，然后执行Controller中的方法
 * 
 * @author quincy
 * 
 */
public class RouseDispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private URL resource;

	@Override
	public void init() throws ServletException {
		// 加载控制器
		// loadController();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		String contextPath = context.getContextPath();
		System.out.println(contextPath + "  -----");
		String realPath = context.getRealPath("/");
		System.out.println(realPath + " *********");
		resource = this.getClass().getResource("/");
		System.out.println(resource.getPath());
		// 获取到classes目录下的所有的字节吗文件，在获取到字节吗文件的
		loadController(null);
		getMethod();
		
		for (Map.Entry<String, Handler> item : hs.entrySet()) {
			System.out.println(item.getKey() + "＊＊＊＊＊＊" + item.getValue());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	private List<Class<?>> clazzList = new ArrayList<>();
	
	// 需要获取到所有的使用注解的类
	private void loadController(String path) {
		List<String> classNameLists = getClassName(resource.getPath(), new ArrayList<String>());
		for (String className : classNameLists) {
			System.out.println(className + " pppp----");
            try {  
                Class<?> c = Class.forName(className);  
                //判断一个类上是否加了Controller注解
                //获取到加有注解的类之后，需要获取到该类中的所有的方法
                if (c.isAnnotationPresent(Controller.class)) {  
                	clazzList.add(c);  
                }  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            }  
		}
	}

	private List<String> getClassName(String path, ArrayList<String> lists) {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				getClassName(f.getAbsolutePath(), lists);
			} else {
				String absolutePath = f.getAbsolutePath();
				System.out.println(absolutePath);
				if (absolutePath.endsWith(".class")) {
					String pathsub = resource.getPath();
					String substring = absolutePath.substring(pathsub.length());
					String replace = substring.replace("/", ".").replace(".class", "");
					System.out.println(replace);
					lists.add(replace);
				}
			}
		}

		return lists;
	}
	
	/**
	 * 获取到所有的方法之后需要，获取到加油注解的方法
	 */
	
	Map<String, Handler> hs = new LinkedHashMap<>();
	
	private void getMethod(){
		 Method[] ms = null;  
	        String rm = null;  
	        for (Class<?> c : clazzList) {  
	            ms = c.getMethods();  
	            String mappingUrl = "";  
	            for (Method m : ms) {  
	                if (m.isAnnotationPresent(RequestMapping.class)) {  
	                    mappingUrl = this.getServletContext().getContextPath()  
	                            + m.getAnnotation(RequestMapping.class).value()  
	                                    .trim();  
	                    rm = m.getAnnotation(RequestMapping.class).method()  
	                            .trim().toUpperCase();  
	                    try {  
	                        hs.put(mappingUrl + rm, new Handler(c.newInstance(), m, rm));
	                    } catch (InstantiationException e) {  
	                        e.printStackTrace();  
	                    } catch (IllegalAccessException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	        } 
	}
	
	/**
	 * 获取到请求对请求路径进行处理
	 * @param request
	 * @param response
	 * 请求到来之后首先需要解析请求，同时创建Handler
	 */
	private void doService(HttpServletRequest request,HttpServletResponse response){
		Handler handler = getHandler(request, response);
		String url = invokeMappedMethod(handler,request,response);
		
		System.out.println(url + "   77777777777777");
		loadView(url,request,response);
	}
	
	/**
	 * 请求转发的方法
	 * @param url
	 * @param request
	 * @param response
	 */
	private void loadView(String url, HttpServletRequest request,HttpServletResponse response){
			
		String viewPath = "/";
		
		try {
			this.getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
	}
	
	private Handler getHandler(HttpServletRequest request,HttpServletResponse response){
		return hs.get(request.getRequestURI() + request.getMethod().toUpperCase());
	}
	
	
	private String invokeMappedMethod(Handler handler,HttpServletRequest request,HttpServletResponse response){
		String url = "";
		//需要获取到表单中的的参数，表单中的参数的属性和javaBean中的是相同的
	
		try {
			//需要检测方法的参数列表
			Class<?>[] parameterTypes = handler.getMappingMethod().getParameterTypes();
			Object[] args = new Object[parameterTypes.length];
			
			for(int i = 0; i < parameterTypes.length ; i ++){
				if(parameterTypes[i].getName().equals("javax.servlet.http.HttpServletRequest")){
					args[i] = request;
					continue;
				}
				if(parameterTypes[i].getName().equals("javax.servlet.http.HttpServletResponse")){
					args[i] = response;
					continue;
				}else{
					Field[] declaredFields = parameterTypes[i].getDeclaredFields();
					Object object = parameterTypes[i].newInstance();
					for (Field field : declaredFields) {
						 String parameter = request.getParameter(field.getName());
						 PropertyDescriptor pd = new PropertyDescriptor(field.getName(),parameterTypes[i]);
						 Method setMethod = pd.getWriteMethod();
				         setMethod.invoke(object, parameter);
					}
					args[i] = object;
					continue;
				}
			}
			
			url = (String) handler.getMappingMethod().invoke(handler.getController(), args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return url;
	}
	
}
