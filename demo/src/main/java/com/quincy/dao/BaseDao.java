package com.quincy.dao;

import java.util.List;

public interface BaseDao<T> {
	
	public void save(T t);
	public void delete();
	public void update();
	public T findById(Integer id);
	public List<T> findAll();

}
