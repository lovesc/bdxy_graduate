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
                        <a href="javascript:;">新闻公告</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${ctx}/news/tonews">新闻信息列表</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="col-md-9">
                <div class="" style="margin-top: 20px">
                   <span class="layui-breadcrumb" lay-separator=">>">
                      <a href="${ctx}/system/student">首页</a>
                      <a href="${ctx}/news/tonews">新闻公告</a>
                      <a><cite>新闻信息列表</cite></a>
                    </span>
                </div>

                <div style="margin-top: 40px">
                    <blockquote class="layui-elem-quote layui-text" style="font-size: 16px;">
                        公告信息列表
                    </blockquote>

                    <div>
                        <table class="layui-table" id="test" lay-filter="test">
                        </table>
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
            ,url:'${ctx}/news/get_news',      
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
                ,{field:'newsname',title:'新闻标题', width:'33%'}                         
                ,{field:'newstype',title:'新闻类型', width:'33%'}
                ,{field:'newstime',title:'发布时间', width:'33%'}
            ]]
            ,page: true//开启分页
            ,skin:'line'
            ,size:'lg'

        });    
        	  table.on('row(test)', function(obj){
        		  var data=obj.data;
        		  var id=data.id
        		 window.location.href ="findnews?id="+id;
        		
        	  })
    });
</script>
</body>
</html>
