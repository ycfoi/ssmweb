<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含引入公共信息--%>
	<%@include file="/WEB-INF/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$(".delete").click(function (){
				return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text());
			})
			$("#clear").click(function (){
				return confirm("你确定要清空购物车");
			})
          //id选择器的特性只有一个生效
			$(".updateCount").change(function (){
				var name=$(this).parent().parent().find("td:first").text();
				var count=$(this).val()
				var id=$(this).attr("BookId");
				if(confirm("你确定要修改"+name+"的数量为"+count)){
					location.href="${pageScope.basepath}cart/update?count="+count+"&id="+id
				}else{
					this.value=this.defaultValue
				}
			})

		})
	</script>
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
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="client/index">当前购物车为空</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart}">
				<c:forEach items="${sessionScope.cart.items}" var="enty">
					<tr>
						<td>${enty.value.name}</td>
						<td><input  BookId="${enty.value.id}" class="updateCount" type="text" value="${enty.value.count}"></td>
						<td>${enty.value.price}</td>
						<td>${enty.value.totalPrice}</td>
						<td><a  class="delete" href="cart/delete?id=${enty.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a id="clear" href="cart/clear">清空购物车</a></span>
			<span class="cart_span"><a   href="order/createorder">去结账</a></span>
		</div>
		</c:if>
	</div>
	<%--静态包含页脚信息--%>
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>