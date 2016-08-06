<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>同城登录</title>
	<link rel="stylesheet" href="1.0.8/iconfont.css">
	<link rel="stylesheet" href="css/login_style.css">

</head>
<body>
	<div id="header">
		<div class="header_box">
			<a href="#"><img src="images/logo.jpg" alt="同城快递"><span> 同城快递</span></a>
		</div>
	</div>
	<div id="body">
		<div class="login">
			<div class="login_title">
				<span>用户登录</span>
				${loginError}
			</div>
			<div class="login_box">
			<form action="./admin/Admin_login.action" method="post">
				<ul>
					<li>
						<span><i class="Hui-iconfont" style="font-size:30px">&#xe60d;</i> 用户名:</span>
						<input type="text" class="input-text radius size-L" name="username">
					</li>
					<li>
						<span><i class="Hui-iconfont" style="font-size:30px">&#xe60e;</i> 密&nbsp;码:</span>
						<input type="password" class="input-text radius size-L" name="password">
					</li>
					<li>
						<input type="submit" value="确认登录" class="login-ok">
					</li>
				</ul>
			</form>
			</div>
		</div>
	</div>
	<div id="footer" class="row">
		<span>© 2016 拓嵘科技</span>
	</div>
	<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
</body>
</html>