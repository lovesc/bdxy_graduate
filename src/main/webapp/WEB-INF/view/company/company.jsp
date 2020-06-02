<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<%@include file="/WEB-INF/view/include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>招聘信息</title>

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
							href="javascript:;">招聘公告</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${ctx}/recruit/torecruit">招聘会信息</a>
								</dd>
								<dd>
									<a href="${ctx}/company/tocompany">招聘信息</a>
								</dd>
							</dl></li>
					</ul>
				</div>
				<div class="col-md-9">
					<div class="" style="margin-top: 20px">
						<span class="layui-breadcrumb" lay-separator=">>"> <a
							href="${ctx}/system/student">首页</a> <a
							href="${ctx}/company/tocompany">招聘信息</a>- <a><cite>公告信息列表</cite></a>
						</span>
					</div>

					<div style="margin-top: 40px">

						<blockquote class="layui-elem-quote layui-text"
							style="font-size: 16px;">线上	招聘信息列表</blockquote>


						<!--  搜索框 -->
						<div class="layui-form">
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label">职位名称</label>
									<div class="layui-input-inline">
										<input type="text" id="workname"  autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">工作类型</label>
									<div class="layui-input-inline">
										<select id="type"  lay-search="">
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
								<div class="layui-form-label">地址</div>
								<div class="layui-input-inline" style="width: 200px;">
									<select id="province" class="province-selector"
										data-value="" lay-filter="province-1">
										<option value="">请选择省</option>
									</select>
								</div>
								<div class="layui-input-inline" style="width: 200px;">
									<select id="city" class="city-selector" data-value=""
										lay-filter="city-1">
										<option value="">请选择市</option>
									</select>
								</div>	
								<button class="layui-btn" style="right: 5px" data-type="reload">搜索</button>
							</div>
						</div>

					</div>
				</div>
				<table class="layui-table" id="test" lay-filter="test">
				</table>

			</div>

		</div>

	</div>

	<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
	<script type="text/javascript">
	 layui.config({
         base	: "${ctxStatic}/mods/",
         version: '1.0'
     });
    layui.use(['util','laydate', 'form','laypage', 'layer', 'layarea','table', 'carousel', 'upload', 'element', 'slider'], function() {
        var laydate = layui.laydate //日期
            , form=layui.form//表单
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , element = layui.element //元素操作
            ,util = layui.util
            ,layarea = layui.layarea;
        layarea.render({
            elem: '#area-picker',
            change: function(res) {
              //选择结果
            }
        });
        laydate.render({
            elem: '#test1' //指定元素
          });

        table.render({
            elem: '#test'
            ,height: 400
            ,url:'${ctx}/company/get_company',      
            parseData: function(res){ //res 即为原始返回的数据
                return {
                  "code": 0, //解析接口状态
                  "msg": "", //解析提示文本
                  "count": res.total, //解析数据长度
                  "data": res.rows //解析数据列表
                };
              }
            ,limit:10,
            request:{
            	 pageName: 'page' //页码的参数名称，默认：page
            	,limitName: 'rows'
            }
            ,id:'contentTable'
            ,title: '公告信息列表'         
            ,cols: [[
                {field:'id', title:'id',  hide: true}
                ,{field:'name',title:'公司名称'}
                ,{field:'workname',title:'招聘职位'}
                ,{field:'type',title:'招聘类型'}
                ,{field:'province',title:'工作地点'}
                ,{field:'starttime',title:'开始时间'}
                ,{field:'endtime',title:'结束时间'}
            ]]
            ,page: true//开启分页
            ,skin:'line'
            ,size:'lg'

        });
        var $ = layui.$, active = {
        	    reload: function(){
        	      var workname = $('#workname'); 
        	      var type = $('#type');
        	      var province = $('#province');
        	      var city = $('#city');
        	      console.log(workname.val())
        	      //执行重载
        	      table.reload('contentTable', {
        	        page: {
        	          curr: 1 //重新从第 1 页开始
        	        }
        	        ,where: {  
        	        	workname: workname.val() ,
        	        	type:type.val(),
        	        	province:province.val(),
        	        	city:city.val()
        	        }
        	      }, 'data');
        	    }
        	  };
        	  
        	  $('.layui-btn').on('click', function(){
        	    var type = $(this).data('type');
        	    active[type] ? active[type].call(this) : '';
        	  });

        	  table.on('row(test)', function(obj){
        		  var data=obj.data;
        		  $.ajax({
        				type : "get",
        				url : "<%=basePath%>enterprise/findname",
        		        data:{"name":data.name},
        		        success: function(data){
        		        	if(data="success"){
        		        		window.location.href = '<%=basePath%>enterprise/findcompany';
																}
															}
														})
											})

						});
	</script>
</body>
</html>
