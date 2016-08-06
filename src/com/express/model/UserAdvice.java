package com.express.model;

import java.util.Date;

public class UserAdvice {
	private String id;
	private String mobile;
	private String advice;
	private Date date;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Date getDate() {
		return new Date();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
