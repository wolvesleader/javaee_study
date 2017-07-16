package com.quincy.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quincy.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<?> clazz;

	// 需要在构造其中获取到具体类类型
	@Autowired
	public BaseDaoImpl() {

		Class<?> clazz = this.getClass();
		Type superclass = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) superclass;
		Type[] arguments = pType.getActualTypeArguments();
		Type type = arguments[0];

		clazz = type.getClass();
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Integer id) {

		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
