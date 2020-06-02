<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 引入 ECharts 文件 -->
<script type="text/javascript" src="${ctxStatic}/js/echarts.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var ID=document.getElementById("id").value;
	
	var ctx="${ctx}";
	var url = ctx+"/sys/attendanceInfo/echars?id="+ID;
	var id = 'main';
	setChartBar(url);
	});

//设置ajax访问后台填充柱图
 function setChartBar(url){
	 var year=document.getElementById("year").value;
	 var Chart=echarts.init(document.getElementById("main"));
	 Chart.showLoading(
			 {text: 'Loding...'  }
	);
	 $.ajax({
       	url:url,
       	dataType:"json",
       	type:'post',
       	success:function(map){
			categories = map.months;//月份
            values = map.salesVolume;//工资元
            	
	       	var option = {  
	       		color: ['#3398DB'],
	       		title : {
	       		     	text: '考勤信息',
	       		       
	       		},
	       		tooltip: {  
       	            show: true  
       	        }, 
       	        
       	        legend: {  
       	            data: ['工资']  
       	        }, 
       	        xAxis: [  
       	            {  
       	                type: 'category',  
       	                data: categories  
       	            }  
       	        ],  
       	        yAxis: [  
       	            {  
       	                type: 'value'  
       	            }  
       	        ],  
       	     	
       	        series: [{  
       	                'name': '柱状图',  
       	                'type': 'bar', 
       	                'data': values,
       	             	barWidth : 30,//柱图宽度
       	                
       	         	itemStyle:{
       	        		normal:{
       	        			
       	        			label:{
       	        				show: true, //开启显示
       	        				position: 'top', //在上方显示
       	        				textStyle: { 
       	        					color: 'black',
       	        					fontSize: 16
       	        			}
       	        		}
       	        	}
       	     		
       	         }
       	 
            },
            	
           		 {
            		'name': '折线图',  
	                'type': 'line', 
	                'data': values,
            	
            }
          
       	 ]  
       	    };
	        Chart.hideLoading();
	       	Chart.setOption(option);  
	       	}
       	});
 } 
</script>
</head>
 
<body>
	<div id="main"  style="width: 600px;height:400px;"></div>
	<input type="hidden" id="id" name="id" value="${id}">
	<input type="hidden" id="year" name="year" value="${year}">
</body>
 
</html>
