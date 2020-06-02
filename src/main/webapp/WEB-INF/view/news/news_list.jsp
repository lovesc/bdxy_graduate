<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>easyui/css/demo.css">
<script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>easyui/js/validateExtends.js"></script>
<script type="text/javascript">
$(function() {	
	var table;
	//datagrid初始化 
    $('#dataList').datagrid({ 
        title:'新闻信息列表', 
        iconCls:'icon-more',//图标 
        border: true, 
        collapsible:false,//是否可折叠的 
        fit: true,//自动大小 
        method: "post",
        url:"get_list?t="+new Date().getTime(),
        idField:'id', 
        singleSelect:false,//是否单选 
        pagination:true,//分页控件 
        rownumbers:true,//行号 
        sortName:'id',
        sortOrder:'DESC', 
        remoteSort: false,
        columns: [[  
			{field:'chk',checkbox: true,width:50},
		        {field:'id',title:'ID',width:50, sortable: true}, 
		        {field:'newsname',title:'新闻名称',width:100, sortable: true},
		        {field:'newscontent',title:'新闻内容',width:150, sortable: true},
		        {field:'newsfbz',title:'发布者',width:100},
		        {field:'newstype',title:'新闻类型',width:100},
		        {field:'newstime',title:'发布时间',width:100, sortable: true,align:"center"},
		        	
 		]], 
        toolbar: "#toolbar"
    }); 
    //设置分页控件 
    var p = $('#dataList').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
    }); 
    //设置工具类按钮
    $("#add").click(function(){
    	table = $("#addTable");
    	$("#addDialog").dialog("open");
    });
    //修改
    $("#edit").click(function(){
    	table = $("#editTable");
    	var selectRows = $("#dataList").datagrid("getSelections");
    	if(selectRows.length != 1){
        	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
        } else{
	    	$("#editDialog").dialog("open");
        }
    });
    //删除
    $("#delete").click(function(){
    	var selectRows = $("#dataList").datagrid("getSelections");
    	var selectLength = selectRows.length;
    	if(selectLength == 0){
        	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
        } else{
        	var ids = [];
        	$(selectRows).each(function(i, row){
        		ids[i] = row.id;
        	});	
        	$.messager.confirm("消息提醒", "将删除与用户相关的所有数据，确认继续？", function(r){
        		if(r){
        			$.ajax({
						type: "post",
						url: "delete",
						data: {ids: ids},
						dateType:'json',
						success: function(data){
							if(data == "success"){
								$.messager.alert("消息提醒","删除成功!","info");
								//刷新表格
								$("#dataList").datagrid("reload");
								 $("#dataList").datagrid("uncheckAll"); 
							} else{
								$.messager.alert("消息提醒",data.msg,"warning");
								return;
							}
						}
					});
        		}
        	});
        }
    });
    
  	//设置添加窗口
    $("#addDialog").dialog({
    	title: "添加用户",
    	width: 400,
    	height: 500,
    	iconCls: "icon-add",
    	modal: true,
    	collapsible: false,
    	minimizable: false,
    	maximizable: false,
    	draggable: true,
    	closed: true,
    	buttons: [
    		{
				text:'添加',
				plain: true,
				iconCls:'icon-user_add',
				handler:function(){
					var validate = $("#addForm").form("validate");
					if(!validate){
						$.messager.alert("消息提醒","请检查你输入的数据!","warning");
						return;
					} else{
						var data = $("#addForm").serialize();
						console.log(data);
						$.ajax({
							type: "post",
							url: "add",
							data: data,
							dataType:'json',
							success: function(data){
								if(data.type == "success"){
									$.messager.alert("消息提醒","添加成功!","info");
									//关闭窗口
									$("#addDialog").dialog("close");
									//清空原表格数据
									$("#add_name").combobox('setValue', "");
									$("#add_content").textbox('setValue', "");
									$("#add_address").textbox('setValue', "");
									$("#add_time").datebox('setValue',"");
									//重新刷新页面数据
						  			$('#dataList').datagrid("reload");
									
								} else{
									$.messager.alert("消息提醒",data.msg,"warning");
									return;
								}
							}
						});
					}
				}
			},
			
		],
		onClose: function(){
			$("#add_content").textbox('setValue', "");
			$("#add_name").combobox('setValue', "");
			$("#add_address").textbox('setValue', "");
		}
    });
  	
  
  	
  	//编辑用户信息
  	$("#editDialog").dialog({
  		title: "修改用户信息",
    	width: 400,
    	height: 500,
    	iconCls: "icon-edit",
    	modal: true,
    	collapsible: false,
    	minimizable: false,
    	maximizable: false,
    	draggable: true,
    	closed: true,
    	buttons: [
			
    		{
				text:'提交',
				plain: true,
				iconCls:'icon-edit',
				handler:function(){			
					var validate = $("#editForm").form("validate");
					if(!validate){
						$.messager.alert("消息提醒","请检查你输入的数据!","warning");
						return;
					} else{
						var data = $("#editForm").serialize();							
						$.ajax({
							type: "post",
							url: "edit",
							data: data,
							dataType:'json',
							success: function(data){
								if(data.type == "success"){
									$.messager.alert("消息提醒","修改成功!","info");
									//关闭窗口
									$("#editDialog").dialog("close");
									//清空原表格数据	
									//重新刷新页面数据
						  			$('#dataList').datagrid("reload");
						  			$('#dataList').datagrid("uncheckAll");
									
								} else{
									$.messager.alert("消息提醒",data.msg,"warning");
									return;
								}
							}
						});
					}
				}
			},
		
		],
		onBeforeOpen: function(){
			var selectRow = $("#dataList").datagrid("getSelected");
			//设置值
			var time = selectRow.newstime.split("-");
			var time1=time[2].split(" ");
			var endtime = time[1] + "/" + time1[0] + "/" + time[0]+" "+time1[1];
			$("#edit-id").val( selectRow.id);
			$("#edit_newsname").textbox('setValue', selectRow.newsname);
			$("#edit_newscontent").textbox('setValue', selectRow.newscontent);
			$("#edit_newstype").textbox('setValue', selectRow.newstype);
			$("#edit_newsfbz").textbox('setValue', selectRow.newsfbz);
			$("#edit_time").datebox('setValue', endtime);
						
			
		}
    });
		//搜索按钮
		$("#search-btn").click(function() {
			$('#dataList').datagrid('reload', {
				newstime : $("#search-time").datebox('getValue'),
				newstype :$("#search-type").textbox('getValue')
			});
		});

	});
</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0">

	</table>
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;">
			<a id="add" href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">添加</a>
		</div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;">
			<a id="edit" href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true">修改</a>
		</div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div>
			<a id="delete" href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-some-delete',plain:true">删除</a> 时间查询： <input
				id="search-time" type="text" class="easyui-datebox" >
				新闻类型： <input
				id="search-type" type="text" class="easyui-textbox" >
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>

	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px;">
		<form id="addForm" method="post">
			<table id="addTable" cellpadding="8">
			<tr>
					<td>新闻名称:</td>
					<td><input id="add_newsname"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="newsname"
						data-options="required:true" /></td>
				</tr>
				
				
				<tr>
					<td>新闻发布人:</td>
					<td><input id="add_newsfbr"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="newsfbz"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td>新闻类型:</td>
					<td><input id="add_newstype"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="newstype"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td>发布时间:</td>
					<td><input id="add_time" class="easyui-datebox"
						name="newstime" data-options="required:true,showSeconds:false"
						value="" style="width: 150px"></input></td>
				</tr>
				<tr>
					<td>新闻内容:</td>
					<td><input class="easyui-textbox" name="newscontent" id="add_content"
	    data-options="multiline:true" style="height:200px;width:200px">
	        </input></td>
			</table>
		</form>
	</div>



	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">

		<form id="editForm" method="post">
			<input type="hidden" name="id" id="edit-id">
			<table id="editTable" border=0 cellpadding="8">
				<tr>
					<td>新闻名称:</td>
					<td><input id="edit_newsname"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="newsname"
						data-options="required:true" /></td>
				</tr>
				
				</tr>
				<tr>
					<td>新闻发布人:</td>
					<td><input id="edit_newsfbz"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="newsfbr"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td>新闻类型:</td>
					<td><input id="edit_newstype"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="newstype"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td>发布时间:</td>
					<td><input id="edit_time" class="easyui-datebox"
						name="newstime" data-options="required:true,showSeconds:false"
						value="" style="width: 150px"></input></td>
				</tr>
				<tr>
					<td>新闻内容:</td>
					<td><input class="easyui-textbox" name="newscontent" id="edit_content"
	    data-options="multiline:true" style="height:200px;width:200px">
	        </input></td>
			</table>
		</form>
	</div>

</body>
</html>