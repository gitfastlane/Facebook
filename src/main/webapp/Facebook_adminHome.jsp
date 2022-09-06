<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="CSS/AdminHome.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminFooter.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminHeader.css">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawVisualization);
		google.charts.setOnLoadCallback(drawVisualizationM);
		google.charts.setOnLoadCallback(drawChart);
		google.charts.setOnLoadCallback(drawChartA);
	
		function drawVisualization() { 
			var nameArr = [['Day', '게시글']];
			<c:forEach items="${countArr}" var="arr">
				nameArr.push(['${arr[0]}',${arr[1]}]);
			</c:forEach>
			console.log(nameArr);
			var data = google.visualization.arrayToDataTable(nameArr);
			var options = {
					title : '${countDate}  게시글 업데이트',
					vAxis: {title: 'Amount'},
					hAxis: {title: 'Day'}, 
					seriesType: 'bars',
				};
			
			var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
		
		function drawVisualizationM() { 
			var nameArr = [['Day', '총합', '여자', '남자', '미지정']];
			<c:forEach items="${memberArr}" var="arr">
				nameArr.push(['${arr[0]}', ${arr[1]}, ${arr[2]}, ${arr[3]}, ${arr[4]}]);
			</c:forEach>
			console.log(nameArr);
			var data = google.visualization.arrayToDataTable(nameArr);
			var options = {
					title : '${countDate}  회원가입 인원수',
					vAxis: {title: 'Amount'},
					hAxis: {title: 'Day'}, 
					seriesType: 'bars',
				};
			
			var chart = new google.visualization.ComboChart(document.getElementById('chart_div_M'));
			chart.draw(data, options);
		}
		
		function drawChart() {
			var nameArr = [['Gender', 'Num of Gender']];
			<c:forEach items="${genderArr}" var="arr">
				nameArr.push(['${arr[0]}', Number(${arr[1]})]);
			</c:forEach>
	        var data = google.visualization.arrayToDataTable(nameArr);
	        var options = {
	          title: '성별 가입자 현황(전체)'
	        };

	        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	        chart.draw(data, options);
	      }
		
		function drawChartA() {
			var nameArr = [['Age', 'Num of Age']];
			<c:forEach items="${ageArr}" var="arr">
				nameArr.push(['${arr[0]}', Number(${arr[1]})]);
			</c:forEach>
	        var data = google.visualization.arrayToDataTable(nameArr);
	        var options = {
	          title: '나이별 가입자 현황(전체)'
	        };

	        var chart = new google.visualization.PieChart(document.getElementById('piechart_A'));
	        chart.draw(data, options);
	      }
	</script>
</head>
<body>
	<jsp:include page="JSP_lib/Facebook_adminHeader.jsp"/>
    <div id="main_body">
        <div class="body_box">
        	<div class="box_dateBox">
        	<form action="admin_home.do" method="post">
        		<select name="year">
        		<c:forEach var="y" begin="2022" end="2099" step="1">
        			<option value="${y-2000 }" <c:if test="${y eq year }">selected</c:if> >${y }</option>
        		</c:forEach>
        		</select>년&nbsp
        		<select name="month">
        		<c:forEach var="m" begin="1" end="12" step="1">
        			<option value="${m }" <c:if test="${m eq month }">selected</c:if> >${m }</option>
        		</c:forEach>
        		</select>월&nbsp
        		<select name="start">
        		<c:forEach var="s" begin="1" end="31" step="1">
        			<option value="${s }" <c:if test="${s eq start }">selected</c:if> >${s }</option>
        		</c:forEach>
        		</select>일&nbsp~&nbsp
        		<select name="end">
        		<c:forEach var="e" begin="1" end="31" step="1">
        			<option value="${e }" <c:if test="${e eq end }">selected</c:if> >${e }</option>
        		</c:forEach>
        		</select>일&nbsp
        		<input type="submit" value="조회">
        	</form>
        	</div>
            <div class="chart_box" id="chart_div" style="width:1500px; height: 350px;"></div>
            <div class="chart_box" id="chart_div_M" style="width:1500px; height: 350px;"></div>
            <div class="chart_box">
	            <div id="piechart" style="width: 730px; height: 350px;"></div>
    	        <div id="piechart_A" style="width: 730px; height: 350px;"></div>
            </div>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_adminFooter.jsp" %>
</body>
</html>