package com.quincy.core.base;

import java.util.List;

import com.quincy.pojo.Page;


public interface BaseDao<T> {
	
	public void save(T t);

	public void update(T t);

	public void delete(T t);
	
	public void get(T t);

	public List<T> findAll(Class<T> clazz);
	
	List<T> getByPage(Class<T> clazz,Page page);


}
