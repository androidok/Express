<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allOrder.jsp' starting page</title>
    
  </head>
  
  <body>
       <table border="2" bgcolor="ccceee" width="650">
         <tr bgcolor="CCCCCC" align="center">
              <td>寄件人姓名</td>
              <td>寄件人地址</td>
         </tr>
         
         <c:forEach var="p" items="${order}">
         <tr>
         <form action="#" name="form1" class="form1" method="post">
              <td>${p.sendName}</td>
              <td>${p.sendAddress}</td>
         </form>
         </tr>
         </c:forEach>
  </table>
  </body>
</html>
