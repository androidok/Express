package com.express.user.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.express.database.dao.BaseDao;
import com.express.model.User;
import com.express.model.UserAdvice;
import com.express.model.UserTemp;
import com.express.user.service.UserServiceDAO;
import com.express.user.service.impl.UserServiceImpl;
import com.express.util.Constant;
import com.express.util.CookiesUtil;
import com.express.util.JsonUtil;
import com.express.util.MD5Util;
import com.express.util.MsgUtil;
import com.express.util.Struts2Util;
import com.express.util.SuperAction;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 用户业务逻辑处理类
 * @author Lwk Cbs
 *
 */
public class UserAction extends SuperAction {
	private UserServiceDAO dao = new UserServiceImpl();
	User user = new User();
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	
	
	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	/**
	 * @Title: code
	 * @Description: 获取验证码
	 * @return
	 * @throws Exception
	 */
		public void code(){				
			//获得注册的手机号
			String mobile = request.getParameter("mobile");
			System.out.println("传过来的手机号："+mobile);
			//获得验证码
//			String code = MsgUtil.msg(mobile);
			
			Random random = new Random();  
			int randomNumber =  (random.nextInt(89999) + 10000);
			String code = Integer.toString(randomNumber);
			System.out.println("验证码是："+code);	
			
			//将验证码和手机号保存在session中
			session.setAttribute("code", code);
			session.setAttribute("mobile", mobile);	
		}
		
		/**
		 * @Title: register
		 * @Description: 用户注册
		 * @return
		 * @throws Exception
		 */
		public String register() throws JsonGenerationException, JsonMappingException, IOException{
			
			//取出验证码
			String code = (String) session.getAttribute("code");
			
			//取出手机号
			String mobile =(String)session.getAttribute("mobile");
			
			if(!(code.equals(request.getParameter("code"))&&mobile.equals(request.getParameter("mobile")))){
				dataMap.put("status", "验证码不正确");
				JsonUtil.writeToResponse(dataMap);
				return null;
			}else if(dao.findNumber(mobile).size()>0){
				dataMap.put("status", "用户已存在");
				JsonUtil.writeToResponse(dataMap);
				return null;
			}else{
				User user = new User();
				user.setId(MD5Util.getUUID());
				user.setMobile(mobile);
				user.setLoginPassword(MD5Util.LoginEncryption(request.getParameter("loginPassword")));
				user.setRole(request.getParameter("role"));
				user.setState("no");
				dao.insertUser(user);
				dataMap.put("status", "注册成功");
				JsonUtil.writeToResponse(dataMap);
				return null;
			}
		}
	
	
	

	/**
	 * @Title: auth
	 * @Description: 用户上传认证信息
	 * @return
	 * @throws Exception
	 */
	public void auth() throws JsonGenerationException, JsonMappingException, IOException{
			    		
		String keepPath = ServletActionContext.getServletContext().getRealPath("/images/identity");
		File file=new File(keepPath);
			if(!file.exists())
			{
				file.mkdir();
			}
			for (int i=0;i<upload.size();i++) {
				FileUtils.copyFile(upload.get(i), new File(file, request.getParameter("mobile")+i+".jpg"));
			}						
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String identity = request.getParameter("identity");
			String mobile = request.getParameter("mobile");
			
			//三张图片的绝对路径
			String path1 = Constant.PATH_PREFIX+mobile+"0.jpg";
			String path2 = Constant.PATH_PREFIX+mobile+"1.jpg";
			String path3 = Constant.PATH_PREFIX+mobile+"2.jpg";
			if("".equals(name)&&"".equals(identity)){			
				dataMap.put("status", "姓名和身份证号不得为空");
				JsonUtil.writeToResponse(dataMap);			
			}else if(upload.size()<3){
				dataMap.put("status", "请添加三张身份证照片");
				JsonUtil.writeToResponse(dataMap);	
			}else {
				    UserTemp userTemp = new UserTemp();
				    userTemp.setId(id);
				    userTemp.setName(name);
				    userTemp.setIdentity(identity);
				    userTemp.setMobile(mobile);
				    userTemp.setPath1(path1);
				    userTemp.setPath2(path2);
				    userTemp.setPath3(path3);
				    dao.idAuth(userTemp);
					dataMap.put("status", "上传成功");
					JsonUtil.writeToResponse(dataMap);	
				}
	}
	
	/**
	 * @Title: loginByPwd
	 * @Description: 用户通过密码登录
	 * @return
	 * @throws Exception
	 */
	public void loginByPwd() throws JsonGenerationException, JsonMappingException, IOException{
		
		String mobile = request.getParameter("mobile");
		String loginPassword = request.getParameter("loginPassword");

		if(!(dao.findNumber(mobile).size()>0)){
			dataMap.put("status", "用户不存在");
			JsonUtil.writeToResponse(dataMap);	
		}else if(dao.findUser(mobile, loginPassword)){
			User user = dao.findUserByPhone(mobile);
			CookiesUtil.saveCookie(user, response);
			session.setAttribute("user", user);
			dataMap.put("status", "登录成功");
			dataMap.put("user", JsonUtil.beanToJson(user));
			JsonUtil.writeToResponse(dataMap);	
		}else{
			dataMap.put("status", "密码错误:");
			JsonUtil.writeToResponse(dataMap);	
		}
		
		
		
	}
	
	/**
	 * @Title: loginByPhone
	 * @Description: 用户通过验证码登录
	 * @return
	 * @throws Exception
	 */
	public void loginByPhone() throws JsonGenerationException, JsonMappingException, IOException{
		
		//取出验证码
		String code = (String) session.getAttribute("code");
		//取出手机号
		String mobile =(String)session.getAttribute("mobile");
		
		if(!(code.equals(request.getParameter("code"))&&mobile.equals(request.getParameter("mobile")))){
			dataMap.put("status", "验证码不正确");
			JsonUtil.writeToResponse(dataMap);
		}else if(dao.findNumber(mobile).size()>0){
			List<User> list = dao.findNumber(mobile);
			session.setAttribute("user", list);
			dataMap.put("user",JsonUtil.beanToJson(list));
			dataMap.put("status", "登录成功");
			JsonUtil.writeToResponse(dataMap);
		}else{
			dataMap.put("status", "用户不存在，请先注册");
			JsonUtil.writeToResponse(dataMap);
		}
		
	}
	
	/**
	 * @Title: loginByPhone
	 * @Description: 用户通过原密码修改密码
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws Exception
	 */
	public void reLoginPwdByOldPwd() throws JsonGenerationException, JsonMappingException, IOException{
		
		UserServiceDAO dao = new UserServiceImpl();
		User user = (User)Struts2Util.getSession("user");//从session获取手机号码
		String mobile = user.getMobile();
//		String mobile = request.getParameter("mobile");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		System.out.println("从session获取的手机号码是："+mobile);
		if("".equals(newPwd)){
			dataMap.put("status", "新密码不能为空");
			JsonUtil.writeToResponse(dataMap);
		}else if(dao.findUser(mobile, oldPwd)){
			dao.reLoginPwd(mobile, newPwd);
			dataMap.put("status", "修改成功");
			JsonUtil.writeToResponse(dataMap);
		}else{
			dataMap.put("status", "旧密码错误");
			JsonUtil.writeToResponse(dataMap);
		}
	}
	
	/**
	 * @Title: loginByPhone
	 * @Description: 用户通过验证码修改密码
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws Exception
	 */
	public void reLoginPwdByPhone() throws JsonGenerationException, JsonMappingException, IOException{
		
		UserServiceDAO dao = new UserServiceImpl();
		
		String code = (String) session.getAttribute("code");//获得验证码				
		String mobile =(String)session.getAttribute("mobile");//取出手机号
		String newPwd = request.getParameter("newPwd");
		
		if(!(code.equals(request.getParameter("code"))&&mobile.equals(request.getParameter("mobile")))){
			dataMap.put("status", "验证码不正确");
			JsonUtil.writeToResponse(dataMap);
		}else{
			dao.reLoginPwd(mobile, newPwd);
			dataMap.put("status", "修改成功");
			JsonUtil.writeToResponse(dataMap);
		}
		
	}
	/**
	 * @Title: logout
	 * @Description: 用户注销
	 * @return
	 */
	public void logout(){
		session.invalidate();
		CookiesUtil.clearCookie(Struts2Util.getResponse());
	}
	
	
	/**
	 * @throws IOException 
	 * 用户修改头像
	 * 
	 */
	public void reIcon() throws IOException{
		UserServiceDAO dao = new UserServiceImpl();
		try {
			String keepPath = ServletActionContext.getServletContext().getRealPath("/images/icon");
			File file=new File(keepPath);
			User user = (User)Struts2Util.getSession("user");//从session获取手机号码
			String mobile = user.getMobile();
			if(!file.exists())
			{
				file.mkdir();
			}else if(0<upload.size()){
				FileUtils.copyFile(upload.get(1), new File(file, mobile+".jpg"));
				String path = "/Express/images/icon/"+mobile+".jpg";
				user.setPath(path);			
			}
			dataMap.put("status", "修改成功");
			JsonUtil.writeToResponse(dataMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dataMap.put("status", "修改失败");
			JsonUtil.writeToResponse(dataMap);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 用户建议
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void advice() throws JsonGenerationException, JsonMappingException, IOException{
		UserAdvice userAdvice = new UserAdvice();
		userAdvice.setMobile(request.getParameter("mobile"));
		userAdvice.setAdvice(request.getParameter("advice"));
		dao.saveAdvice(userAdvice);
		dataMap.put("status", "提交成功");
		JsonUtil.writeToResponse(dataMap);
	}
	
	
	/**
	 * 设置支付密码
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void setPayPwd() throws JsonGenerationException, JsonMappingException, IOException{
		User user = new User();
		user.setId(request.getParameter("id"));
		System.out.println("获取到的id："+request.getParameter("id"));
		user.setPayPassword(request.getParameter("payPwd"));
		System.out.println("获取到的密码："+request.getParameter("payPwd"));
		if(request.getParameter("payPwd").equals(null)){
			dataMap.put("status", "密码不能为空");
			JsonUtil.writeToResponse(dataMap);
		}else {
			dao.rePayPwd(user);
			dataMap.put("status", "设置成功");
			JsonUtil.writeToResponse(dataMap);
		}
		
	}
	
	/**
	 * 通过原密码修改支付密码
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws NoSuchFieldException
	 */
	public void rePwdByPwd() throws JsonGenerationException, JsonMappingException, IOException, NoSuchFieldException{
		user.setId(request.getParameter("id"));
		user.setPayPassword(request.getParameter("oldPwd"));
		if(request.getParameter("newPwd").equals(null)){
			dataMap.put("status", "新密码不能为空");
			JsonUtil.writeToResponse(dataMap);
		}
		else if(dao.checkUser(user).size()>0){
			user.setPayPassword(request.getParameter("newPwd"));
			dao.rePayPwd(user);
			dataMap.put("status", "设置成功");
			JsonUtil.writeToResponse(dataMap);
		}else {
			dataMap.put("status", "原密码错误");
			JsonUtil.writeToResponse(dataMap);
		}
	}
	
	/**
	 * 通过验证码修改密码
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws NoSuchFieldException
	 * @throws IOException
	 */
	public void rePwdByPhone() throws JsonGenerationException, JsonMappingException, NoSuchFieldException, IOException{
		//取出验证码
		String code = (String) session.getAttribute("code");
		//取出手机号
		String mobile =(String)session.getAttribute("mobile");
		if(!(code.equals(request.getParameter("code"))&&mobile.equals(request.getParameter("mobile")))){
			dataMap.put("status", "验证码错误");
			JsonUtil.writeToResponse(dataMap);
		}else if(request.getParameter("newPwd").equals(null)){
			dataMap.put("status", "新密码不能为空");
			JsonUtil.writeToResponse(dataMap);
		}else{
			user.setMobile("mobile");
			user.setPayPassword(request.getParameter("newPwd"));
			dao.rePayPwdByPhone(user);
			dataMap.put("status", "设置成功");
			JsonUtil.writeToResponse(dataMap);
		}
	}
	
}
