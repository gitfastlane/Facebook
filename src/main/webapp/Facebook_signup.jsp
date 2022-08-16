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
<link rel="stylesheet" type="text/css" href="CSS/Signup.css">
<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
</head>
<body>
<div id="wrap">
    <header></header>
    <div id="main_body">
    	<form name="member_signup" action="member_signupOK.do" method="post" onsubmit="return allCheck();">
        <div id="body_box">
            <div id="box_title">
                <a href="member_login.do"><img src="img/facebooklogo.png" alt=""></a>
            </div>
            <div id="box_content">
                <div id="content_name">
                    <input type="text" name="lastName" placeholder="Last Name" required>
                    <input type="text" name="name" placeholder="Name" required>
                </div>
                <div id="content_id">
                    <input type="text" name="id" placeholder="id" required readonly>
                    <input type="button" value="Check" onclick="idCheckForm()">
                </div>
                <div id="content_pw">
                    <input type="password" name="pw" id="pw" placeholder="password" required>
                </div>
                <div id="content_pwchk">
                    <input type="password" name="pwchk" id="pwchk" placeholder="confirm password" required>
                    <span id="pwchkMessage"></span>
                </div>
                <div id="content_email">
                    <input type="email" name="email" id="email" placeholder="email" required>
                    <input type="hidden" name="emailchk" id="emailchk">
                    <input type="button" value="Check" onclick="emailCheck()">
                    <span id="emailchkMessage"></span>
                </div>
                <div id="content_birth">
                    <div>생일</div>
                    <span>
                        <select name="birth_year" id="year" required>
                        	<c:forEach var="y" begin="1922" end="2022">
	                        	<option value="${y }">${y }</option>
                        	</c:forEach>
                        </select>
                    </span>
                    <span>
                        <select name="birth_month" id="month" required>
                        	<c:forEach var="m" begin="1" end="12">
                        		<option value="${m }">${m }</option>
                        	</c:forEach>
                        </select>
                    </span>
                    <span>
                        <select name="birth_day" id="day" required>
                        	<c:forEach var="d" begin="1" end="31">
                        		<option value="${d }">${d }</option>
                        	</c:forEach>
                        </select>
                    </span>
                </div>
                <div id="content_gender">
                    <div>성별</div>
                    <span>
                        <label for="female">female</label>
                        <input type="radio" name="gender" id="female" value="여자">
                    </span>
                    <span>
                        <label for="male">male</label>
                        <input type="radio" name="gender" id="male" value="남자">
                    </span>
                    <span>
                        <label for="none">none</label>
                        <input type="radio" name="gender" id="none" value="미지정" checked>
                    </span>
                </div>
            </div>
            <div id="box_footer">
                <div id="footer_submit">
                    <input value="Sign up" type="submit">
                </div>
            </div>
        </div>
    	</form>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
    <script type="text/javascript" src="JS/Signup.js"></script>
</body>
</html>