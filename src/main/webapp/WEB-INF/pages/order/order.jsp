<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含引入公共信息--%>
	<%@include file="/WEB-INF/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">我的订单</span>

	<%--静态包含，登录 成功之后的菜单 --%>
	<%@ include file="/WEB-INF/pages/common/login_sccess_menu.jsp"%>


</div>


	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>

					<td>是否确认签收</td>


			</tr>
			<c:forEach items="${sessionScope.myorder}" var="order">
			<tr>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<td><c:choose>
					<c:when test="${order.status==0}">
						未发货
					</c:when>
					<c:when test="${order.status==1}">
						已发货
					</c:when>
					<c:when test="${order.status==2}">
						已签收
					</c:when>
				</c:choose></td>

				<td><a href="order/showOrderDetail?orderId=${order.orderId}">查看详情</a></td>
				<c:if test="${order.status==1}">
				<td><a href="order/receiverOrder?orderId=${order.orderId}">是否确认签收</a>
					</c:if></td>
			</tr>

			</c:forEach>
			

		</table>
		
	
	</div>

<%--静态包含页脚信息--%>
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>