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
		<c:choose>
			<c:when test="${result eq true }">
				<span>${newId }는 사용가능한 아이디 입니다.</span><br>
				<button onclick="useID('${newId}')">사용하기</button>
			</c:when>
			<c:otherwise>
				<span>${newId }는 사용중인 아이디 입니다.</span>
				
			</c:otherwise>
		</c:choose>
		<br><a href="javascript:history.back()"><button id="restart">다시시도</button></a>&nbsp
		<a href="javascript:window.close()"><button id="closeAll">끝내기</button></a>
	</div>
	<script type="text/javascript" src="JS/Facebook_idCheckFormOK.js"></script>
</body>
</html>