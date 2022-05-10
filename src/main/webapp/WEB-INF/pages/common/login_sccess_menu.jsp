<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2022/3/13
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${not empty sessionScope.user.username}">
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="order/showMyOrder">我的订单</a>
        <a href="user/logout">注销</a>&nbsp;&nbsp;
    </c:if>

    <span>欢迎你光临尚硅谷书城</span>

    <a href="client/index">返回首页</a>
</div>
