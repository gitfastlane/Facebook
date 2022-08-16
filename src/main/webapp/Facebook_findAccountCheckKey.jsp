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
	boolean confirmation = (boolean)request.getAttribute("confirmation");
	if(confirmation){
		String id = (String)request.getAttribute("id");
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("member_findAccountChangePw.do");
		dispatcher.forward(request, response);
	}else{
		response.sendRedirect("member_findAccount.do");
	}
%>
</body>
</html>