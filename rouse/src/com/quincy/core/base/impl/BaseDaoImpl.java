package com.quincy.core.base.impl;

import java.util.List;

import com.quincy.core.base.BaseDao;
import com.quincy.pojo.Page;
import com.quincy.rouse.session.impl.RouseSession;

public class BaseDaoImpl<T> implements BaseDao<T> {

	@Override
	public void save(T t) {
		RouseSession<T> session = new RouseSession<T>();
		session.save(t);
	}

	@Override
	public void update(T t) {
		RouseSession<T> session = new RouseSession<T>();
		session.update(t);
	}

	@Override
	public void delete(T t) {
		RouseSession<T> session = new RouseSession<T>();
		session.delete(t);
	}

	@Override
	public void get(T t) {
		RouseSession<T> session = new RouseSession<T>();
		session.get(t);
	}

	@Override
	public List<T> findAll(Class<T> clazz) {
		RouseSession<T> session = new RouseSession<T>();
		return session.findAll(clazz);
	}

	@Override
	public List<T> getByPage(Class<T> clazz,Page page) {
		RouseSession<T> session = new RouseSession<T>();
		return session.page(clazz, page);
	}

}
