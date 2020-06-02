<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/layui/css/layui.css" />
</head>
<body>
<form class="layui-form layui-form-pane" action="">


    <div class="layui-inline" style="padding-left: 9.1%;padding-top: 2%">
        <form class="layui-form layui-form-pane" action="javascript:;">
            <div class="layui-form-item" id="addressDiv">
                <label class="layui-form-label">详细地址</label>
                <div class="layui-input-inline">
                    <select name="P1" lay-filter="province" id="province">
                        <option></option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="C1" lay-filter="city" id="city">
                        <option></option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="A1" lay-filter="area" id="area">
                        <option></option>
                    </select>
                </div>
                <div class="layui-input-inline" id="addressDetail">
                    <input id="address" type="text" name="address" lay-verify="title" autocomplete="off"
                           placeholder="请输入具体地址" class="layui-input" style="width: 120%;">
                    <dl id="addressTip" class="addressDl">
                    </dl>
                </div>
            </div>
        </form>
    </div>
</form>

<script src="${ctxStatic}/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/select.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
