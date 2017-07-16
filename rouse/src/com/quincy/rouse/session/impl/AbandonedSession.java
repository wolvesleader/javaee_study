package com.quincy.rouse.session.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.quincy.rouse.util.AbandonedStringUtil;
import com.quincy.utils.DBHelper;

/**
 * 废弃到Session类，不建议使用
 * @author quincy
 *
 */
public class AbandonedSession {
	
	public void  save(Object obj){
		
		try {
			//获取到范型的真实的类型
			//需要拼接一个sql语句
			//insert into t_user values (?,?);
			//获取到传入对象的自己吗对象
			Class<?> clazz = obj.getClass();
			
			//user.rouse.xml
			System.out.println(clazz.getSimpleName() + "----");
			
			//通过字节吗，和反射获取到内部的方法
			//获取到需要操作到的数据库的表
			Field tableName = clazz.getDeclaredField("tableName");
		
			StringBuffer buffer = new StringBuffer("insert into ");
			buffer.append(tableName.get(obj))
			.append(" values ")
			.append("(");
			//insert into t_user values ( 'sdv' ,23);
			//获取到javaBean中所有字段的值
			Field[] fields = clazz.getDeclaredFields();
			for(int i = 0 ; i < fields.length ; i ++){
				System.out.println(fields[i].getName());
				String functionName = fields[i].getName();
				if("tableName".equals(functionName)){
					//System.out.println("什么都不用去做");
				}else{
					String functionNameToUppercase = AbandonedStringUtil.getFirstCharUppercase(functionName);
					//getUsername()
					Method method = clazz.getDeclaredMethod("get" + functionNameToUppercase);
					Object value = method.invoke(obj);
					
				    //需要检测传入的数据类型
					if(fields[i].getType() == java.lang.String.class){
						buffer.append("'")
						.append(value)
						.append("'");
					}else{
						buffer.append(value);
					}
					
					//需要取消掉最后一个逗号,需要减去2的原因是有一个没有提供get方法的字段
					if(i != fields.length - 2){
						buffer.append(" , ");
					}else{
						buffer.append(");");
					}
				}
			}
			
			//执行sql语句
			Connection conn = DBHelper.getConnection();
			PreparedStatement prepareStatement = conn.prepareStatement(buffer.toString());
			
			prepareStatement.executeUpdate();
			System.out.println(buffer.toString());
			
			//return prepareStatement.executeUpdate();
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
