<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<%@include file="/WEB-INF/view/include/tag.jsp"%>
<html>
<head>
    <title>公告详细页面</title>
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
                            <dd><a href="${ctx}/news/tonews	">新闻信息列表</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="col-md-9">
                <div class="" style="margin-top: 20px">
                   <span class="layui-breadcrumb" lay-separator=">>">
                      <a href="${ctx}/system/student">首页</a>
                      <a href="${ctx}/news/tonews">新闻公告</a>
                      <a><cite>新闻详细信息</cite></a>
                    </span>
                </div>

                <div style="margin-top: 40px">
                    <blockquote class="layui-elem-quote layui-text" style="font-size: 16px;">
                        新闻详细信息
                    </blockquote>
                    <div class="container">
                        <input type="hidden" value="${news.id}" id="id">
                        <div class="page-header" style="text-align: center">
                            <h3>${news.newsname}</h3>
                            <p><small>发布人：${news.newsfbz}</small></p>
                            <p><small>类型：${news.newstype}</small></p>
                            <p><small>发布时间：<fmt:formatDate value="${news.newstime}" pattern="yyyy-MM-dd HH:mm" /></small></p>
                        </div>

                        <div class="alert alert-danger" role="alert">
                            <div>
                                <blockquote class="layui-text" style="font-size: 20px;">
                                   新闻内容
                                </blockquote>

                                <p>
                                <pre style="font-size: 15px;">${news.newscontent}</pre>
                                </p>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>
<script type="text/javascript">
    layui.use(['util','laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function() {
        var laydate = layui.laydate //日期
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , element = layui.element //元素操作
            ,util = layui.util;

    });
</script>
</body>
</html>
