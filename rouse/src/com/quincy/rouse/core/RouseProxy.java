package com.quincy.rouse.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.quincy.rouse.util.RouseUtil;

/**
 * 动态代理类
 * @author quincy
 */
public class RouseProxy implements InvocationHandler{
	
	private Object target;
	
	public RouseProxy(Object target){
		this.target = target;
	}
	
	public Object newProxyInstance(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args){
		Object result = null;
		//事务的开启
		try {
			RouseUtil.openTransaction();
			result = method.invoke(target, args);
			RouseUtil.commitTransaction();
		} catch (Exception e) {
			try {
				RouseUtil.rollBackTransaction();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		//事务的关闭
		return result;
	}
	

}
