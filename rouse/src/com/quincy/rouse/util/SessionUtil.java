package com.quincy.rouse.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class SessionUtil {
	
	//存在的问题需要多次通过反射获取到对象的字段
	//感觉xml文件没有太大的作用，遵循约定大于配置的思想
	
	//设置一个缓存，主要是解决每执行一个数据库操作，都需要通过反射获取一次对象的值的问题
	public static LinkedHashMap<String, String> linkedHashMap;
	
	public static LinkedHashMap<String, String> fieldValue(Object obj){
		linkedHashMap = new LinkedHashMap<String,String>();
		try {
			Class<?> clazz = obj.getClass();
			linkedHashMap.put(clazz.getSimpleName().toLowerCase(), clazz.getSimpleName().toLowerCase());
			Field[] fields = clazz.getDeclaredFields();
			for(int i = 1 ; i < fields.length ; i ++){
				String filename = fields[i].getName();
				try {
					 PropertyDescriptor pd = new PropertyDescriptor(filename,clazz);
					 Method getMethod = pd.getReadMethod();//获得get方法
			         Object o = getMethod.invoke(obj);//执行get方法返回一个Object
			        
			         if(fields[i].getType() == java.lang.String.class){
			        	 linkedHashMap.put(filename, "'" + o.toString() + "'");
			         }else{
			        	 linkedHashMap.put(filename, o.toString());
			         }
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linkedHashMap;
	}
	
	/**
	 * 是否初始化集合
	 * @return
	 */
	public static boolean isBuffer(String clazzName){
		return linkedHashMap != null && linkedHashMap.size() > 0 && linkedHashMap.keySet().contains(clazzName) ? true :false;
	}
	
	public static String subSQLString(StringBuffer buffer){
		return buffer.delete(buffer.length() - 1, buffer.length()).toString();
	}
	
	public static StringBuffer getUpdateSQLString(Object obj,StringBuffer buffer,LinkedHashMap<String,String> linkedMap){
		for (Entry<String, String> item : linkedMap.entrySet()) {
			if(item.getKey().equals(obj.getClass().getSimpleName().toLowerCase())){
				buffer.append(linkedMap.get(obj.getClass().getSimpleName().toLowerCase()))
				.append(" set ");
			}else{
				buffer.append(item.getKey())
				.append(" = ")
				.append(item.getValue())
				.append(",");
			}
		}
		return buffer;
	}
	
	//delete from 表名 where 条件判断
	public static String getDeleteSQLString(Object obj,LinkedHashMap<String,String> linkedMap){
		StringBuffer buffer = new StringBuffer("delete from ");
		String string = linkedMap.get(obj.getClass().getSimpleName().toLowerCase());
		buffer.append(string)
		.append(" where ");
		//主要是为了获取到集合中第二个位置的数据
		int temp = 0;
		for (Entry<String, String> item : linkedMap.entrySet()) {
			 ++ temp;
			if(temp == 2){
				buffer.append(item.getKey())
				.append(" = ")
				.append(item.getValue());
				break;
			}
		}
		buffer.append(";");
		return buffer.toString();
	}
	
	//// select * from tablename where id = 1;
	public static <T> String findByIdSQLString(T t,LinkedHashMap<String,String> linkedMap){
		StringBuffer buffer = new StringBuffer("select * from ");
		String string = linkedMap.get(t.getClass().getSimpleName().toLowerCase());
		buffer.append(string)
		.append(" where ");
		//主要是为了获取到集合中第二个位置的数据
		int temp = 0;
		for (Entry<String, String> item : linkedMap.entrySet()) {
			 ++ temp;
			if(temp == 2){
				buffer.append(item.getKey())
				.append(" = ")
				.append(item.getValue());
				break;
			}
		}
		buffer.append(";");
		return buffer.toString();
	}

}
