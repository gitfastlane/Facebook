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
</head>
<body>
<%
	String adminID = (String)session.getAttribute("adminID");
	String userID = (String)session.getAttribute("userID");
	if(adminID!=null){
		response.sendRedirect("admin_home.do");		
	}else if(userID!=null){
		response.sendRedirect("home.do");
	}else{
		response.sendRedirect("member_login.do");
	}
%>
</body>
</html>