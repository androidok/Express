package com.express.model;

/**
 * 管理员类
 * @author Cbs
 *
 */
public class Admin {

	private String id;
	private String username;//管理员用户名
	private String password;//管理员密码
	private String role;//角色
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
