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
<script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<div id="main" style="width: 100%; height: 500px;"></div>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        	$.post("/student/get_student",function(data){
        		  var myChart = echarts.init(document.getElementById('main'));
        	        // 指定图表的配置项和数据
        	        var option = {
        	            title: {
        	                text: '毕业生的就业情况'
        	            },
        	            toolbox:{},
        	            
        	            tooltip:{},
        	            legend: {
        	                data:['就业率']
        	            },
        	            xAxis: {
        	                data: ["计算机", "金融", "医疗", "销售", "教育", "法律","其他"]
        	            },
        	            yAxis: {},
        	        
      
        	            series: [{
        	                name: '就业率',
        	                type: 'bar',
        	                data: data,
        	                label: {
            	                show: true, // 开启显示
            	                position: 'top', // 在上方显示
            	                distance: 20, // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。
            	                verticalAlign: 'middle',
            	                textStyle: { // 数值样式
            	                  color: 'black',
            	                  fontSize: 16
            	                }
            	              }
        	            }]
        	        };
        	        // 使用刚指定的配置项和数据显示图表。
        	        myChart.setOption(option);
        	})
        
     
    </script>
</body>
</html>