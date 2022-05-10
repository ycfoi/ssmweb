<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含引入公共信息--%>
	<%@include file="/WEB-INF/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>

	<script type="text/javascript">

		//使用$()使得代码在初始化后再被调用
		$(function(){
			//获取用户名输入框，定义失去焦点的方法
			$("#username").blur(function(){
				//获取用户名
				var username=this.value;
				//通过ajax请求查询用户名是否可用，此中的$｛basepath｝表示映射到webaap目录
				$.getJSON("${basepath}user/ajaxUsername",
						"username="+username,
						function(data){
                        //判断用户名是否可用
					if(data.exist){
							  $(".errorMsg").text("用户名不可用");
						  }else{
							  $(".errorMsg").text("用户名可用");
						  }
				})
			})
			//刷新验证码
			$('#code_img').click(
					//此中加入new Date（）是为防止浏览器的缓存使得验证码无法刷新
					function (){
						this.src="${basepath}kap.jpg?d="+new Date;
					}
			)
			//验证表单中的数据是否合法，若不合法则return false阻止表单提交 反之return true提交表单
			$("#sub_btn").click(
					function () {
                         //定义字母或数字，下划线的6到12位用户名验证规则，加上了^与$符号以后表示要从头到尾完全匹配
						var patter = /^\w{6,12}$/;
						//获取用户名的值
						var value = $("#username").val()
                         //调用正则表达式中的test方法，比较用户名是否合法
						if (!patter.test(value)) {
							$(".errorMsg").text("用户名不合法");
							return false
						}
						//密码匹配与用户名操作相同
						var value1 = $("#password").val()
						if (!patter.test(value1)) {
							$(".errorMsg").text("密码长度为6，12");
							return false;
						}
						//获取确认密码
						var value2 = $("#repwd").val()


							//确认密码是否符合格式
							if(!patter.test(value2)){
								$(".errorMsg").text("密码长度为6，12");
								return false;
							}
							//判断两次输入密码是否相同
							if(value1!=value2){
								$(".errorMsg").text("密码不一致");
								return false;
							}
                          //获取邮箱的值
                       var email=$("#email").val()
						//定义邮箱的正则表达式,建议通过网上获取
						var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

                             //判断邮箱是否为空
							if(email!=null&&email!=""){
								      //判断邮箱是否符合格式
	                                 if(!emailPatt.test(email)){
		                              $(".errorMsg").text("邮箱格式不一致");
		                              return false;
	                                }
                                    }else{
	                                  $(".errorMsg").text("邮箱不能为空");
	                                   return false;
                                     }
                     var code=$('#code').val()
						if($.trim(code)==""||$.trim(code)==null){
							$(".errorMsg").text("验证码不能为空");
							return false;
						}
						$(".errorMsg").text("")


					}
			)
		})
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<!--获取错误信息显示在页面上-->
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<!--发送请求到userServlet-->
								<form action="user/registsend" method="post">

									<label>用户名称：</label>
									<!--当用户名存在时将原来输入的用户名回显到页面上-->
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<!--当邮箱存在时将原来输入的邮箱回显到页面上-->
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
									value="${requestScope.email}"/>
									<br />
									<br />
										<label>验证码：</label>
								<input class="itxt" type="text"  name="code" style="width: 80px; " id="code"/>
									<img  id="code_img" alt="" src="kap.jpg" style="float: right; margin-right: 40px; width:110px; height: 30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚信息--%>
		<%@include file="/WEB-INF/pages/common/foot.jsp"%>
</body>
</html>