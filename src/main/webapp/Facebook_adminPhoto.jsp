<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="CSS/AdminPhoto.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminFooter.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminHeader.css">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<jsp:include page="JSP_lib/Facebook_adminHeader.jsp"/>
    <div id="main_body">
    	<div class="body_box">
        	<form action="admin_managePhoto.do" method="post">
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
        		<input type="submit" value="조회">
        	</div>            	
            <select name="contentAmount">
            	<option value="15" <c:if test="${contentAmount eq 15 }">selected</c:if> >15개</option>
            	<option value="30" <c:if test="${contentAmount eq 30 }">selected</c:if> >30개</option>
            </select>
            <span>검색 결과: ${totalCount } 개</span>
        	</form>
            <div class="box_content">
                <form action="admin_deletePhotoContent.do?year=${year }&month=${month }&start=${start }&end=${end }&searchText=${searchText }&contentAmount=${contentAmount }" method="post">
                    <div class="middle_box">
                    	<input class="submit_btn" type="submit" value="삭제">
                    </div>
                    <div class="middle_box">
                    <c:set var="i" value="0"/>
                    <c:forEach var="dto" items="${blist }">
                    <c:set var="i" value="${i+1 }"/>
                    	<div class="content_box">
	                    	<input type="checkbox" name="board_check" value="${dto.b_no_pk }">
    	                	<div class="album_photo" id="album_photo${i }">
    	                		<img src="${dto.b_image }" alt="">
    	                		<p>ID: ${dto.b_id_fk }</p>
    	                	</div>                      	
                            <div class="album_popup" id="album_popup${i }"><img src="${dto.b_image }" alt=""></div>    
                    	</div>
                    </c:forEach>
                    <input type="hidden" id="album_cnt" value="${i }">
                    </div>
                </form>
                    <div class="middle_box">
                	<c:forEach var="p" begin="1" end="${pageAmount }">
                		<a href="admin_managePhoto.do?year=${year }&month=${month }&start=${start }&end=${end }&searchText=${searchText }&contentAmount=${contentAmount }&pageNum=${p }" <c:if test="${p eq pageNum }">style="color: blue; font-size: 18px" </c:if> >${p }</a>&nbsp
                	</c:forEach>
                    </div>
            </div>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_adminFooter.jsp" %>
<script type="text/javascript" src="JS/AdminPhoto.js"></script>
</body>
</html>