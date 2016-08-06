package com.express.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.express.admin.service.AdminDAO;
import com.express.admin.service.impl.AdminDAOImpl;
import com.express.model.Admin;
import com.express.model.Order;
import com.express.model.User;
import com.express.model.UserTemp;
import com.express.util.IdentityCheck;
import com.express.util.JsonUtil;
import com.express.util.MD5Util;
import com.express.util.SuperAction;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class AdminAction extends SuperAction{
	AdminDAO dao = new AdminDAOImpl();
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	User user = new User();
	Order order = new Order();
	UserTemp userTemp = new UserTemp();
	
	/**
	 * 管理员登录
	 * @return
	 */
	public String login()
	{
		AdminDAO adminDAO=new AdminDAOImpl();
		
		Admin admin = new Admin();
		//获取管理员登录名和密码
		String username = request.getParameter("username");
		String password = MD5Util.LoginEncryption(request.getParameter("password"));
		admin.setUsername(username);
		admin.setPassword(password);
		
		if(adminDAO.adminLogin(admin))
		{
			session.setAttribute("username", admin.getUsername());
			return "login_success";
		}
		else if ("admin".equals(username)&&MD5Util.LoginEncryption("admin").equals(password)) {
			adminDAO.addAdmin(admin);
			session.setAttribute("username", admin.getUsername());
			return "login_success";
		}
		else {
			return "login_failure";
		}
		
	}
	
	/**
	 *对管理员登录进行校验 	
	 */
	@Override
	public void validate() {
		
		AdminDAO adminDAO=new AdminDAOImpl();
		
		Admin admin = new Admin();
		String username = request.getParameter("username");
		String password = MD5Util.LoginEncryption(request.getParameter("password"));
		admin.setUsername(username);
		admin.setPassword(password);

		if(!("admin".equals(username)&&MD5Util.LoginEncryption("admin").equals(password)))
		{
		if(!adminDAO.adminLogin(admin))
		{
			this.addFieldError("loginError","用户名或密码错误");
		}
		}
	}
	
	/**
	 * 查找所有需要检查的信息
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SkipValidation
	public String getAllUserIdentity() throws ServletException, IOException{
		AdminDAO dao=new AdminDAOImpl();
		List<UserTemp> allUser = dao.findAllUserIdentity();
		request.setAttribute("user", allUser);
		return "finish";
	}
	
	/**
	 * 检测身份证是否合法
	 * @throws IOException
	 */
	@SkipValidation
	public void identityCheck() throws IOException{
		String contentType = "text/html;charset=utf-8"; 
		//指定输出内容类型和编码  
        ServletActionContext.getResponse().setContentType(contentType);   
        //获取输出流，然后使用  
        PrintWriter out = ServletActionContext.getResponse().getWriter();   
		String identity = request.getParameter("identity");
		if (IdentityCheck.Check(identity)) {
			out.print("身份证合法");
		}else{
			out.print("身份证不合法");
		}
	}
	
	
	/**
	 * 审核用户信息
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SkipValidation
	public void pass() throws JsonGenerationException, JsonMappingException, IOException{
		String judge = request.getParameter("judge");
		String id = request.getParameter("id");
		String identity = request.getParameter("identity");
		if(judge.equals("pass")){
			user.setId(id);
			user.setIdentity(identity);
			dao.updateIdentity(user);
			dataMap.put("status", "审核完毕");
			JsonUtil.writeToResponse(dataMap);
		}else {
			dataMap.put("status", "审核完毕");
			JsonUtil.writeToResponse(dataMap);
		}
		//删除userTemp表的记录
		userTemp.setId(id);
		dao.deleteRecord(userTemp);
	}
	
	/**
	 * 查找订单
	 * @return
	 */
	@SkipValidation
	public String findOrder(){
		String orderId = request.getParameter("orderId");
		order.setOrderId(orderId);
		request.setAttribute("order", dao.findOrder(order));
		return "order";
	}
	
	/**
	 *关闭订单
	 */
	@SkipValidation
	public void closeOrder(){
		String orderId = request.getParameter("orderId");
		order.setOrderId(orderId);
		dao.closeOrder(order);
	}
	
	
	
		
	
}
