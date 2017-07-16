package com.quincy.action;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * springboot不提供fastjson和struts2的整合，所以我们只能自己实现
 * @author quincy
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class<T> clazz ;
	
	public T t;
	
	@SuppressWarnings("unchecked")
	public BaseAction() {
		
		Type type = getClass().getGenericSuperclass();  
        if (type instanceof ParameterizedType) {  
            this.clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];  
        } else {  
            this.clazz = null;  
        }  
	}
	
	@Override
	public T getModel() {
		try {
			t = (T) clazz.newInstance();
			return t;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
     * 将对象转换成JSON字符串，并响应回前台()
     * 
     * @param object
     * @throws IOException
     */
    public void writeJson(Object object) {
        try {

            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm");
            ServletActionContext.getRequest().getSession();
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(json);
            ServletActionContext.getResponse().getWriter().flush();
            ServletActionContext.getResponse().getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param object
     */
    public void writeJsonOut(Object object){
        OutputStream outputStream=null;
        String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm");
        ServletActionContext.getRequest().getSession();
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        HttpServletResponse response = ServletActionContext.getResponse();
        byte[] jsonby;
        try {
            jsonby = json.toString().getBytes("utf-8");
            response.setContentLength(jsonby.length);
            outputStream=response.getOutputStream();
            outputStream.write(jsonby);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * 参数是已经封装好的json集合
     * @param map
     */
    public void writeJson(Map<String, Object>map) {
        OutputStream outputStream=null;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            request.getSession();
            byte[] jsonby=map.toString().getBytes("utf-8");
            response.setContentLength(jsonby.length);
            outputStream=response.getOutputStream();

            outputStream.write(jsonby);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
	
	

}
