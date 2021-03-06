/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-05-17 01:42:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class student_005flist3_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      out.write("<script src=\"https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("easyui/jquery.min.js\"></script>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>用户列表</title>\r\n");
      out.write("<div id=\"main\" style=\"width: 100%; height: 500px;\"></div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("        // 基于准备好的dom，初始化echarts实例\r\n");
      out.write("        \t$.post(\"/student/get_student\",function(data){\r\n");
      out.write("        \t\t  var myChart = echarts.init(document.getElementById('main'));\r\n");
      out.write("        \t        // 指定图表的配置项和数据\r\n");
      out.write("        \t        var option = {\r\n");
      out.write("        \t            title: {\r\n");
      out.write("        \t                text: '毕业生的就业情况'\r\n");
      out.write("        \t            },\r\n");
      out.write("        \t            toolbox:{},\r\n");
      out.write("        \t            \r\n");
      out.write("        \t            tooltip:{},\r\n");
      out.write("        \t            legend: {\r\n");
      out.write("        \t                data:['就业率']\r\n");
      out.write("        \t            },\r\n");
      out.write("        \t            xAxis: {\r\n");
      out.write("        \t                data: [\"计算机\", \"金融\", \"医疗\", \"销售\", \"教育\", \"法律\",\"其他\"]\r\n");
      out.write("        \t            },\r\n");
      out.write("        \t            yAxis: {},\r\n");
      out.write("        \t        \r\n");
      out.write("      \r\n");
      out.write("        \t            series: [{\r\n");
      out.write("        \t                name: '就业率',\r\n");
      out.write("        \t                type: 'bar',\r\n");
      out.write("        \t                data: data,\r\n");
      out.write("        \t                label: {\r\n");
      out.write("            \t                show: true, // 开启显示\r\n");
      out.write("            \t                position: 'top', // 在上方显示\r\n");
      out.write("            \t                distance: 20, // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。\r\n");
      out.write("            \t                verticalAlign: 'middle',\r\n");
      out.write("            \t                textStyle: { // 数值样式\r\n");
      out.write("            \t                  color: 'black',\r\n");
      out.write("            \t                  fontSize: 16\r\n");
      out.write("            \t                }\r\n");
      out.write("            \t              }\r\n");
      out.write("        \t            }]\r\n");
      out.write("        \t        };\r\n");
      out.write("        \t        // 使用刚指定的配置项和数据显示图表。\r\n");
      out.write("        \t        myChart.setOption(option);\r\n");
      out.write("        \t})\r\n");
      out.write("        \r\n");
      out.write("     \r\n");
      out.write("    </script>\r\n");
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
}
