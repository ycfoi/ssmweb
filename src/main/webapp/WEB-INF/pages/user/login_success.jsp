<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含引入公共信息--%>
	<%@include file="/WEB-INF/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
<div id="header">
	<img class="logo_img" alt="" src="../../../static/img/logo.gif" >
	<span class="wel_word">我的订单</span>

	<%--静态包含，登录 成功之后的菜单 --%>
	<%@ include file="/WEB-INF/pages/common/login_sccess_menu.jsp"%>


</div>
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>

<%--静态包含页脚信息--%>
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>