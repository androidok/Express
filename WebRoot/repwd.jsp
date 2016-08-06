<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'register.jsp' starting page</title>
  </head>
  <body>
    <form action="<%=path %>/user/User_reLoginPwdByOldPwd.action" name="form1" enctype="multipart/form-data" class="form1" method="post">
         <li>手机号<input type="text" name="mobile"/></li>
         <li>原密码<input type="text" name="oldPwd"/>
         <li>新密码<input type="text" name="newPwd"/>
         <li><input type="submit" value="提交" id="Submit"/></li>
    </form>
  
  </body>
</html>
