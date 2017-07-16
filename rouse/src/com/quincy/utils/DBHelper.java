package com.quincy.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 *
 * @author quincy
 *
 */
public class DBHelper {
	
	public static final ComboPooledDataSource cpds = new ComboPooledDataSource();

	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return cpds.getConnection();
	}
	/**
	 * 释放数据库各种资源
	 * @param rs
	 * @param st
	 * @param cn
	 */
	public static void close(ResultSet rs, Statement st, Connection cn){

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
}
