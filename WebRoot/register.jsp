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
    <form action="<%=path %>/user/User_code.action" name="form1" class="form1" method="post">
         <li>手机号<input type="text" name="mobile"/></li>
         <li><input type="submit" value="获取验证码" id="Submit"/></li>
    </form>
  
  </body>
</html>
