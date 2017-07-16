package com.quincy.rouse.session.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.quincy.pojo.Page;
import com.quincy.rouse.rousejdbc.impl.RouseC3p0;
import com.quincy.rouse.session.ISession;
import com.quincy.rouse.util.RouseUtil;
import com.quincy.rouse.util.SessionUtil;

/**
 * 
 * @author quincy
 *
 */

public class CopyOfRouseSession<T> implements ISession<T>{
	@Override
	public void  save(Object obj){
		//insert into user values(?,?,?);
		LinkedHashMap<String, String> linkedMap = SessionUtil.fieldValue(obj);
		
		StringBuffer buffer = new StringBuffer("insert into ");
		
		for (Entry<String, String> item : linkedMap.entrySet()) {
			if(item.getKey().equals(obj.getClass().getSimpleName().toLowerCase())){
				buffer.append(linkedMap.get(obj.getClass().getSimpleName().toLowerCase()))
				.append(" values ")
				.append("( ");
			}else{
				buffer.append(item.getValue()).append(",");
			}
		}
		
		buffer = buffer.delete(buffer.length() - 1, buffer.length());
		buffer.append(" );");
		
		System.out.println(buffer.toString());
		
		int result = executeUpdate(buffer.toString());
		
	}

	@Override
	public void update(Object obj) {
		//SessionUtil.linkedHashMap.clear();
		//update 表名 set 字段1 = 值1，字段2 = 值2  where 条件判断
		boolean isBuffer = SessionUtil.isBuffer(obj.getClass().getSimpleName().toLowerCase());
		StringBuffer buffer = new StringBuffer("update ");
		if(isBuffer){
			SessionUtil.getUpdateSQLString(obj,buffer, SessionUtil.linkedHashMap);
		}else{
			System.out.println("＝＝＝＝＝＝Update No Buffer＝＝＝＝＝＝");
			SessionUtil.getUpdateSQLString(obj,buffer, SessionUtil.fieldValue(obj));
		}
		
		String sqlString = SessionUtil.subSQLString(buffer);
		System.out.println(sqlString);
		
		RouseC3p0 rouseJdbc = RouseC3p0.getSingleTon();
		Connection connection = rouseJdbc.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlString);
			int result = ps.executeUpdate();
			System.out.println("插入： " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			RouseUtil.close(null, ps, connection);
		}
	}

	//delete from 表名 where 条件判断
	@Override
	public void delete(Object obj) {
		boolean isBuffer = SessionUtil.isBuffer(obj.getClass().getSimpleName().toLowerCase());
		
		String sqlString = null;
		
		if(isBuffer){
			sqlString = SessionUtil.getDeleteSQLString(obj,SessionUtil.linkedHashMap);
		}else{
			System.out.println("＝＝＝＝＝＝Delete No Buffer＝＝＝＝＝＝");
			sqlString = SessionUtil.getDeleteSQLString(obj, SessionUtil.fieldValue(obj));
		}
		
		System.out.println(sqlString);
		RouseC3p0 rouseJdbc = RouseC3p0.getSingleTon();
		Connection connection = rouseJdbc.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlString);
			int result = ps.executeUpdate();
			System.out.println("插入： " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			RouseUtil.close(null, ps, connection);
		}
	}

	//默认的是根据id来查询的
	// select * from tablename where id = 1;
	@Override
	public T get(T t) {
		boolean isBuffer = SessionUtil.isBuffer(t.getClass().getSimpleName().toLowerCase());
		String sqlString = null;
		if(isBuffer){
			sqlString = SessionUtil.findByIdSQLString(t,SessionUtil.linkedHashMap);
		}else{
			System.out.println("＝＝＝＝＝＝Select By id No Buffer＝＝＝＝＝＝");
			sqlString = SessionUtil.findByIdSQLString(t, SessionUtil.fieldValue(t));
		}
		System.out.println(sqlString);
		return null;
	}
	
	//select * from tablename;
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<?> clazz){
		ArrayList<T> lists = new ArrayList<T>();
		StringBuffer buffer = new StringBuffer("select * from ");
		buffer.append(clazz.getSimpleName().toLowerCase());
		System.out.println(buffer.toString());
		RouseC3p0 rouseJdbc = RouseC3p0.getSingleTon();
		Connection connection = rouseJdbc.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			Field[] fields = clazz.getDeclaredFields();
			while(rs.next()){
				T instance = (T) clazz.newInstance();
				for(int i = 1 ; i < fields.length ; i ++){
					String filename = fields[i].getName();
					Object objectValue = rs.getObject(filename);
				    //fields[i].set(instance, objectValue);
					PropertyDescriptor pd = new PropertyDescriptor(filename,clazz);
					Method setMethod = pd.getWriteMethod();//获取到set方法
			        setMethod.invoke(instance,objectValue);//执行set方法设置值
				}
				lists.add(instance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	/**
	 * 提供对分页的支持
	 * SELECT * FROM  user LIMIT ? OFFSET ?;
	 */
	@SuppressWarnings("unchecked")
	public List<T> page(Class<?> clazz ,Page page){
		ArrayList<T> lists = new ArrayList<T>();
		StringBuffer buffer = new StringBuffer("select * from ");
		buffer.append(clazz.getSimpleName().toLowerCase())
		.append(" limit ")
		.append(( page.getCurrentPageNumber() - 1 ) * page.getPageRecordNumber())
		.append(" , ")
		.append(page.getPageRecordNumber());
		System.out.println(buffer.toString());
		RouseC3p0 rouseJdbc = RouseC3p0.getSingleTon();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = rouseJdbc.getConnection();
			ps = connection.prepareStatement(buffer.toString());
			rs = ps.executeQuery();
			Field[] fields = clazz.getDeclaredFields();
			while(rs.next()){
				T instance = (T) clazz.newInstance();
				for(int i = 1 ; i < fields.length ; i ++){
					String filename = fields[i].getName();
					Object objectValue = rs.getObject(filename);
				    //fields[i].set(instance, objectValue);
					PropertyDescriptor pd = new PropertyDescriptor(filename,clazz);
					Method setMethod = pd.getWriteMethod();//获取到set方法
			        setMethod.invoke(instance,objectValue);//执行set方法设置值
				}
				lists.add(instance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			RouseUtil.close(rs, ps, connection);
		}
		return lists;
	}
	
	
	public int executeUpdate(String sql){
		RouseC3p0 rouseJdbc = RouseC3p0.getSingleTon();
		Connection connection = rouseJdbc.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			int result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			RouseUtil.close(null, ps, connection);
		}
		
		return 0;
	}
	
}
