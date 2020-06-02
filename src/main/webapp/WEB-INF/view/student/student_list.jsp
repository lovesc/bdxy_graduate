<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/css/demo.css">
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	var colleges = ${collegesJson};
	$(function() {	
		var table;
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'学生信息列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"get_List?t="+new Date().getTime(),
	        idField:'studentid', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'studentid',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
				 {field:'chk',checkbox: true,width:50},
 		         {field:'studentid',title:'学号',width:100, sortable: true},
 		         {field:'studentname',title:'学生姓名	',width:100},
 		         {field:'sex',title:'性别	',width:100},
 		        {field:'studentphone',title:'联系方式	',width:100},
 		        {field:'number',title:'所属院校',width:150, sortable: true,align:"center",
  		        	formatter:function(value,index,row){
  		        		for(var i=0;i<colleges.length;i++){
  		        			if(colleges[i].number == value){
  		        				return colleges[i].name;
  		        			}
  		        		}
  		        		return value;
  		    	   }
  		        },
 		         {field:'state',title:'工作状态	',width:100},
 		         {field:'province',title:'就业省份	',width:100},
 		         {field:'city',title:'市	',width:100},
 		         {field:'name',title:'公司名称',width:100},
 		         {field:'workname',title:'职位名称',width:100},
 		         {field:'information',title:'工作情况	',width:200},
 		         {field:'track',title:'就业后情况',width:100},
 		         {field:'type',title:'就业类型',width:100},
 		         
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
            	var studentids = [];
            	$(selectRows).each(function(i, row){
            		studentids[i] = row.studentid;
            	});	
            	$.messager.confirm("消息提醒", "将删除与用户相关的所有数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "delete",
							data: {studentids: studentids},
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
	    
	  	
	  	//编辑用户信息
	  	$("#editDialog").dialog({
	  		title: "修改就业信息",
	    	width: 500,
	    	height: 650,
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
				$("#edit-id").val( selectRow.id);
				$("#edit-password").val( selectRow.password);
				$("#edit_studentname").textbox('setValue', selectRow.studentname);
				$("#edit_studentid").textbox('setValue', selectRow.studentid);
				$("#edit_number").combobox('setValue', selectRow.number);
				$("#edit_state").combobox('setValue', selectRow.state);
				$("#edit_name").textbox('setValue', selectRow.name);
				$("#edit_workname").combobox('setValue', selectRow.workname);
				$("#edit_province").textbox('setValue', selectRow.province);
				$("#edit_city").textbox('setValue', selectRow.city);
				$("#edit_type").combobox('setValue', selectRow.type);
				$("#edit_information").textbox('setValue', selectRow.information);
				$("#edit_track").textbox('setValue', selectRow.track);
				$("#edit_sex").combobox('setValue', selectRow.sex);
							
				
			}
	    });
	   	
		//搜索按钮
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('reload',{
	  			studentname:$("#search-studentname").textbox('getValue'),
	  			province:$("#search-province").combobox('getValue'),
	  			city:$("#search-city").combobox('getValue'),
	  			type:$("#search-type").combobox('getValue'),
	  			studentid:$("#search-studentid").textbox('getValue'),
	  			
	  		});
	  	});$('#search-province').combobox(
				{
					onSelect : function() {
						var newValue = $('#search-province').combobox('getValue');
						$('#search-city').combobox('clear');
						$('#search-city').combobox('reload',
								'getcity?province=' + newValue)
					}

				});
		$('#edit_province').combobox(
						{
					onSelect : function() {
					var newValue = $('#edit_province').combobox('getValue');
					$('#edit_city').combobox('clear');
					$('#edit_city').combobox('reload','getcity?province=' + newValue)
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
		<!-- <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div> -->
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div>
			<a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>
			学生姓名：<input
				id="search-studentname" class="easyui-textbox" />
				学号：<input
				id="search-studentid" class="easyui-textbox" /> 所属省份： <select
				id="search-province" class="easyui-combobox" style="width: 100px;">
				<option value="">全部</option>
				<c:forEach items="${provincelist }" var="p">
					<option value="${p }">${p }</option>
				</c:forEach>
			</select> 所属市： <select id="search-city" class="easyui-combobox"
				valueField="id" textField="text" panelHeight="auto"
				style="width: 150px;">
				<option value="">全部</option>
			</select>
			</select> 工作类型： <select id="search-type" class="easyui-combobox"
				style="width: 150px;">
				<option value="">全部</option>
				<option value="计算机">计算机</option>
				<option value="金融">金融</option>
				<option value="医疗">医疗</option>
				<option value="销售">销售</option>
				<option value="教育">教育</option>
			</select> 
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>
	
	
	
	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">		 
    	<form id="editForm" method="post">
    		<input type="hidden" name="id" id="edit-id">
    		<input type="hidden" name="password" id="edit-password">
	    	<table id="editTable" border=0  cellpadding="8" >
	    		<tr >
	    			<td>学生学号:</td>
	    			<td>
	    				<input id="edit_studentid"  readonly="readonly" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="studentid" data-options="required:true, missingMessage:'请填写学号'"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>学生姓名:</td>
	    			<td>
	    				<input id="edit_studentname" readonly="readonly"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="studentname" data-options="required:true, missingMessage:'请填写姓名'"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>性别:</td>
	    			<td>
	    				<select id="edit_sex" readonly="readonly"  class="easyui-combobox" style="width: 200px;" name="sex" data-options="required:true, missingMessage:'请选择性别'">
	    						<option value="男">男</option>
	    						<option value="女">女</option>						
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>学生联系方式:</td>
	    			<td>
	    				<input id="edit_studentphone"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="studenphone"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>院校名称:</td>
	    			<td>
	    				<select id="edit_number"  readonly="readonly" class="easyui-combobox" style="width: 200px;" name="number" data-options="required:true, missingMessage:'请选择所属院校'">
	    					<c:forEach items="${ colleges}" var="pro">
	    						<option value="${pro.number }">${pro.name}</option>
	    					</c:forEach>			
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>工作状态:</td>
	    			<td>
	    				<select id="edit_state"  class="easyui-combobox" style="width: 200px;" name="state" >
	    						<option value="就业">就业</option>
	    						<option value="待业">待业</option>						
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>公司名称:</td>
	    			<td>
	    				<input id="edit_name"   class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="name"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>工作职位:</td>
	    			<td>
	    				<input id="edit_worknam"   class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="workname"  />
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
			<td>
			 工作类型：</td>
			 <td> <select id="edit_type" class="easyui-combobox" name="type"
				style="width: 150px;">
				<option value="">全部</option>
				<option value="计算机">计算机</option>
				<option value="金融">金融</option>
				<option value="医疗">医疗</option>
				<option value="销售">销售</option>
				<option value="教育">教育</option>
			</select> 
			</td>
	    		</tr>
	    		<tr >
	    			<td>工作介绍:</td>
	    			<td>
	    				<input id="edit_information"  class="easyui-textbox" style="width: 200px; height: 200px;" type="text" name="information"  />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>工作一段时间情况:</td>
	    			<td><input id="edit_track" style="width: 200px; height: 150px;" class="easyui-textbox" type="text" name="track"   /></td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>
	
	
</body>
</html>