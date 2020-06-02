<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/login/css/login.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/layui/css/layui.css">
<script src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div class="login">
		<div class="content clearfix">
			<div class="content-left">
				<div class="logo">
					<img src="${ctxStatic}/login/images/bdxy.jpg" alt="" />
					<p>保定学院就业管理系统</p>
				</div>
			</div>
			<div class="shu"></div>
			<div class="content-right">
				<div class="login-form">
					<h2>学生/教师用户登录/LOGIN</h2>
					<div class="identifire">
						<span>&nbsp;&nbsp;&nbsp;身 份：</span> <select>
							<option value="1" selected="selected">学生</option>
							<option value="2">管理员</option>
							<option value="3">辅导员</option>
						</select>
					</div>
					<div class="account clearfix">
						<span>&nbsp;&nbsp;&nbsp;账 号：</span> <input type="text" value=""
							placeholder=" &nbsp;请输入学号/工号" id="userName" class="userName" />
					</div>
					<div class="password clearfix">
						<span>&nbsp;&nbsp;&nbsp;密 码：</span> <input type="password"
							value="" placeholder=" &nbsp;请输入密码" id="password1"
							class="password1" />
					</div>
					<div class="btn">
						<span id="login"><a>登录</a></span>
					</div>
					<div class="btn">
						<span id="register"><a>注册</a></span>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#login').on('click', 'a', function() {
				
				var role = $('.identifire select').val();
				var username = $('#userName').val();
				var password = $('#password1').val();
				$.ajax({
					type : "post",
					url : "login",
					dataType : "json",
					async : false,
					data : {
						role : role,
						username : username,
						password : password
					},
					success : function(data) {
						if (data.type == "success") {
							if (data.role == "2") {
								window.parent.location.href = "index";
							}
							if(data.role=="3"){
								window.parent.location.href = "index1";
							}
							if (data.role == "1") {
								window.parent.location.href = "student";
							}
							return;
						} else {
							alert(data.msg);
							return;
						}
					}
				});
			});

			//注册
			$("#register").on("click", "a", function() {
				$('#register a').attr('href', '${ctx}/system/register');
				return;
			});
		});
	</script>
</body>
</html>
