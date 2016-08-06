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
    <form action="<%=path %>/user/User_auth.action" name="form1" enctype="multipart/form-data" class="form1" method="post">
         <li>手机号<input type="text" name="mobile"/></li>
         <li>姓名<input type="text" name="name"/>
         <li>身份证号<input type="text" name="idnumber"/></li>
         <input type="file" name="upload"/> 
         <input type="file" name="upload"/>
         <input type="file" name="upload"/>
         <li><input type="submit" value="提交" id="Submit"/></li>
    </form>
  
  </body>
</html>
