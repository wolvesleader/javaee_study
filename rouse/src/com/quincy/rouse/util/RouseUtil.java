package com.quincy.rouse.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.quincy.rouse.rousejdbc.impl.RouseC3p0;
public class RouseUtil {
	
	/**
	 * 释放数据库各种资源
	 * @param rs
	 * @param st
	 * @param cn
	 */
	public static void close(ResultSet rs, PreparedStatement st, Connection cn){
		try {
			if(rs != null){
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(st != null){
				   st.close();
				   st = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(cn != null){
						cn.close();
						cn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 开启事务
	 * @param con
	 * @throws SQLException 
	 */
	public static void openTransaction() throws Exception{
		RouseC3p0.getSingleTon().getConnectionFromThread().setAutoCommit(false);;
	}
	
	public static void commitTransaction() throws Exception{
		RouseC3p0.getSingleTon().getConnectionFromThread().commit();
	}

	/**
	 * 事务的关闭
	 * @param con
	 * @throws SQLException 
	 */
	public static void closeTransaction() throws Exception{
		RouseC3p0.getSingleTon().getConnectionFromThread().close();;
	}
	
	
	public static void rollBackTransaction() throws Exception{
		RouseC3p0.getSingleTon().getConnectionFromThread().rollback();
	}

}
