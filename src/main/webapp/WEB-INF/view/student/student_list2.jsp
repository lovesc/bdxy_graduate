	
	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>学生列表</title>
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
<script type="text/javascript">
	var colleges = ${collegesJson};
	$(function() {
		var table;
		//datagrid初始化 
		$('#dataList').datagrid({
			title : '学生信息列表',
			iconCls : 'icon-more',//图标 
			border : true,
			collapsible : false,//是否可折叠的 
			fit : true,//自动大小 
			method : "post",
			url : "get_List?t=" + new Date().getTime(),
			idField : 'studentid',
			singleSelect : false,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			sortName : 'studentid',
			sortOrder : 'DESC',
			remoteSort : false,
			columns : [ [ {
				field : 'chk',
				checkbox : true,
				width : 50
			}, {
				field : 'studentid',
				title : '学号',
				width : 100,
				sortable : true
			}, {
				field : 'studentname',
				title : '学生姓名	',
				width : 100
			},{
				field : 'studentphone',
				title : '联系电话	',
				width : 100
			},
			{
				field : 'sex',
				title : '性别	',
				width : 100
			}, {
				field : 'number',
				title : '所属院校',
				width : 150,
				sortable : true,
				align : "center",
				formatter : function(value, index, row) {
					for (var i = 0; i < colleges.length; i++) {
						if (colleges[i].number == value) {
							return colleges[i].name;
						}
					}
					return value;
				}
			}, {
				field : 'role',
				title : '激活状态	',
				width : 100
			}, ] ],
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
		//设置上传窗口的打开
		$("#import").click(function() {
			table = $("#importTable");
			$("#import-dialog").dialog("open");
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
						var studentids = [];
						$(selectRows).each(function(i, row) {
							studentids[i] = row.studentid;
						});

						$.messager.confirm("消息提醒", "如果学生信息下存在其他相关信息，则无法删除！",
								function(r) {
									if (r) {
										$.ajax({
											type : "post",
											url : "delete",
											data : {
												studentids : studentids
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
		//上传导入文件
		$('#import-dialog').dialog({
			closed : true,
			modal : true,
			title : "导入信息",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					submitFile();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#import-dialog').dialog('close');
				}
			} ],
			onBeforeOpen : function() {
			}
		});
		//设置添加窗口
		$("#addDialog").dialog(
				{
					title : "添加学生信息",
					width : 400,
					height : 550,
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
									/* if (!validate) {
										$.messager.alert("消息提醒", "请检查你输入的数据!",
												"warning");
										return;
									}  else {*/
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
													$("#add_studentname")
															.textbox(
																	'setValue',
																	"");
													$("#add_studentid")
															.textbox(
																	'setValue',
																	"");
													
													$("#add_sex").combobox(
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
							}, ],
					onClose : function() {
						$("#add_studentname").textbox('setValue', "");
						$("#add_studentid").textbox('setValue', "");
						$("#add_number").combobox('setValue', "");
						$("#add_state").combobox('setValue', "");		
						$("#add_sex").combobox('setValue', "");
					}
				});

		//编辑班级信息
		$("#editDialog").dialog(
				{
					title : "修改学生信息",
					width : 400,
					height : 550,
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
							}, ],
					onBeforeOpen : function() {
						var selectRow = $("#dataList").datagrid("getSelected");
						//设置值
						$("#edit-id").val(selectRow.id);
						$("#edit-password").val(selectRow.password);
						$("#edit_studentname").textbox('setValue',
								selectRow.studentname);
						$("#edit_studentid").textbox('setValue',
								selectRow.studentid);
						$("#edit_studentphone").textbox('setValue',
								selectRow.studentphone);
						$("#edit_number")
								.combobox('setValue', selectRow.number);
						$("#edit_state").combobox('setValue', selectRow.state);
						$("#edit_province").textbox('setValue',
								selectRow.province);
						$("#edit_city").combobox('setValue', selectRow.city);
						$("#edit_name").textbox('setValue', selectRow.name);
						$("#edit_workname").textbox('setValue', selectRow.workname);
						$("#edit_type").combobox('setValue', selectRow.type);
						$("#edit_information").textbox('setValue',
								selectRow.information);
						$("#edit_track").textbox('setValue', selectRow.track);
						$("#edit_sex").combobox('setValue', selectRow.sex);
					}
				});

		//搜索按钮
		$("#search-btn").click(function() {
			$('#dataList').datagrid('reload', {
				studentname : $("#search-studentname").textbox('getValue'),
				studentid : $("#search-studentid").textbox('getValue'),
				role : $("#search-role").combobox('getValue'),
			});
		});
		$('#edit_province').combobox({
			onSelect : function() {
				console.log(123);
				var newValue = $('#edit_province').combobox('getValue');
				$('#edit_city').combobox('clear');
				$('#edit_city').combobox('reload', 'getcity?province=' + newValue)
			}
		});
	});

	//控制进度条
	function start() {
		var value = $('#p').progressbar('getValue');
		if (value < 100) {
			value += Math.floor(Math.random() * 10);
			$('#p').progressbar('setValue', value);
		} else {
			$('#p').progressbar('setValue', 0)
		}
	}
	//上传文件
	function submitFile() {
		if ($("#import-file").val() == '') {
			$.messager.alert("消息提醒", "请选择文件!", "warning");
			return;
		}
		var formData = new FormData();
		formData.append('excelFile',
				document.getElementById('import-file').files[0]);
		$("#process-dialog").dialog('open');
		var interval = setInterval(start, 200);
		$.ajax({
			url : 'import_student',
			type : 'post',
			data : formData,
			contentType : false,
			processData : false,
			success : function(data) {
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if (data.type == 'success') {
					$.messager.alert("消息提醒", data.msg, "info");
					$('#import-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				} else {
					$.messager.alert("消息提醒", data.msg, "warning");
				}
			},
			error : function(data) {
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒", "上传失败!", "warning");
			}
		});
	}

	function uploadFile() {
		$("#import-file").click();
	}
	function selectedFile() {
		$("#filename").val($("#import-file").val());
	}
	
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
				data-options="iconCls:'icon-some-delete',plain:true">删除</a> 学号：<input
				id="search-studentid" class="easyui-textbox" /> 姓名：<input
				id="search-studentname" class="easyui-textbox" /> 激活状态： <select
				id="search-role" class="easyui-combobox" style="width: 150px;">
				<option value="">全部</option>
				<option value="未激活">未激活</option>
				<option value="已激活">已激活</option>
			</select> <a id="search-btn" href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true">搜索</a>
			<div style="float: left;">
				<a id="import" href="javascript:;" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true">导入excel文件</a>
			</div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		</div>
	</div>

	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px;">
		<form id="addForm" method="post">
			<table id="addTable" cellpadding="8">
				<tr>
					<td>学生学号:</td>
					<td><input id="add_studentid" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="studentid"
						data-options="required:true, missingMessage:'请填写用户名'" /></td>
				</tr>
				<tr>
					<td>学生姓名:</td>
					<td><input id="add_studentname" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="studentname"
						data-options="required:true, missingMessage:'请填写用户名'" /></td>
				</tr>
				<tr>
					<td>联系方式:</td>
					<td><input id="add_studentphone" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="studentphone"
						data-options="required:true, missingMessage:'请填写用户名'" /></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><select id="add_sex" class="easyui-combobox"
						style="width: 200px;" name="sex"
						data-options="required:true, missingMessage:'请选择所属院校'">
							<option value="">全部</option>
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>



	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">
		<form id="editForm" method="post">
			<input type="hidden" name="id" id="edit-id"> <input
				type="hidden" name="password" id="edit-password">
			<table id="editTable" border=0 cellpadding="8">
				<tr>
					<td>学生学号:</td>
					<td><input id="edit_studentid" readonly="readonly"
						class="easyui-textbox" style="width: 200px; height: 30px;"
						type="text" name="studentid"
						data-options="required:true, missingMessage:'请填写用户名'" /></td>
				</tr>
				<tr>
					<td>学生姓名:</td>
					<td><input id="edit_studentname" readonly="readonly"
						class="easyui-textbox" style="width: 200px; height: 30px;"
						type="text" name="studentname"
						data-options="required:true, missingMessage:'请填写用户名'" /></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><select id="edit_sex" readonly="readonly"
						class="easyui-combobox" style="width: 200px;" name="sex"
						data-options="required:true, missingMessage:'请选择所属院校'">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>院校名称:</td>
					<td><select id="edit_number" readonly="readonly"
						class="easyui-combobox" style="width: 200px;" name="number"
						data-options="required:true, missingMessage:'请选择所属院校'">
							<c:forEach items="${ colleges}" var="pro">
								<option value="${pro.number }">${pro.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>联系方式:</td>
					<td><input id="edit_studentphone" 
						class="easyui-textbox" style="width: 200px; height: 30px;"
						type="text" name="studentphone"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td>工作状态:</td>
					<td><select id="edit_state" class="easyui-combobox"
						style="width: 200px;" name="state"
						data-options="required:true, missingMessage:'请选择所属院校'">
							<option value="就业">就业</option>
							<option value="待业">待业</option>
					</select></td>
				</tr>
				<tr>
					<td>公司名称:</td>
					<td><input id="edit_name" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="name" /></td>
				</tr>
				<tr>
					<td>工作职位:</td>
					<td><input id="edit_worknam" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="workname" />
					</td>
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
					<td><select id="edit_type" class="easyui-combobox" name="type"
						style="width: 150px;">
							<option value="">全部</option>
							<option value="计算机">计算机</option>
							<option value="金融">金融</option>
							<option value="医疗">医疗</option>
							<option value="销售">销售</option>
							<option value="教育">教育</option>
							<option value="法律">法律</option>
							<option value="其他">其他</option>
					</select></td>
				</tr>
				<tr>
					<td>工作介绍:</td>
					<td><input class="easyui-textbox" name="information"
						id="edit_information" data-options="multiline:true"
						style="height: 200px; width: 200px"></td>
						
				</tr>
				<tr>
					<td>工作一段时间情况:</td>
					<td><input class="easyui-textbox" name="track"
						id="edit_track" data-options="multiline:true"
						style="height: 200px; width: 200px"></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 导入窗口 -->
	<div id="import-dialog" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 480px; padding: 10px;">
		<form id="import-form" action="import_student" method="post">
			<table id="importTable" border=0 cellpadding="8">
				<tr>
					<td width="80px" align="right">选择文件:</td>
					<td><input type="text" id="filename" readOnly="readOnly"
						class="wu-text easyui-validatebox"
						data-options="required:true, missingMessage:'请选择文件'" /> <input
						type="file" onchange="selectedFile()" name="importedFile"
						id="import-file" style="display: none;" /></td>
					<td><a style="float: left;" href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-upload"
						onclick="uploadFile()" plain="true">选择文件</a></td>
				</tr>
			</table>
		</form>
	</div>
	<!--  进度条 -->
	<div id="process-dialog" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'"
		style="width: 450px; padding: 10px;">
		<div id="p" class="easyui-progressbar" style="width: 400px;"
			data-options="text:'正在上传中...'"></div>
	</div>
</body>
</html>