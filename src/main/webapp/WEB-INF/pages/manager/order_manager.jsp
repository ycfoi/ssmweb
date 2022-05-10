<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib   uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含引入公共信息--%>
	<%@include file="/WEB-INF/pages/common/head.jsp"%>
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
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.page.list}" var="order">
			<tr>
				<td><fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd"/>

				</td>
				<td>${order.price}</td>
				<td><a href="order/showOrderDetail?orderId=${order.orderId}">查看详情</a></td>
				<td>
					<c:choose>
						<c:when test="${order.status==0}">
							<a href="order/sendOrder?orderId=${order.orderId}&pageNo=${requestScope.page.pageNum}">点击发货</a>
						</c:when>
						<c:when test="${order.status==1}">
							已发货
						</c:when>
						<c:when test="${order.status==2}">
							已签收
						</c:when>
					</c:choose>
				</td>
			</tr>
			</c:forEach>


		</table>
		<%@include file="/WEB-INF/pages/common/page_nav.jsp"%>
	</div>

	<%--静态包含页脚信息--%>
	<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>