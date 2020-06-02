<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<%@include file="/WEB-INF/view/include/tag.jsp"%>
<head>
<style type="text/css">
body {
	font-size: 14px;
	background: url("${ctxStatic}/module/include/images/body_bg.png")
		repeat-x;
	font-family: verdana, Microsoft YaHei, Tahoma, sans-serif;
}

body .modal-body {
	font-size: 14px;
	background: url("${ctxStatic}/module/include/images/modal_bg.png")
		repeat-x;
	font-family: verdana, Microsoft YaHei, Tahoma, sans-serif;
}

.top {
	margin: auto;
	width: 1000px;
	height: 204px;
	background: url("${ctxStatic}/module/include/images/fengjing2019.5.jpg")
		no-repeat 0 50%;
}

.layui-nav a {
	text-decoration: none;
}

#layui-layout-body a {
	text-decoration: none;
}

#test3 img {
	width: 680px;
	height: 322px;
}

.layui-tab-title li {
	font-size: 16px;
	font-weight: 700;
	font-family: '微软雅黑', Arial, Helvetica, sans-serif;
}

#tab1, #tab2 {
	border: 1px solid #9acfea;
}
</style>
</head>

<div class="top"></div>
<%--<div class="layui-container">--%>
<ul class="layui-nav layui-bg-green">
	<li class="layui-nav-item"><a href="${ctx}/system/student">首页</a></li>
	<li class="layui-nav-item"><a
		href="${ctx}/news/tonews">新闻公告</a></li>
	<li class="layui-nav-item"><a href="javascript:;">招聘信息</a>
		<dl class="layui-nav-child">
			<dd>
				<a href="${ctx}/recruit/torecruit">招聘会信息</a>
			</dd>
			<dd>
				<a href="${ctx}/company/tocompany">招聘信息列表</a>
			</dd>
		</dl></li>



	<li class="layui-nav-item"><a href="javascript:;">个人中心</a>
		<dl class="layui-nav-child">
			<dd>
				<a href="${ctx}/student/alterstudent">个人就业信息</a>
			</dd>


		</dl></li>
	<ul class="layui-nav layui-layout-right layui-bg-green">
		<li class="layui-nav-item"><a href="javascript:;"><img
				src="${ctxStatic}/module/include/images/student.jpg"
				class="layui-nav-img">${user.studentname }</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="${ctx}/student/toAlterPwd" id="alterPwd">修改密码</a>
				</dd>
				<dd>
					<a  href="${ctx}/system/login_out"
						id="loginOut">退出</a>
				</dd>
			</dl></li>
	</ul>
</ul>
<%--</div>--%>
<script src="${ctxStatic}/layui/layui.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	
</script>
