<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<%@include file="/WEB-INF/view/include/tag.jsp"%>
<html>
<head>
<title>首页</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>
	<div class="modal-body" style="margin-top: 20px">
		<!--中间模块布局-->
		<div class="layui-container">
			<!--第一模块，轮播图及日历布局-->
			<div class="layui-row layui-col-space20">
				<div class="layui-col-md6">
					<div class="layui-carousel" id="test3">
						<div carousel-item="">
							<div>
								<img src="${ctxStatic}/module/include/sliderImages/push_1.jpg">
							</div>
							<div>
								<img src="${ctxStatic}/module/include/sliderImages/push_2.jpg">
							</div>
							<div>
								<img src="${ctxStatic}/module/include/sliderImages/push_3.jpg">
							</div>
							<div>
								<img src="${ctxStatic}/module/include/sliderImages/push_5.jpg">
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-md4 layui-col-md-offset2">
					<div class="grid-demo grid-demo-bg1">
						<div class="site-demo-laydate">
							<div class="layui-inline" id="test-n1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
	 <script type="text/javascript">
        layui.use(['element','carousel', 'laydate','layer','util'], function() {
            var element = layui.element,
                layer = layui.layer,
                carousel = layui.carousel,
                util = layui.util,
                laydate = layui.laydate;

            layer.config({
                skin: 'demo-class'
            })
           carousel.render({
                elem: '#test3',
                width: '680px',
                height: '322px',
                interval: 3000,
                arrow: 'hover'
            });
           laydate.render({
                elem: '#test-n1'
                , position: 'static'
            }); 
        })
        </script>
</body>
</html>