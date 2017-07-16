package com.quincy.rouse.session;

import java.util.List;

/**
 * Session接口
 * @author quincy
 *
 */
public interface ISession<T> {
	
	void save(Object obj);
	
	void update(Object obj);
	
	//默认的是根据主键来删除数据
	void delete(Object obj);
	
	List<T> findAll(Class<?> clazz);

	T get(T t);

}
