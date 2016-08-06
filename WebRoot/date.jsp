<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'date.jsp' starting page</title>
    
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
  	<form action = "./order/Order_createOrder.action"  enctype="multipart/form-data" method = "post">
   <input type = "text"  name = "receiveDate"  onclick="laydate()">
   <input type="text" name="sendName">
   <input type="file" name="upload"/> 
   <input type="file" name="upload"/> 
   <input type="file" name="upload"/> 
   <button type = "submit">提交</button>
   </form>
   <script src = "admin/js/laydate/laydate.js"></script>
  </body>
</html>
