<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <base href="<%=basePath%>">
    <title>My JSP 'findOrder.jsp' starting page</title>
  </head>
  
  <body>
  
  <form action="./admin/Admin_findOrder.action" method="post">
  					订单号:<input type="text"  name="orderId" >
  					<input type="submit" value="查询">
  </form>
  </body>
</html>
