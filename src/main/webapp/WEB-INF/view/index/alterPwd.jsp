<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>修改密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctxStatic}/layui/css/layui.css" type="text/css">
    <link rel="stylesheet" href="${ctxStatic}/bootstrap-4.0.0/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${ctxStatic}/bootstrap3/css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/bootstrap-4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>

	<div class="modal-body" style="margin-top: 30px; margin-bottom: 30px">
		<div class="container">
			<div class="row">
				<div class="col-md-3" style="margin-top: 20px">
					<ul class="layui-nav layui-nav-tree layui-bg-blue"
						lay-filter="test">
						<li class="layui-nav-item"><a
							href="${ctx}/student/toAlterPwd">修改密码</a></li>
						<li class="layui-nav-item"><a href="${ctx}/system/login_out">退出</a></li>
					</ul>
				</div>
				<div class="col-md-7" style="margin-top: 20px">
					<div class="">
						<span class="layui-breadcrumb" lay-separator=">>"> <a
							href="${ctx}/system/student">首页</a> <a><cite>修改密码</cite></a>
						</span>
					</div>
					 <div class="form-horizontal" style="margin-top: 20px">
                   <div class="form-group">
                       <label for="oldPassword" class="col-md-2 control-label">原始密码</label>
                       <div class="col-md-5">
                           <input type="password" class="form-control"  id="oldPassword" placeholder="请输入原始密码">
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="newPassword1" class="col-md-2 control-label">新密码</label>
                       <div class="col-md-5">
                           <input type="password" class="form-control" id="newPassword1" placeholder="请输入新密码">
                       </div>
                       <div class="layui-form-mid layui-word-aux">请填新密码</div>
                   </div>
                   <div class="form-group">
                       <label for="newPassword2" class="col-md-2 control-label">确认新密码</label>
                       <div class="col-md-5">
                           <input type="password" class="form-control" id="newPassword2" placeholder="请再次输入新密码">
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-md-offset-2 col-md-5">
                           <button type="submit" onclick="alterPwd();" class="layui-btn layui-btn-radius">提交</button>
                       </div>
                   </div>
               <%--</form>--%>
               </div>
           </div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
    <script type="text/javascript">
    layui.use(['element','form'], function() {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer;
    });

    function alterPwd() {
        var oldPassword = $("#oldPassword").val();
        var newPassword1 = $("#newPassword1").val();
        var newPassword2 = $("#newPassword2").val();
        if(oldPassword == null || oldPassword == ""){
            layer.tips("请填写原密码","#oldPassword");
            return false;
        }


        if(newPassword1 == null || newPassword1 == ""){
            layer.tips("请输入新密码","#newPassword1");
            return false;
        }

        if(newPassword2 == null || newPassword2 == ""){
            layer.tips("请再次输入新密码","#newPassword2");
            return false;
        }

        if(newPassword1.trim() != newPassword2.trim()){
            layer.tips( "两次密码输入不一致，请重新输入","#newPassword2");
            return false;
        }
        
        $.ajax({
            type: "post",
            url: "${ctx}/student/alertPwd",
            dataType: "json",
            async: false,
            data:{
                oldPassword: oldPassword,
                password : newPassword1
            },
            success: function (data) {
                if(data.type =="success"){
                    alert(data.msg);
                    layer.alert(data.msg)
                    window.location.href= "${ctx}/system/login";
                    return;
                }
                else{
                	layer.alert(data.msg);
                }  
            }
        });

    }


</script>
</body>
</html>
