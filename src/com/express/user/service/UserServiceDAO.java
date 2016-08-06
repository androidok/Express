package com.express.user.service;

import java.util.List;

import com.express.model.User;
import com.express.model.UserAdvice;
import com.express.model.UserTemp;

public interface UserServiceDAO {

	public List<User> findNumber(String mobile);  //通过手机号查找用户
	 
	public boolean insertUser(User user);   //用户注册，插入数据
	
	public User findUserByPhone(String mobile); //通过手机号查找用户
	//上传用户认证信息
	public void idAuth(UserTemp userTemp); 
	
	public boolean updataUser(User user);//用户完善信息，暂时用不到（未完成）
	
	public boolean findUser(String mobile,String pwd);//检查手机号密码是否正确
	
	public void reLoginPwd(String mobile,String pwd);//修改登录密码
	
	public void changeIcon(User user);//用户修改头像
	
	public void saveAdvice(UserAdvice userAdvice);//用户建议
	
	public List<User> checkUser(User user) throws NoSuchFieldException;//检查用户
	
	public void rePayPwd(User user);//修改支付密码
	
	public void rePayPwdByPhone(User user);//通过手机号修改密码

}