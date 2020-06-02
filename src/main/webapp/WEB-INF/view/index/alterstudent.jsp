<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${ctxStatic}/layui/css/layui.css" type="text/css">
    <link rel="stylesheet" href="${ctxStatic}/bootstrap-4.0.0/css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/bootstrap-4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>

<div class="modal-body">
    <div class="container">
        <div class="row">
            <div class="col-md-3" style="margin-top: 20px">
                <ul class="layui-nav layui-nav-tree layui-bg-green" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">就业信息</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${ctx}/student/alterstudent">查看/修改就业信息</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="col-md-8">
                <div class="" style="margin-top: 20px">
                   <span class="layui-breadcrumb" lay-separator=">>">
                      <a href="${ctx}/student/">首页</a>
                      <a><cite>编辑就业信息</cite></a>
                    </span>
                </div>
					<form id="epForm" class="layui-form layui-form-pane"
						lay-filter="epForm" style="margin-top: 40px;">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">单位名称</label>
								<div class="layui-input-inline">
									<input type="text" name="name" id="name" autocomplete="off"
										lay-verify="required" placeholder="请输入单位名称"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">单位性质</label>
								<div class="layui-input-inline">
									<select id="type" name="type" lay-search="">
										<option value="">全部</option>
										<option value="计算机">计算机</option>
										<option value="金融">金融</option>
										<option value="医疗">医疗</option>
										<option value="销售">销售</option>
										<option value="教育">教育</option>
									</select>
								</div>
							</div>

						</div>
						<div class="layui-form-item" id="area-picker">
							<div class="layui-form-label">单位地址</div>
							<div class="layui-input-inline" style="width: 200px;">
								<select id="province" name="province" class="province-selector"
									data-value="" lay-filter="province-1">
									<option value="">请选择省</option>
								</select>
							</div>
							<div class="layui-input-inline" style="width: 200px;">
								<select id="city" name="city" class="city-selector"
									data-value="" lay-filter="city-1">
									<option value="">请选择市</option>
								</select>
							</div>
						</div>
						
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">从事职位</label>
								<div class="layui-input-inline">
									<input type="text" id="eCompanyProfession" name="workname"
										lay-verify="required" placeholder="请输入" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">单位联系电话</label>
								<div class="layui-input-inline">
									<input type="text" name="phone" id="phone" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">个人电话</label>
								<div class="layui-input-inline">
									<input type="text" name="studentphone"
										id="studentphone" lay-verify="required"
										autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">工作介绍</label>
							<div class="layui-input-block">
								<textarea placeholder="请输入内容" name="information"
									class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">就业后情况</label>
							<div class="layui-input-block">
								<textarea placeholder="请输入内容" name="track"
									class="layui-textarea"></textarea>
							</div>
						</div>
					</form>
					<div class="layui-form-item">
                    <button class="layui-btn" id="epBtn">保存</button>
                </div>
            </div>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
<script type="text/javascript" src="${ctxStatic}/layui/layui.js"></script>
<script>
	layui.config({
		base : "${ctxStatic}/mods/",
		version : '1.0'
	});
	layui.use([ 'form', 'element', 'layarea', 'laydate', 'layedit' ],
					function() {
						var form = layui.form, element = layui.element, laydate = layui.laydate, layedit = layui.layedit, layer = layui.layer, layarea = layui.layarea;
						form.val("epForm", {
							"name" : "${students.name}",
							"type" : "${students.type}",
							"workname" : "${students.workname}",
							"province" : "${students.province}市",
							"city" : "${students.city}市",
							"phone" : "${enterprise.phone}",
							"information" : "${students.information}",
							"studentphone":"${students.studentphone}",
							"track":"${students.track}"
						});
						layarea.render({
							elem : '#area-picker',
							change : function(res) {
								//选择结果
							}
						});

					});

	$(document).ready(function() {
		$("#epBtn").on("click", function() {
			$.ajax({
				type : "POST",
				url : "${ctx}/student/edit",
				dataType : "json",
				async : false,
				data : $("#epForm").serialize(),
				success : function(data) {
					if(data.type="success"){
						alert("修改成功")
						window.location.reload();
					}
				}
			});
		})

	});
</script>
</body>
</html>
