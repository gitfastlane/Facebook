<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="CSS/AdminMemberOne.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminFooter.css">
	<link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_adminHeader.css">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<jsp:include page="JSP_lib/Facebook_adminHeader.jsp"/>
	
    <div id="main_body">
    	<div class="body_box">
        	<form action="admin_memberOne.do?m_id_pk=${mdto.m_id_pk }" method="post">
        	<div class="box_profile">
        		<table>
        			<tr><td style="font-weight: bold">아이디</td><td>${mdto.m_id_pk }</td><td style="font-weight: bold">Last Name</td><td>${mdto.m_lastName }</td><td style="font-weight: bold">Name</td><td>${mdto.m_name }</td><td rowspan="3"><img src="${mdto.m_image }"></td></tr>
        			<tr><td style="font-weight: bold">이메일</td><td>${mdto.m_email }</td><td style="font-weight: bold">생년월일</td><td>${mdto.m_birthYear }-${mdto.m_birthMonth }-${mdto.m_birthDay }</td><td style="font-weight: bold">Gender</td><td>${mdto.m_gender }</td></tr>
        			<tr><td style="font-weight: bold">생성일</td><td colspan="5">${mdto.m_createDate }</td></tr>
        		</table>
        	</div>
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
            <form action="admin_deleteMemberOne.do?m_id_pk=${mdto.m_id_pk }&searchText=${searchText }&contentAmount=${contentAmount }" method="post">
            <div class="box_content">
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
                <c:forEach var="p" begin="1" end="${pageAmount }">
                	<a href="admin_memberOne.do?m_id_pk=${mdto.m_id_pk }&searchText=${searchText }&contentAmount=${contentAmount }&pageNum=${p }" <c:if test="${p eq pageNum }">style="color: blue; font-size: 18px" </c:if> >${p }</a>&nbsp
                </c:forEach>
            </div>
            <div class="box_content">
                    <div class="middle_box">
                    <c:set var="i" value="0"/>
                    <c:forEach var="dto" items="${blist }">
                    <c:set var="i" value="${i+1 }"/>
                    <c:if test="${not empty dto.b_image }">
                    	<div class="content_box">
	                    	<input type="checkbox" name="board_check" value="${dto.b_no_pk }">
    	                	<div class="album_photo" id="album_photo${i }">
    	                		<img src="${dto.b_image }" alt="">
    	                		<p>번호: ${dto.b_no_pk }</p>
    	                	</div>                      	
                            <div class="album_popup" id="album_popup${i }"><img src="${dto.b_image }" alt=""></div>    
                    	</div>
                    </c:if>
                    </c:forEach>
                    <input type="hidden" id="album_cnt" value="${i }">
                    </div>
            </div>
            </form>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_adminFooter.jsp" %>
<script type="text/javascript" src="JS/AdminPhoto.js"></script>
</body>
</html>