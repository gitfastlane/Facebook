<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="CSS/Facebook_idCheckForm.css">
</head>
<body>
	<div id="header">
		<img alt="" src="../img/facebooklogo.png">	
	</div>
	<div id="main">
		<form action="member_idCheckOK.do" onsubmit="return idValueCheck();" method="post">
			<input type="text" id="idchk" name="idchk">
			<input id="submit" type="submit" value="ID Check"><br>
			<span id="idchkM"></span>
		</form>	
	</div>
	<script type="text/javascript" src="JS/Facebook_idCheckForm.js"></script>
</body>
</html>