package com.quincy.rouse.rousejdbc.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.quincy.rouse.rousejdbc.IRouseJdbc;

/**
 * rouse对c3p0数据库连接池的支持
 * @author quincy
 *
 */
public class RouseC3p0 implements IRouseJdbc{
	
	private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	private static final ThreadLocal<Connection> thread = new ThreadLocal<Connection>();
	
	private RouseC3p0(){
		
	}
	
	private static RouseC3p0 s = null;

    public static RouseC3p0 getSingleTon(){
           if( s == null){
                //位置1
                synchronized (RouseC3p0. class) {
                     /*判断没有对象创建的时候，就去创建*/
                     /*如果删除里边这一层判断
                     * 当第一个线程进入第一个if判断快以后，假如他停在了位置1处
                     * 当第二个线程进入以后，在第一个线程new完对象之后，第二个线程任然会new一个对象
                     *
                     * 加上第二个if判断之后，在第一个线程new完之后，第二个线程进入发现已经有对象了，
                     * 就不会再去new对象了
                     * */
                     if( s == null){
                           s = new RouseC3p0();
                    }
               }
          }
          return s;
    }
	//不需要开启事务的时候可以调用改方法获取连接
	public Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 把Connection绑定在当前的线程中
	 */
	public Connection getConnectionFromThread(){
		Connection conn = thread.get();
		if(conn == null){
			conn = getConnection();
			thread.set(conn);
		}
		return conn;
	}
}
