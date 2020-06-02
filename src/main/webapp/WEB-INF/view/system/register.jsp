<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/login/css/register.css" />
<script src="${ctxStatic}/jquery/jquery-3.3.1.min.js"
	type="text/javascript" charset="utf-8"></script>
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
					<h2>用户注册/REGISTER</h2>
					<div class="account clearfix" id="college">
						<span>&nbsp;&nbsp;院&nbsp;&nbsp;校&nbsp;&nbsp;：</span> <select>
							<option value="">请选择</option>
							<option value="1">数据科学与软件工程学院</option>
							<option value="2">文学院</option>
							<option value="3">体育学院</option>
							<option value="4">知识产权与财经学院</option>
							<option value="5">地理与旅游学院</option>
							<option value="6">信息工程学院</option>
							<option value="7">教师教育学院</option>
							<option value="8">音乐舞蹈学院</option>
							<option value="9">美术与设计学院</option>
						</select>
					</div>

					<div class="account clearfix">
						<span>&nbsp;&nbsp;学&nbsp;&nbsp;号&nbsp;&nbsp;：</span> <input
							type="text" placeholder=" &nbsp;请输入学号" id="studentid"
							name="studentid" />
					</div>
					<div class="account clearfix">
						<span>&nbsp;&nbsp;姓&nbsp;&nbsp;名&nbsp;&nbsp;：</span> <input
							type="text" placeholder=" &nbsp;请输入姓名" id="studentname"
							name="studentname" />
					</div>

					<div class="password clearfix">
						<span>&nbsp;&nbsp;密&nbsp;&nbsp;码&nbsp;&nbsp;：</span> <input
							type="password" value="" placeholder=" &nbsp;请输入密码" id="password"
							name="password" />
					</div>

					<div class="account clearfix" id="sex" style="top: 20px">
						<span>&nbsp;&nbsp;性&nbsp;&nbsp;别&nbsp;&nbsp;：</span> <select>
							<option value="">请选择</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>
					<div class="btn">
						<span id="register" name="register"><a>注册</a></span>
					</div>
					<div class="btn">
						<span id="login"><a href="${ctx}/system/login">去登录</a></span>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#register").on("click","a",function() {
						
								var number = $("#college select").val();
								var studentname = $("#studentname").val();
								var studentid = $("#studentid").val();
								var password = $("#password").val();
								var sex = $("#sex select").val();
								if (!valiate()) {
									return false;
								}
								$.ajax({
									type : "POST",
									url : "${ctx}/student/register",
									dataType : "json",
									data : {
										number : number,
										studentname : studentname,
										studentid : studentid,
										password : password,
										sex : sex
									},
									success : function(data) {
										console.log(data);
										if (data.type == "success") {
											alert("注册成功请登录")
											$("#register a").attr('href','${ctx}/system/login');
											return;
										} else {
											alert(data.msg);
											console.log(data.msg)
										}

									}
								});
							});
				});
		function valiate() {
			var number = $("#college select").val();
			var studentname = $("#studentname").val();
			var studentid = $("#studentid").val();
			var password = $("#password").val();
			var sex = $("#sex select").val();

			if (number == null || number == "") {
				alert("请选择院校");
				return false;
			}
			if (studentname == null || studentname == "") {
				alert("请输入姓名");
				return false;
			}
			if (studentid == null || studentid == "") {
				alert("请输入学号");
				return false;
			}
			if (password == null || password == "") {
				alert("请输入密码");
				return false;
			}
			if (sex == null || sex == "") {
				alert("请选择性别");
				return false;
			}
			return true;
		}
	</script>

</body>
</html>
