<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
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
	
	input {
		text-align: center;
	}
</style>
	<script>
		$(function(){
			$("#sub").click(function(){
				var data=$("#form").serialize();

				var method=$("#method").val();

				location.href="${basepath}book/"+method+"?"+data;
				return false
			})
		})
	</script>
</head>
<body>
	<%@include file="/WEB-INF/pages/common/manager_menu.jsp"%>
		<div id="main">
			<!---发送请求到bookServlet-->
			<form  id="form">
				<!--设置隐藏域中的数据，action请求方法，id表示图书id，pageNo表示分页Id-->
				<input type="hidden" name="action" value="${param.method}" id="method">
				<input type="hidden" name="id" value="${param.id}">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<!--设置表单回显的数据-->
						<td><input name="name" type="text" value="${book.name}"/></td>
						<td><input name="price" type="text" value="${book.price}"/></td>
						<td><input name="author" type="text" value="${book.author}"/></td>
						<td><input name="sales" type="text" value="${book.sales}"/></td>
						<td><input name="stock" type="text" value="${book.stock}"/></td>
						<td><input type="submit" value="提交" id="sub"/></td>
					</tr>	
				</table>
			</form>
		</div>

		<%--静态包含页脚信息--%>
		<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>