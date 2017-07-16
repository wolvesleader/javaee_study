package com.quincy.pojo;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//id字段必须要手动提供不能使用，数据库中的自增长
	private String userid;
	private String username;
	private String password;

	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
}
