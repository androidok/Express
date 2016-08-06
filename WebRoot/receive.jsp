<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'receive.jsp' starting page</title>
  </head>
  
  <body>
<form action="./order/Order_receiveOrder.action" method="post">
快递员id<input type="text" name="courierid">
<input type="submit"  value="抢单">
</form>
  </body>
</html>
