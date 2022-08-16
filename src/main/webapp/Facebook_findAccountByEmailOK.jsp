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
<link rel="stylesheet" type="text/css" href="CSS/FindAccountByEmailOK.css">
<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
</head>
<body>
<div id="wrap">
    <header></header>
    <div id="main_body">
    	<form action="member_findAccountCheckKey.do" method="post" onsubmit="checkKey()">
    	<input type="hidden" name="emailKey" id="emailKey" value="${emailKey }">
    	<input type="hidden" name="id" value="${id }">
        <div id="body_box">
            <div id="box_title">
                <a href="member_login.do"><img src="img/facebooklogo.png" alt=""></a>
            </div>
            <div id="box_content">
            	<div>
            		<span>${email }로 보내진 인증번호를 입력하세요.</span>
            	</div>
                <div id="content_id">
                    <input type="text" name="userEmailKey" id="userEmailKey" placeholder="인증번호" required>
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
    <script type="text/javascript" src="JS/FindAccountByEmailOK.js"></script>
</div>
</body>
</html>