<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>

	<%@include file="/WEB-INF/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			//使用ajax异步更新图书加入信息
			$(".cart").click(function (){
			$.getJSON(
                "${basepath}cart/ajaxaddItem",
                "id="+$(this).attr("BookId"),
                function(data){
                            <%--要记得这是一个servlet程序--%>
                          if(${empty sessionScope.cart.items}){
							  location.replace("${pageScope.basepath}${requestScope.url}");
						  }
						  console.log(data)
			                $("#count").html("您的购物车中有"+data.totalCount+"件商品")
				            $("#name").html(data.lastname)
			})
			})
			$("#price_sub").click(function (){
				location.href="${basepath}client/pageByPrice/"+$("#min").val()+"/"+$("#max").val()

				return false
			}

			)
		})
	</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
		<div>
			<c:if test="${empty sessionScope.user.username}">
				<a href="user/login">登录</a> |
				<a href="user/regist">注册</a> &nbsp;&nbsp;
			</c:if>
			<c:if test="${not empty sessionScope.user.username}">
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
				<a href="order/showMyOrder">我的订单</a>
				<a href="/user/logout">注销</a>&nbsp;&nbsp;
			</c:if>
			<a href="cart/cart">购物车</a>
			<a href="manager/manager">后台管理</a>
		</div>
	</div>
	<div id="main">
		<!--第一改造-->
		<div id="book">
			<div class="book_cond">
				<!--根据价格查询-->
				<form action="client/pageByPrice" method="get" >
					价格：<input id="min" type="text" name="min" value="${requestScope.min}"> 元 -
						<input id="max" type="text" name="max" value="${requestScope.max}"> 元
						<input type="submit" value="查询" id="price_sub" />
				</form>
			</div>
			<c:if test="${empty sessionScope.cart.items}">
				<div style="text-align: center" >
					<span id="count"></span>
					<div>
						<span style="color: red"  id="name">当前购物车为空</span>
					</div>
				</div>
			</c:if>
			<c:if test="${ not empty sessionScope.cart.items}">
				<div style="text-align: center"   >
					<span id="count">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span style="color: red"  id="name">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</div>
			</c:if>
			<!--改造二-->
			<!---遍历图书并显示-->
			<c:forEach items="${requestScope.page.list}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.img_path}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button BookId="${book.id}" class="cart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>

		</div>
         <!--改造三-->
		<%@include file="/WEB-INF/pages/common/page_nav.jsp"%>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>