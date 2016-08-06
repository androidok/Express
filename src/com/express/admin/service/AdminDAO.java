package com.express.admin.service;

import java.util.List;

import com.express.model.Admin;
import com.express.model.Order;
import com.express.model.User;
import com.express.model.UserTemp;


/**
 * 管理员业务接口
 * @author Administrator
 *
 */
public interface AdminDAO {

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public boolean adminLogin(Admin admin);
	
	/**
	 * 获取所有审核信息
	 * @return
	 */
	public List<UserTemp> findAllUserIdentity();
	
	/**
	 * 新增管理员
	 */
	public boolean addAdmin(Admin admin);
	
	/**
	 * 更新用户身份信息
	 */
	public void updateIdentity(User user);
	
	/**
	 * 删除已审查的信息
	 */
	public void deleteRecord(UserTemp userTemp);
	
	/**
	 * 查找订单
	 */
	public Order findOrder(Order order);
	
	/**
	 * 关闭订单
	 * @param order
	 */
	public void closeOrder(Order order);
}
