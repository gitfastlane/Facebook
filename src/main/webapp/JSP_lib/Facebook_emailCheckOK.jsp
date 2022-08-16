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
	<link rel="stylesheet" type="text/css" href="CSS/Facebook_emailCheckOK.css">
</head>
<body>
	<div id="header">
		<img alt="" src="../img/facebooklogo.png">	
	</div>
	<div id="main">
		<p>인증번호를 입력하세요.</p><br>
		<input type="hidden" id="emailKey" name="emailKey" value="${emailKey }">
		<input type="text" id="userEmailKey" name="userEmailKey">
		<input id="button" type="button" onclick="checkEmailKey('${email }')" value="인증"><br>
	</div>
	<script type="text/javascript" src="JS/Facebook_emailCheckOK.js"></script>
</body>
</html>