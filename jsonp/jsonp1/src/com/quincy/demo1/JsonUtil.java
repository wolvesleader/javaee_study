package com.quincy.demo1;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@SuppressWarnings("unchecked")
public class JsonUtil {
	
	/**
	 * 把javaBean对象转换为json数据
	 * @param obj
	 * @return
	 */
	public static String builderJsonFromJavaBean(Object obj){
		Gson  gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static <T> T parseJsonToJavaBean(String json,Class<?> clazz){
		Gson gson = new Gson();
		T t = null;
		try {
			t = (T) gson.fromJson(json, clazz);
		} catch (Exception e) {
			System.out.println("解析Json有错误");
		}
		return t;
	}
	

	/**
	 * 把json字符串解析成集合
	 * 
	 * @param json
	 * @param type
	 *            new TypeToken<List<yourbean>>(){}.getType()
	 * @return
	 */
	public static List<?> parseJsonToList(String json,Type type){
		Gson gson = new Gson();
		List<?> lists = null;
		try {
			lists = gson.fromJson(json, type);
		} catch (Exception e) {
			System.out.println("解析Json有错误");
		}
		return lists;
	}
	
	public static HashMap<String, Object> parseJsonToMap(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
		HashMap<String, Object> map = null;
		try {
			map = gson.fromJson(json, type);
		} catch (Exception e) {
		}
		return map;
	}
	
	
}
