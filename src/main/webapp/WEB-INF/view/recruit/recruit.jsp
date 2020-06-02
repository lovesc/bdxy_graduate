<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<%@include file="/WEB-INF/view/include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
    <title>通知公告列表</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>
<div class="modal-body">
    <div class="container">
        <div class="row">
            <div class="col-md-3" style="margin-top: 20px">
                <ul class="layui-nav layui-nav-tree layui-bg-green" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">招聘公告</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${ctx}/recruit/torecruit">招聘会信息</a></dd>
                            <dd><a href="${ctx}/company/tocompany">招聘信息</a></dd>
                        </dl> 
                    </li>
                </ul>
            </div>
            <div class="col-md-9">
                <div class="" style="margin-top: 20px">
                   <span class="layui-breadcrumb" lay-separator=">>">
                      <a href="${ctx}/system/student">首页</a>
                      <a href="${ctx}/recruit/torecruit">招聘会信息</a>-
                      <a><cite>线下招聘信息</cite></a>
                    </span>
                </div>

                <div style="margin-top: 40px">
                
                    <blockquote class="layui-elem-quote layui-text" style="font-size: 16px;">
                        招聘会信息列表
                    </blockquote>

                    <div>
                        <div class="demoTable">
  时间搜索：
  <div class="layui-inline">
    <input type="text" class="layui-input"  autocomplete="off" id="test1" placeholder="yyyy-MM-dd">  
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
                        <table class="layui-table" id="test" lay-filter="test"> </table>
  
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
<script type="text/javascript">
    layui.use(['util','laydate', 'form','laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function() {
        var laydate = layui.laydate //日期
            , form=layui.form//表单
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , element = layui.element //元素操作
            ,util = layui.util;
        laydate.render({
            elem: '#test1' //指定元素
          });

        table.render({
            elem: '#test'
            ,height: 400
            ,url:'${ctx}/recruit/get_recruit',      
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
                ,{field:'name',title:'公司名称', width:'33%'}                         
                ,{field:'address',title:'举办地点', width:'33%'}
                ,{field:'time',title:'举办时间', width:'33%'}
            ]]
            ,page: true//开启分页
            ,skin:'line'
            ,size:'lg'

        });
        var $ = layui.$, active = {
        	    reload: function(){
        	      var test1 = $('#test1');      	      
        	      //执行重载
        	      table.reload('contentTable', {
        	        page: {
        	          curr: 1 //重新从第 1 页开始
        	        }
        	        ,where: {  
        	           time: test1.val()    
        	        }
        	      }, 'data');
        	    }
        	  };
        	  
        	  $('.demoTable .layui-btn').on('click', function(){
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
        		        		window.location.href = '<%=basePath%>enterprise/findrecruit';
        		        	}
        		        }
        	  })
        	  })
    });
</script>
</body>
</html>
