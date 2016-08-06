<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'LookOrder.jsp' starting page</title>
  </head>
  
  <body>
  <form action="./order/Order_browseOrder.action" name="form1" class="form1" method="post">
  经度<input type="text"  input name="receiveLongitude">
  纬度<input type="text" name="receiveLatitude">
         <input type="submit"  value="查询">
  </form>
  </body>
</html>
