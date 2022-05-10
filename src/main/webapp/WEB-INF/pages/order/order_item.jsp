<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%--静态包含引入公共信息--%>
    <%@include file="/WEB-INF/pages/common/head.jsp"%>

</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >

    <div>

        <a href="client/bookServlet?action=page">返回商城</a>
    </div>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>总数</td>

            <td>价格</td>
            <td>总价格</td>



        </tr>
        <c:forEach items="${sessionScope.orderItems}" var="items">
            <tr>
                <td>${items.name}</td>
                <td>${items.count}</td>
                <td>${items.price}</td>
                <td>${items.totalPrice}</td>

            </tr>
        </c:forEach>





    </table>

</div>

<%--静态包含页脚信息--%>
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>