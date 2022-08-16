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
    <link rel="stylesheet" type="text/css" href="CSS/Login.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
</head>
<body>
<div id="wrap">
    <header></header>
    <div id="main_body">
        <div id="body_box">
            <div id="box_title">
                <img src="img/facebooklogo.png" alt="">
            </div>
            <div id="box_content">
            <form action="member_loginOK.do" method="post">
                <div id="content_id">
                    <input type="text" name="id" placeholder="ID">
                </div>
                <div id="content_pw">
                    <input type="password" name="pw" placeholder="Password">
                </div>
                <div id="content_signin">
                    <input type="submit" value="Sign in">
                </div>
            </form>
                <div id="content_signup">
                    <a href="member_signup.do"><input value="Sign up" type="button"></a>
                </div>
            </div>
            <div id="box_footer">
                <a href="member_findAccount.do"><input type="button" value="Find Account"></a>
            </div>
        </div>
    </div>
    <div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
    </div>
</div>
</body>
</html>