<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'button.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="<%=path %>/admin/Admin_getAllUserIdentity.action" name="form1" class="form1" method="post">
    
          <td><input type="submit" value="查看所有用户提交的身份信息"/></td>
          
    </form>
    <form action="./order/Order_findAllOrder.action" name="form1" class="form1" method="post">
    	  <tr>用户id<input type="text" name="userid"></tr>
    	  <tr>分页起点<input type="text" name="first"></tr>
          <tr><input type="submit" value="通过用户id查看订单"/></tr>
          
    </form>
  </body>
</html>
