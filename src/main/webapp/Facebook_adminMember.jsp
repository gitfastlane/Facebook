<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="CSS/AdminMember.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminFooter.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminHeader.css">
</head>
<body>
	<jsp:include page="JSP_lib/Facebook_adminHeader.jsp"/>
	
    <div id="main_body">
    	<div class="body_box">
        	<form action="admin_manageMember.do" method="post">
            <div class="box_dateBox">
                <input type="text" name="searchText" placeholder="검색 입력" value="${searchText }">
        		<input type="submit" value="조회">
        	</div>
            	<select name="contentAmount">
            		<option value="15" <c:if test="${contentAmount eq 15 }">selected</c:if> >15개</option>
            		<option value="30" <c:if test="${contentAmount eq 30 }">selected</c:if> >30개</option>
            	</select>
            	<span>검색 결과: ${totalCount } 개</span>
        	</form>
            <div class="box_content">
                <table>
                    <tr><th>아이디</th><th>Last Name</th><th>Name</th><th>이메일</th><th>생년월일</th><th>Gender</th><th>생성일</th></tr>
                    <c:forEach var="dto" items="${mlist }">
                    <tr><td><a href="admin_memberOne.do?m_id_pk=${dto.m_id_pk }">${dto.m_id_pk }</a></td><td>${dto.m_lastName }</td><td>${dto.m_name }</td>
                    	<td>${dto.m_email }</td><td>${dto.m_birthYear }-${dto.m_birthMonth }-${dto.m_birthDay }</td><td>${dto.m_gender }</td><td>${dto.m_createDate }</td></tr>
                    </c:forEach>
                </table>
                <c:forEach var="p" begin="1" end="${pageAmount }">
                	<a href="admin_manageMember.do?searchText=${searchText }&contentAmount=${contentAmount }&pageNum=${p }" <c:if test="${p eq pageNum }">style="color: blue; font-size: 18px" </c:if> >${p }</a>&nbsp
                </c:forEach>
            </div>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_adminFooter.jsp" %>
</body>
</html>