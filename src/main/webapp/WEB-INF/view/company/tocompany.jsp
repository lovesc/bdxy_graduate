<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<%@include file="/WEB-INF/view/include/tag.jsp"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>
	<div class="modal-body">
		<div class="container">
			<div class="row">
				<div class="col-md-3" style="margin-top: 20px">
					<ul class="layui-nav layui-nav-tree layui-bg-green"
						lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed"><a
							href="javascript:;">招聘会信息</a>  <dl class="layui-nav-child">
                              <dd><a href="${ctx}/recruit/torecruit">招聘会信息</a></dd>
                            <dd><a href="${ctx}/company/tocompany">招聘信息</a></dd>
                        </dl> </li>
					</ul>
				</div>
				<div class="col-md-9">
					<div class="" style="margin-top: 20px">
						<span class="layui-breadcrumb" lay-separator=">>"> <a
							href="${ctx}/system/student">首页</a> <a
							href="${ctx}/company/tocompany">招聘信息</a>- <a><cite>线上招聘	</cite></a>
						</span>
					</div>

					<div style="margin-top: 40px">
						<blockquote class="layui-elem-quote layui-text"
							style="font-size: 16px;">在线招聘信息详情页面</blockquote>
						<div class="container">
							<input type="hidden" value="${recruitInfo.id}" id="rId">
							<input type="hidden" value="${recruitInfo.cId}" id="cId">
							<div class="page-header" style="text-align: center">
								<h2>${enterprise.name}</h2>
								<p>
									开始时间：
									<fmt:formatDate value="${company.starttime}"
										pattern="yyyy-MM-dd " />
								</p>
								<p>
									结束时间：
									<fmt:formatDate value="${company.endtime}"
										pattern="yyyy-MM-dd " />
								</p>
							</div>

							<div class="alert alert-info" role="alert">
								<blockquote class="layui-text" style="font-size: 30px;">
									单位简介:</blockquote>
								<p style="margin-left: 30px;text-indent:2em;">
								<pre style="font-size: 16px;text-indent:2em">${enterprise.information}</pre>
								</p>
								<p style="font-size: 16px">单位所在地：${enterprise.province}地区 ${enterprise.city}市</p>
								<p style="font-size: 16px">公司规模：${enterprise.people}人</p>
								<p style="font-size: 16px">公司电话：${enterprise.phone}</p>
								<p style="font-size: 16px">公司类型：${enterprise.type}</p>
							</div>
							<div class="alert alert-danger" role="alert">
								<blockquote class="layui-text" style="font-size: 30px;">
									招聘职位：</blockquote>
									<p style="font-size: 16px">
									需要的招聘岗位：${company.workname}
									</p>
						</div>
							<div class="alert alert-danger" role="alert">
								<blockquote class="layui-text" style="font-size: 30px;">
									招聘内容：</blockquote>
								<p>
								<pre style="font-size: 16px">${company.information}</pre>
								</p>							
							</div>
							
						<div class="alert alert-danger" role="alert">
								<blockquote class="layui-text" style="font-size: 30px;">
									其他信息：</blockquote>
									<p style="font-size: 16px">
									工作地点：${company.province}地区${company.city}
									</p>
									<p style="font-size: 16px">
									有问题请联系：${company.companyphone}
									</p>
									<p style="font-size: 16px">
									请投简历至：${company.email}
									</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
	<script type="text/javascript">
		layui.use([ 'util', 'laydate', 'laypage', 'layer', 'table', 'carousel',
				'upload', 'element', 'slider' ], function() {
			var laydate = layui.laydate //日期
			, laypage = layui.laypage //分页
			, layer = layui.layer //弹层
			, table = layui.table //表格
			, element = layui.element //元素操作
			, util = layui.util;

		});
	</script>

</body>
</html>
