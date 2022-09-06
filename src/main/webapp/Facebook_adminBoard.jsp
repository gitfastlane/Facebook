<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="CSS/AdminBoard.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminFooter.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminHeader.css">
</head>
<body>
	<jsp:include page="JSP_lib/Facebook_adminHeader.jsp"/>
	
    <div id="main_body">
    	<div class="body_box">
        	<form action="admin_manageBoard.do" method="post">
            <div class="box_dateBox">
        		<select name="year">
        		<c:forEach var="y" begin="2022" end="2099" step="1">
        			<option value="${y-2000 }" <c:if test="${y eq year }">selected</c:if> >${y }</option>
        		</c:forEach>
        		</select>년&nbsp
        		<select name="month">
        		<c:forEach var="m" begin="1" end="12" step="1">
        			<option value="${m }" <c:if test="${m eq month }">selected</c:if> >${m }</option>
        		</c:forEach>
        		</select>월&nbsp
        		<select name="start">
        		<c:forEach var="s" begin="1" end="31" step="1">
        			<option value="${s }" <c:if test="${s eq start }">selected</c:if> >${s }</option>
        		</c:forEach>
        		</select>일&nbsp~&nbsp
        		<select name="end">
        		<c:forEach var="e" begin="1" end="31" step="1">
        			<option value="${e }" <c:if test="${e eq end }">selected</c:if> >${e }</option>
        		</c:forEach>
        		</select>일&nbsp
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
                <form action="admin_deleteBoardContent.do?year=${year }&month=${month }&start=${start }&end=${end }&searchText=${searchText }&contentAmount=${contentAmount }" method="post">
                <table>
                	<caption>
                		<input type="submit" value="삭제">
                	</caption>
                    <tr><th>선택</th><th>번호</th><th>날짜</th><th>아이디</th><th>게시글</th><th>그룹 번호</th><th>종류</th><th>좋아요</th></tr>
                    <c:forEach var="dto" items="${blist }">
                    <tr><td><input type="checkbox" name="board_check" value="${dto.b_no_pk }"></td><td>${dto.b_no_pk }</td><td>${dto.b_wtime }</td><td>${dto.b_id_fk }</td>
                    	<td>${dto.b_content }</td><td>${dto.b_groupNum }</td><td>${dto.b_indentNum > 0 ? '댓글' : '게시글' }</td><td>${dto.b_like }</td></tr>
                    </c:forEach>
                </table>
                </form>
                <c:forEach var="p" begin="1" end="${pageAmount }">
                	<a href="admin_manageBoard.do?year=${year }&month=${month }&start=${start }&end=${end }&searchText=${searchText }&contentAmount=${contentAmount }&pageNum=${p }" <c:if test="${p eq pageNum }">style="color: blue; font-size: 18px" </c:if> >${p }</a>&nbsp
                </c:forEach>
            </div>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_adminFooter.jsp" %>
</body>
</html>