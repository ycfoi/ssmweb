<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--静态包含引入公共信息--%>
	<%@include file="/WEB-INF/pages/common/head.jsp"%>
	<script type="text/javascript">
	$(function (){
		$("a.delete").click(
				function (){
					return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text())
				}
		)
	})
	</script>
</head>
<body>	<%@include file="/WEB-INF/pages/common/manager_menu.jsp"%>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<!---遍历图书信息-->
				<c:forEach items="${requestScope.page.list}" var="book">
			<tr>
				<!---回显数据-->
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
				<!---相关设置图书操作请求的方法与所要的参数-->
					<td><a href="book/getBook?id=${book.id}&method=update&pageNo=${requestScope.page.pageNum}">修改</a></td>
					<td><a  class="delete" href="book/delete?id=${book.id}&pageNo=${requestScope.page.pageNum}">删除</a></td>
			</tr>
				</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="manager/book_edit?method=add&pageNo=${requestScope.page.pages}">添加图书</a></td>
			</tr>	
		</table>
		<%@include file="/WEB-INF/pages/common/page_nav.jsp"%>
	</div>
	<%--静态包含页脚信息--%>
	<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>