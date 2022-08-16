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
<link rel="stylesheet" type="text/css" href="CSS/FindAccount.css">
<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
</head>
<body>
<div id="wrap">
    <header></header>
    <div id="main_body">
    	<form action="member_findAccountByEmail.do" method="post">
        <div id="body_box">
            <div id="box_title">
                <a href="member_login.do"><img src="img/facebooklogo.png" alt=""></a>
            </div>
            <div id="box_content">
            	<div>
            		<p>비밀번호를 찾고자 하는 아이디를 입력하세요.</p>
            		<span id="wrongid">${wrongMessage }</span>
            	</div>
                <div id="content_id">
                    <input type="text" name="id" placeholder="id" required>
                </div>
            </div>
            <div id="box_footer">
                <div id="footer_submit">
                    <input value="Next" type="submit">
                </div>
            </div>
        </div>
    	</form>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
</body>
</html>