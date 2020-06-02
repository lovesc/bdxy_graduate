<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<String> citys = (List<String>) request.getSession().getAttribute("citylist");
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
			title : '招聘信息列表',
			iconCls : 'icon-more',//图标 
			border : true,
			collapsible : false,//是否可折叠的 
			fit : true,//自动大小 
			method : "post",
			url : "get_List?t=" + new Date().getTime(),
			idField : 'id',
			singleSelect : false,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			sortName : 'id',
			sortOrder : 'DESC',
			remoteSort : false,
			columns : [ [ {
				field : 'chk',
				checkbox : true,
				width : 50
			}, {
				field : 'id',
				title : 'ID',
				width : 50,
				sortable : true
			}, {
				field : 'name',
				title : '公司名称',
				width : 100,
				sortable : true
			}, {
				field : 'workname',
				title : '工作名称',
				width : 100,
				sortable : true
			}, {
				field : 'email',
				title : '公司邮箱',
				width : 100,
				sortable : true
			}, {
				field : 'companyphone',
				title : '联系电话',
				width : 100,
				sortable : true
			}, {
				field : 'information',
				title : '招聘内容',
				width : 300
			}, {
				field : 'province',
				title : '省份',
				width : 100,
				sortable : true,
				align : "center"
			}, {
				field : 'city',
				title : '市',
				width : 100,
				sortable : true,
				align : "center"
			}, {
				field : 'starttime',
				title : '招聘开始时间',
				width : 150,
				sortable : true,
				align : "center"
			}, {
				field : 'endtime',
				title : '招聘结束时间',
				width : 150,
				sortable : true,
				align : "center"
			}, {
				field : 'type',
				title : '招聘类型',
				width : 150,
				sortable : true,
				align : "center"
			}

			] ],
			toolbar : "#toolbar"
		});
		//设置分页控件 
		var p = $('#dataList').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10 
			pageList : [ 10, 20, 30, 50, 100 ],//可以设置每页记录条数的列表 
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		});
		//设置工具类按钮
		$("#add").click(function() {
			table = $("#addTable");
			$("#addDialog").dialog("open");
		});
		//修改
		$("#edit").click(function() {
			table = $("#editTable");
			var selectRows = $("#dataList").datagrid("getSelections");
			if (selectRows.length != 1) {
				$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
			} else {
				$("#editDialog").dialog("open");
			}
		});

		//删除
		$("#delete").click(
				function() {
					var selectRows = $("#dataList").datagrid("getSelections");
					var selectLength = selectRows.length;
					if (selectLength == 0) {
						$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
					} else {
						var ids = [];
						$(selectRows).each(function(i, row) {
							ids[i] = row.id;
						});
						$.messager.confirm("消息提醒", "将删除与用户相关的所有数据，确认继续？",
								function(r) {
									if (r) {
										$.ajax({
											type : "post",
											url : "delete",
											data : {
												ids : ids
											},
											dateType : 'json',
											success : function(data) {
												if (data == "success") {
													$.messager.alert("消息提醒",
															"删除成功!", "info");
													//刷新表格
													$("#dataList").datagrid(
															"reload");
													$("#dataList").datagrid(
															"uncheckAll");
												} else {
													$.messager
															.alert("消息提醒",
																	data.msg,
																	"warning");
													return;
												}
											}
										});
									}
								});
					}
				});

		//设置添加窗口
		$("#addDialog").dialog(
				{
					title : "添加招聘信息",
					width : 400,
					height : 500,
					iconCls : "icon-add",
					modal : true,
					collapsible : false,
					minimizable : false,
					maximizable : false,
					draggable : true,
					closed : true,
					buttons : [
							{
								text : '添加',
								plain : true,
								iconCls : 'icon-user_add',
								handler : function() {
									var validate = $("#addForm").form(
											"validate");
									if (!validate) {
										$.messager.alert("消息提醒", "请检查你输入的数据!",
												"warning");
										return;
									} else {
										var data = $("#addForm").serialize();
										$.ajax({
											type : "post",
											url : "add",
											data : data,
											dataType : 'json',
											success : function(data) {
												if (data.type == "success") {
													$.messager.alert("消息提醒",
															"添加成功!", "info");
													//关闭窗口
													$("#addDialog").dialog(
															"close");
													//清空原表格数据
													$("#add_name").combobox(
															'setValue', "");
													$("#add_workname").textbox(
															'setValue', "");
													$("#add_information")
															.textbox(
																	'setValue',
																	"");
													$("#add_province")
															.combobox(
																	'setValue',
																	"");
													$("#add_city").combobox(
															'setValue', "");
													$("#add_email").textbox(
															'setValue', "");
													$("#add_type").combobox(
															'setValue', "");
													$("#add_starttime")
															.datebox(
																	'setValue',
																	"");
													$("#add_endtime").datebox(
															'setValue', "");
													//重新刷新页面数据
													$('#dataList').datagrid(
															"reload");

												} else {
													$.messager
															.alert("消息提醒",
																	data.msg,
																	"warning");
													return;
												}
											}
										});
									}
								}
							},

					],
					onClose : function() {
						$("#add_name").combobox('setValue', "");
						$("#add_workname").textbox('setValue', "");
						$("#add_information").textbox('setValue', "");
						$("#add_province").combobox('setValue', "");
						$("#add_city").combobox('setValue', "");
						$("#add_email").textbox('setValue', "");
						$("#add_companyphone").textbox('setValue', "");
						$("#add_type").combobox('setValue', "");
						$("#add_starttime").datebox('setValue', "");
						$("#add_endtime").datebox('setValue', "");
					}
				});

		//编辑用户信息
		$("#editDialog").dialog(
				{
					title : "修改招聘信息",
					width : 350,
					height : 500,
					iconCls : "icon-edit",
					modal : true,
					collapsible : false,
					minimizable : false,
					maximizable : false,
					draggable : true,
					closed : true,
					buttons : [

							{
								text : '提交',
								plain : true,
								iconCls : 'icon-edit',
								handler : function() {
									var validate = $("#editForm").form(
											"validate");
									if (!validate) {
										$.messager.alert("消息提醒", "请检查你输入的数据!",
												"warning");
										return;
									} else {
										var data = $("#editForm").serialize();
										$.ajax({
											type : "post",
											url : "edit",
											data : data,
											dataType : 'json',
											success : function(data) {
												if (data.type == "success") {
													$.messager.alert("消息提醒",
															"修改成功!", "info");
													//关闭窗口
													$("#editDialog").dialog(
															"close");
													//清空原表格数据	
													//重新刷新页面数据
													$('#dataList').datagrid(
															"reload");
													$('#dataList').datagrid(
															"uncheckAll");

												} else {
													$.messager
															.alert("消息提醒",
																	data.msg,
																	"warning");
													return;
												}
											}
										});
									}
								}
							},

					],
					onBeforeOpen : function() {
						var selectRow = $("#dataList").datagrid("getSelected");
						//设置值	
						var time = selectRow.endtime.split("-");
						var time1 = selectRow.starttime.split("-");
						var endtime = time[1] + "/" + time[2] + "/" + time[0];
						var starttime = time1[1] + "/" + time1[2] + "/"
								+ time1[0];
						$("#edit-id").val(selectRow.id);
						$("#edit_name").combobox('setValue', selectRow.name);
						$("#edit_workname").textbox('setValue',
								selectRow.workname);
						$("#edit_information").textbox('setValue',
								selectRow.information);
						$("#edit_province").combobox('setValue',
								selectRow.province);
						$("#edit_city").combobox('setValue', selectRow.city);
						$("#edit_starttime").datebox('setValue', starttime);
						$("#edit_endtime").datebox('setValue', endtime);
						$("#edit_type").textbox('setValue', selectRow.type);
						$("#edit_email").textbox('setValue', selectRow.email);
						$("#edit_companyphone").textbox('setValue',
								selectRow.companyphone);

					}
				});
		//搜索按钮
		$("#search-btn").click(function() {
			$('#dataList').datagrid('reload', {
				name : $("#search-name").combobox('getValue'),
				province : $("#search-province").combobox('getValue'),
				city : $("#search-city").combobox('getValue'),
				type : $("#search-type").combobox('getValue'),
				workname : $("#search-workname").textbox('getValue')
			});

		});


		$('#search-province').combobox(
				{
					onSelect : function() {
						var newValue = $('#search-province').combobox(
								'getValue');
						$('#search-city').combobox('clear');
						$('#search-city').combobox('reload',
								'getcity?province=' + newValue)
					}

				});
		$('#add_province').combobox(
				{
					onSelect : function() {
						var newValue = $('#add_province').combobox('getValue');
						$('#add_city').combobox('clear');
						$('#add_city').combobox('reload',
								'getcity?province=' + newValue)
					}

				});
		$('#edit_province')
				.combobox(
						{
							onSelect : function() {
								var newValue = $('#edit_province').combobox(
										'getValue');
								$('#edit_city').combobox('clear');
								$('#edit_city').combobox('reload',
										'getcity?province=' + newValue)
							}

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
				data-options="iconCls:'icon-some-delete',plain:true">删除</a> 公司名字：<select
				id="search-name" class="easyui-combobox" style="width: 100px;"
				name="name">
				<option value="">全部</option>
				<c:forEach items="${companyname }" var="c">
					<option value="${c }">${c }</option>
				</c:forEach>
			</select> 工作名称：<input id="search-workname" class="easyui-textbox" /> 所属省份： <select
				id="search-province" class="easyui-combobox" style="width: 100px;">
				<option value="">全部</option>
				<c:forEach items="${provincelist }" var="p">
					<option value="${p }">${p }</option>
				</c:forEach>
			</select> 所属市： <select id="search-city" class="easyui-combobox"
				valueField="id" textField="text" panelHeight="auto"
				style="width: 150px;">
				<option value="">全部</option>
			</select> 工作类型： <select id="search-type" class="easyui-combobox"
				style="width: 150px;">
				<option value="">全部</option>
				<option value="计算机">计算机</option>
				<option value="金融">金融</option>
				<option value="医疗">医疗</option>
				<option value="销售">销售</option>
				<option value="教育">教育</option>
			</select> <a id="search-btn" href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>

	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px;">
		<form id="addForm" method="post">
			<table id="addTable" cellpadding="8">
				<tr>
					<td>公司名字:</td>
					<td><select id="add_name" class="easyui-combobox"
						style="width: 150px;" name="name">
							<c:forEach items="${companyname }" var="c">
								<option value="${c }">${c }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>工作名称:</td>
					<td><input id="add_workname" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="workname"
						data-options="required:true, missingMessage:'请填写内容'" /></td>
				</tr>
				<tr>
					<td>招聘内容:</td>
					<td><input class="easyui-textbox" name="information"
						id="add_information" data-options="multiline:true"
						style="height: 200px; width: 200px"> </input></td>
				</tr>
				<tr>
					<td>联系电话:</td>
					<td><input id="add_companyphone" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text"
						name="companyphone"
						data-options="required:true, missingMessage:'请填写联系方式'" /></td>
				</tr>
				<tr>

					<td>所属省份：</td>
					<td><select id="add_province" class="easyui-combobox"
						style="width: 150px;" name="province">
							<option value="">全部</option>
							<c:forEach items="${provincelist}" var="p">
								<option value="${p }">${p }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>所属市：</td>
					<td><select id="add_city" class="easyui-combobox"
						valueField="id" textField="text" panelHeight="auto"
						style="width: 150px;" name="city">
							<option value="">全部</option>
					</select></td>
				</tr>
				<tr>
					<td>工作类型：</td>
					<td><select id="add_type" class="easyui-combobox"
						style="width: 150px;" name="type">
							<option value="">全部</option>
							<option value="计算机">计算机</option>
							<option value="金融">金融</option>
							<option value="医疗">医疗</option>
							<option value="销售">销售</option>
							<option value="教育">教育</option>
					</select></td>
				</tr>
				<tr>
					<td>招聘开始时间:</td>
					<td><input id="add_starttime" type="text" name="starttime"
						class="easyui-datebox" required="required"></input></td>
				</tr>
				<tr>
					<td>招聘结束时间:</td>
					<td><input id="add_endtime" type="text" name="endtime"
						class="easyui-datebox" required="required"></input></td>
				</tr>
				<tr>
					<td>公司邮箱:</td>
					<td><input id="add_email" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="email"
						data-options="required:true, missingMessage:'请填写内容'" /></td>
				</tr>
			</table>
		</form>
	</div>



	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">

		<form id="editForm" method="post">
			<input type="hidden" name="id" id="edit-id">
			<table id="editTable" border=0 cellpadding="8">
				<tr>
					<td>公司名字:</td>
					<td><select id="edit_name" class="easyui-combobox"
						style="width: 150px;" name="name">
							<c:forEach items="${companyname }" var="c">
								<option value="${c }">${c }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>工作名称:</td>
					<td><input id="edit_workname" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="workname"
						data-options="required:true, missingMessage:'请填写内容'" /></td>
				</tr>
				<tr>
					<td>招聘内容:</td>
					<td><input class="easyui-textbox" name="informationt"
						id="edit_information" data-options="multiline:true"
						style="height: 200px; width: 200px"> </input></td>
				</tr>
				<tr>
					<td>公司电话:</td>
					<td><input id="edit_companyphone" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text"
						name="companyphone"
						data-options="required:true, missingMessage:'请填写内容'" /></td>
				</tr>
				<tr>

					<td>所属省份：</td>
					<td><select id="edit_province" class="easyui-combobox"
						style="width: 150px;" name="province">
							<option value="">全部</option>
							<c:forEach items="${provincelist }" var="p">
								<option value="${p }">${p }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>所属市：</td>
					<td><select id="edit_city" class="easyui-combobox"
						valueField="id" textField="text" panelHeight="auto"
						style="width: 150px;" name="city">
							<option value="">全部</option>
					</select></td>
				</tr>
				<tr>
					<td>工作类型：</td>
					<td><select id="edit_type" class="easyui-combobox"
						style="width: 150px;" name="type">
							<option value="">全部</option>
							<option value="计算机">计算机</option>
							<option value="金融">金融</option>
							<option value="医疗">医疗</option>
							<option value="销售">销售</option>
							<option value="教育">教育</option>
					</select></td>
				</tr>
				<tr>
					<td>招聘开始时间:</td>
					<td><input id="edit_starttime" type="text" name="starttime"
						class="easyui-datebox" required="required"></input></td>
				</tr>
				<tr>
					<td>招聘结束时间:</td>
					<td><input id="edit_endtime" type="text" name="endtime"
						class="easyui-datebox" required="required"></input></td>
				</tr>
				<tr>
					<td>公司邮箱:</td>
					<td><input id="edit_email" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="email"
						data-options="required:true, missingMessage:'请填写内容'" /></td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>