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
	String email = (String)request.getAttribute("email");
	if(email==null){
		String wrongMessage = "존재하지 않는 아이디입니다.";
		request.setAttribute("wrongMessage", wrongMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("member_findAccount.do");
		dispatcher.forward(request, response);		
	}else{
		String id = (String)request.getAttribute("id");
		int emailKey = (int)request.getAttribute("emailKey");
		request.setAttribute("id", id);
		request.setAttribute("email", email);
		request.setAttribute("emailKey", emailKey);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member_findAccountByEmailOK.do");
		dispatcher.forward(request, response);		
	}
%>
</body>
</html>