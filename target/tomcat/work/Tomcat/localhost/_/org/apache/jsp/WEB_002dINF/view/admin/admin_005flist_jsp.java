/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-04-24 09:53:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<title>用户列表</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(basePath);
      out.write("easyui/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(basePath);
      out.write("easyui/themes/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(basePath);
      out.write("easyui/css/demo.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("easyui/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("easyui/js/validateExtends.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\tvar colleges = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${collegesJson}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("\t$(function() {\t\r\n");
      out.write("\t\tvar table;\r\n");
      out.write("\t\t//datagrid初始化 \r\n");
      out.write("\t    $('#dataList').datagrid({ \r\n");
      out.write("\t        title:'辅导员列表', \r\n");
      out.write("\t        iconCls:'icon-more',//图标 \r\n");
      out.write("\t        border: true, \r\n");
      out.write("\t        collapsible:false,//是否可折叠的 \r\n");
      out.write("\t        fit: true,//自动大小 \r\n");
      out.write("\t        method: \"post\",\r\n");
      out.write("\t        url:\"get_List?t=\"+new Date().getTime(),\r\n");
      out.write("\t        idField:'id', \r\n");
      out.write("\t        singleSelect:false,//是否单选 \r\n");
      out.write("\t        pagination:true,//分页控件 \r\n");
      out.write("\t        rownumbers:true,//行号 \r\n");
      out.write("\t        sortName:'id',\r\n");
      out.write("\t        sortOrder:'DESC', \r\n");
      out.write("\t        remoteSort: false,\r\n");
      out.write("\t        columns: [[  \r\n");
      out.write("\t\t\t\t{field:'chk',checkbox: true,width:50},\r\n");
      out.write(" \t\t        {field:'id',title:'ID',width:50, sortable: true},    \r\n");
      out.write(" \t\t        {field:'username',title:'用户名',width:150, sortable: true},\r\n");
      out.write(" \t\t        {field:'password',title:'密码',width:100},\r\n");
      out.write(" \t\t        {field:'number',title:'所属院校',width:150, sortable: true,align:\"center\",\r\n");
      out.write(" \t\t        \tformatter:function(value,index,row){\r\n");
      out.write(" \t\t        \t\tfor(var i=0;i<colleges.length;i++){\r\n");
      out.write(" \t\t        \t\t\tif(colleges[i].number == value){\r\n");
      out.write(" \t\t        \t\t\t\treturn colleges[i].name;\r\n");
      out.write(" \t\t        \t\t\t}\r\n");
      out.write(" \t\t        \t\t}\r\n");
      out.write(" \t\t        \t\treturn value;\r\n");
      out.write(" \t\t    \t   }\r\n");
      out.write(" \t\t        },\r\n");
      out.write("\t \t\t]], \r\n");
      out.write("\t        toolbar: \"#toolbar\"\r\n");
      out.write("\t    }); \r\n");
      out.write("\t    //设置分页控件 \r\n");
      out.write("\t    var p = $('#dataList').datagrid('getPager'); \r\n");
      out.write("\t    $(p).pagination({ \r\n");
      out.write("\t        pageSize: 10,//每页显示的记录条数，默认为10 \r\n");
      out.write("\t        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 \r\n");
      out.write("\t        beforePageText: '第',//页数文本框前显示的汉字 \r\n");
      out.write("\t        afterPageText: '页    共 {pages} 页', \r\n");
      out.write("\t        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', \r\n");
      out.write("\t    }); \r\n");
      out.write("\t    //设置工具类按钮\r\n");
      out.write("\t    $(\"#add\").click(function(){\r\n");
      out.write("\t    \ttable = $(\"#addTable\");\r\n");
      out.write("\t    \t$(\"#addDialog\").dialog(\"open\");\r\n");
      out.write("\t    });\r\n");
      out.write("\t    //修改\r\n");
      out.write("\t    $(\"#edit\").click(function(){\r\n");
      out.write("\t    \ttable = $(\"#editTable\");\r\n");
      out.write("\t    \tvar selectRows = $(\"#dataList\").datagrid(\"getSelections\");\r\n");
      out.write("        \tif(selectRows.length != 1){\r\n");
      out.write("            \t$.messager.alert(\"消息提醒\", \"请选择一条数据进行操作!\", \"warning\");\r\n");
      out.write("            } else{\r\n");
      out.write("\t\t    \t$(\"#editDialog\").dialog(\"open\");\r\n");
      out.write("            }\r\n");
      out.write("\t    });\r\n");
      out.write("\t    //删除\r\n");
      out.write("\t    $(\"#delete\").click(function(){\r\n");
      out.write("\t    \tvar selectRows = $(\"#dataList\").datagrid(\"getSelections\");\r\n");
      out.write("        \tvar selectLength = selectRows.length;\r\n");
      out.write("        \tif(selectLength == 0){\r\n");
      out.write("            \t$.messager.alert(\"消息提醒\", \"请选择数据进行删除!\", \"warning\");\r\n");
      out.write("            } else{\r\n");
      out.write("            \tvar ids = [];\r\n");
      out.write("            \t$(selectRows).each(function(i, row){\r\n");
      out.write("            \t\tids[i] = row.id;\r\n");
      out.write("            \t});\t\r\n");
      out.write("            \t$.messager.confirm(\"消息提醒\", \"将删除与用户相关的所有数据，确认继续？\", function(r){\r\n");
      out.write("            \t\tif(r){\r\n");
      out.write("            \t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\ttype: \"post\",\r\n");
      out.write("\t\t\t\t\t\t\turl: \"delete\",\r\n");
      out.write("\t\t\t\t\t\t\tdata: {ids: ids},\r\n");
      out.write("\t\t\t\t\t\t\tdateType:'json',\r\n");
      out.write("\t\t\t\t\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\t\t\t\t\tif(data == \"success\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",\"删除成功!\",\"info\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//刷新表格\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#dataList\").datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t $(\"#dataList\").datagrid(\"uncheckAll\"); \r\n");
      out.write("\t\t\t\t\t\t\t\t} else{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",data.msg,\"warning\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("            \t\t}\r\n");
      out.write("            \t});\r\n");
      out.write("            }\r\n");
      out.write("\t    });\r\n");
      out.write("\t    \r\n");
      out.write("\t  \t//设置添加窗口\r\n");
      out.write("\t    $(\"#addDialog\").dialog({\r\n");
      out.write("\t    \ttitle: \"添加用户\",\r\n");
      out.write("\t    \twidth: 400,\r\n");
      out.write("\t    \theight: 300,\r\n");
      out.write("\t    \ticonCls: \"icon-add\",\r\n");
      out.write("\t    \tmodal: true,\r\n");
      out.write("\t    \tcollapsible: false,\r\n");
      out.write("\t    \tminimizable: false,\r\n");
      out.write("\t    \tmaximizable: false,\r\n");
      out.write("\t    \tdraggable: true,\r\n");
      out.write("\t    \tclosed: true,\r\n");
      out.write("\t    \tbuttons: [\r\n");
      out.write("\t    \t\t{\r\n");
      out.write("\t\t\t\t\ttext:'添加',\r\n");
      out.write("\t\t\t\t\tplain: true,\r\n");
      out.write("\t\t\t\t\ticonCls:'icon-user_add',\r\n");
      out.write("\t\t\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\t\t\tf();\r\n");
      out.write("\t\t\t\t\t\tvar validate = $(\"#addForm\").form(\"validate\");\r\n");
      out.write("\t\t\t\t\t\tif(!validate){\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",\"请检查你输入的数据!\",\"warning\");\r\n");
      out.write("\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t} else{\r\n");
      out.write("\t\t\t\t\t\t\tvar data = $(\"#addForm\").serialize();\r\n");
      out.write("\t\t\t\t\t\t\tconsole.log(data);\r\n");
      out.write("\t\t\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\t\ttype: \"post\",\r\n");
      out.write("\t\t\t\t\t\t\t\turl: \"add\",\r\n");
      out.write("\t\t\t\t\t\t\t\tdata: data,\r\n");
      out.write("\t\t\t\t\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\t\t\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(data.type == \"success\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",\"添加成功!\",\"info\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//关闭窗口\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\"#addDialog\").dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//清空原表格数据\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\"#add_username\").textbox('setValue', \"\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\"#add_password\").textbox('setValue', \"\");\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//重新刷新页面数据\r\n");
      out.write("\t\t\t\t\t\t\t  \t\t\t$('#dataList').datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} else{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",data.msg,\"warning\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t],\r\n");
      out.write("\t\t\tonClose: function(){\r\n");
      out.write("\t\t\t\t$(\"#add_username\").textbox('setValue', \"\");\r\n");
      out.write("\t\t\t\t$(\"#add_password\").textbox('setValue', \"\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t    });\r\n");
      out.write("\t  \t\r\n");
      out.write("\t  \r\n");
      out.write("\t  \t\r\n");
      out.write("\t  \t//编辑用户信息\r\n");
      out.write("\t  \t$(\"#editDialog\").dialog({\r\n");
      out.write("\t  \t\ttitle: \"修改用户信息\",\r\n");
      out.write("\t    \twidth: 350,\r\n");
      out.write("\t    \theight: 400,\r\n");
      out.write("\t    \ticonCls: \"icon-edit\",\r\n");
      out.write("\t    \tmodal: true,\r\n");
      out.write("\t    \tcollapsible: false,\r\n");
      out.write("\t    \tminimizable: false,\r\n");
      out.write("\t    \tmaximizable: false,\r\n");
      out.write("\t    \tdraggable: true,\r\n");
      out.write("\t    \tclosed: true,\r\n");
      out.write("\t    \tbuttons: [\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t    \t\t{\r\n");
      out.write("\t\t\t\t\ttext:'提交',\r\n");
      out.write("\t\t\t\t\tplain: true,\r\n");
      out.write("\t\t\t\t\ticonCls:'icon-edit',\r\n");
      out.write("\t\t\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\t\t\tg();\r\n");
      out.write("\t\t\t\t\t\tvar validate = $(\"#editForm\").form(\"validate\");\r\n");
      out.write("\t\t\t\t\t\tif(!validate){\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",\"请检查你输入的数据!\",\"warning\");\r\n");
      out.write("\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t} else{\r\n");
      out.write("\t\t\t\t\t\t\tvar data = $(\"#editForm\").serialize();\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\t\ttype: \"post\",\r\n");
      out.write("\t\t\t\t\t\t\t\turl: \"edit\",\r\n");
      out.write("\t\t\t\t\t\t\t\tdata: data,\r\n");
      out.write("\t\t\t\t\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\t\t\t\t\tsuccess: function(data){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(data.type == \"success\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",\"修改成功!\",\"info\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//关闭窗口\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\"#editDialog\").dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//清空原表格数据\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//重新刷新页面数据\r\n");
      out.write("\t\t\t\t\t\t\t  \t\t\t$('#dataList').datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t\t  \t\t\t$('#dataList').datagrid(\"uncheckAll\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} else{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$.messager.alert(\"消息提醒\",data.msg,\"warning\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t],\r\n");
      out.write("\t\t\tonBeforeOpen: function(){\r\n");
      out.write("\t\t\t\tvar selectRow = $(\"#dataList\").datagrid(\"getSelected\");\r\n");
      out.write("\t\t\t\t//设置值\r\n");
      out.write("\t\t\t\t$(\"#edit-id\").val( selectRow.id);\r\n");
      out.write("\t\t\t\t$(\"#edit_username\").textbox('setValue', selectRow.username);\r\n");
      out.write("\t\t\t\t$(\"#edit_password\").textbox('setValue', selectRow.password);\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t    });\r\n");
      out.write("\t   \t\r\n");
      out.write("\t\t//搜索按钮\r\n");
      out.write("\t  \t$(\"#search-btn\").click(function(){\r\n");
      out.write("\t  \t\t$('#dataList').datagrid('reload',{\r\n");
      out.write("\t  \t\t\tnumber:$(\"#search-colleges\").combobox('getValue')\r\n");
      out.write("\t  \t\t});\r\n");
      out.write("\t  \t});\r\n");
      out.write("\t  \r\n");
      out.write("\t    \r\n");
      out.write("\t});\r\n");
      out.write("\tfunction f(){\r\n");
      out.write("\t\tfor(var i=0;i<colleges.length;i++){\r\n");
      out.write(" \t\t\tif(colleges[i].name==$(\"#add_colleges\").textbox('getText')){\r\n");
      out.write(" \t\t\t\tdocument.getElementById(\"add_colleges\").value=colleges[i].number;\r\n");
      out.write(" \t\t\t\t//alert(\"123\");\r\n");
      out.write(" \t\t\t}\r\n");
      out.write(" \t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction g(){\r\n");
      out.write("\t\tfor(var i=0;i<colleges.length;i++){\r\n");
      out.write(" \t\t\tif(colleges[i].number==$(\"#edit_colleges\").textbox('getValue')){\r\n");
      out.write(" \t\t\t\tdocument.getElementById(\"edit_colleges\").value=colleges[i].name;\r\n");
      out.write(" \t\t\t\t//alert(\"123\");\r\n");
      out.write(" \t\t\t}\r\n");
      out.write(" \t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- 数据列表 -->\r\n");
      out.write("\t<table id=\"dataList\" cellspacing=\"0\" cellpadding=\"0\"> \r\n");
      out.write("\t    \r\n");
      out.write("\t</table> \r\n");
      out.write("\t<!-- 工具栏 -->\r\n");
      out.write("\t<div id=\"toolbar\">\r\n");
      out.write("\t\t<div style=\"float: left;\"><a id=\"add\" href=\"javascript:;\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\">添加</a></div>\r\n");
      out.write("\t\t\t<div style=\"float: left;\" class=\"datagrid-btn-separator\"></div>\r\n");
      out.write("\t\t<div style=\"float: left;\"><a id=\"edit\" href=\"javascript:;\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\">修改</a></div>\r\n");
      out.write("\t\t\t<div style=\"float: left;\" class=\"datagrid-btn-separator\"></div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<a id=\"delete\" href=\"javascript:;\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-some-delete',plain:true\">删除</a>\r\n");
      out.write("\t\t\t所属院校：\r\n");
      out.write("\t\t\t<select id=\"search-colleges\" class=\"easyui-combobox\" style=\"width: 200px;\">\r\n");
      out.write("\t\t\t\t<option value=\"\">全部</option>\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t<a id=\"search-btn\" href=\"javascript:;\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-search',plain:true\">搜索</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t<!-- 添加窗口 -->\r\n");
      out.write("\t<div id=\"addDialog\" style=\"padding: 10px;\">  \r\n");
      out.write("   \t\t<form id=\"addForm\" method=\"post\">\r\n");
      out.write("\t    \t<table id=\"addTable\" cellpadding=\"8\">\r\n");
      out.write("\t    \t\t<tr >\r\n");
      out.write("\t    \t\t\t<td>用户名:</td>\r\n");
      out.write("\t    \t\t\t<td>\r\n");
      out.write("\t    \t\t\t\t<input id=\"add_username\"  class=\"easyui-textbox\" style=\"width: 200px; height: 30px;\" type=\"text\" name=\"username\" data-options=\"required:true, missingMessage:'请填写用户名'\"  />\r\n");
      out.write("\t    \t\t\t</td>\r\n");
      out.write("\t    \t\t</tr>\r\n");
      out.write("\t    \t\t<tr>\r\n");
      out.write("\t    \t\t\t<td>密码:</td>\r\n");
      out.write("\t    \t\t\t<td><input id=\"add_password\" style=\"width: 200px; height: 30px;\" class=\"easyui-textbox\" type=\"password\" name=\"password\" data-options=\"required:true, missingMessage:'请填写密码'\"  /></td>\r\n");
      out.write("\t    \t\t</tr>\r\n");
      out.write("\t    \t\t<tr >\r\n");
      out.write("\t    \t\t\t<td>院校名称:</td>\r\n");
      out.write("\t    \t\t\t<td>\r\n");
      out.write("\t    \t\t\t\t<select id=\"add_colleges\"  class=\"easyui-combobox\" style=\"width: 200px;\" name=\"number\" data-options=\"required:true, missingMessage:'请选择所属院校'\">\r\n");
      out.write("\t    \t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\t\t\t\r\n");
      out.write("\t    \t\t\t\t</select>\r\n");
      out.write("\t    \t\t\t</td>\r\n");
      out.write("\t    \t\t</tr>\r\n");
      out.write("\t    \t</table>\r\n");
      out.write("\t    </form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 修改窗口 -->\r\n");
      out.write("\t<div id=\"editDialog\" style=\"padding: 10px\">\r\n");
      out.write("\t\t \r\n");
      out.write("    \t<form id=\"editForm\" method=\"post\">\r\n");
      out.write("    \t\t<input type=\"hidden\" name=\"id\" id=\"edit-id\">\r\n");
      out.write("\t    \t<table id=\"editTable\" border=0  cellpadding=\"8\" >\r\n");
      out.write("\t    \t\t<tr >\r\n");
      out.write("\t    \t\t\t<td>用户名:</td>\r\n");
      out.write("\t    \t\t\t<td>\r\n");
      out.write("\t    \t\t\t\t<input id=\"edit_username\"  class=\"easyui-textbox\" style=\"width: 200px; height: 30px;\" type=\"text\" name=\"username\" data-options=\"required:true, missingMessage:'请填写用户名'\"  />\r\n");
      out.write("\t    \t\t\t</td>\r\n");
      out.write("\t    \t\t</tr>\r\n");
      out.write("\t    \t\t<tr>\r\n");
      out.write("\t    \t\t\t<td>密码:</td>\r\n");
      out.write("\t    \t\t\t<td><input id=\"edit_password\" style=\"width: 200px; height: 30px;\" class=\"easyui-textbox\" type=\"password\" name=\"password\" data-options=\"required:true, missingMessage:'请填写密码'\"  /></td>\r\n");
      out.write("\t    \t\t</tr>\r\n");
      out.write("\t    \t\t<tr >\r\n");
      out.write("\t    \t\t\t<td>院校名称:</td>\r\n");
      out.write("\t    \t\t\t<td>\r\n");
      out.write("\t    \t\t\t\t<select id=\"edit_colleges\"  class=\"easyui-combobox\" style=\"width: 200px;\" name=\"number\" data-options=\"required:true, missingMessage:'请选择所属院校'\">\r\n");
      out.write("\t    \t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f2(_jspx_page_context))
        return;
      out.write("\t\t\t\r\n");
      out.write("\t    \t\t\t\t</select>\r\n");
      out.write("\t    \t\t\t</td>\r\n");
      out.write("\t    \t\t</tr>\r\n");
      out.write("\t    \t</table>\r\n");
      out.write("\t    </form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/view/admin/admin_list.jsp(282,4) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/admin/admin_list.jsp(282,4) '${colleges}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${colleges}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/view/admin/admin_list.jsp(282,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("c");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t    \t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.number }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t    \t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /WEB-INF/view/admin/admin_list.jsp(308,10) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/admin/admin_list.jsp(308,10) '${ colleges}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${ colleges}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/view/admin/admin_list.jsp(308,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("pro");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t    \t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pro.number }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pro.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t    \t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f2.setParent(null);
    // /WEB-INF/view/admin/admin_list.jsp(340,10) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/admin/admin_list.jsp(340,10) '${ colleges}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${ colleges}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/view/admin/admin_list.jsp(340,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVar("pro");
    int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
      if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t    \t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pro.number }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pro.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t    \t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f2.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
    }
    return false;
  }
}
