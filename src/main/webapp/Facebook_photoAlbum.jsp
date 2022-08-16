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
    <link rel="stylesheet" href="CSS/PhotoAlbum.css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_header.css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_list_friend.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_myPageOptionzone.css">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="wrap">
    <jsp:include page="JSP_lib/Facebook_header.jsp"></jsp:include>
    <div id="main_body">
        <jsp:include page="JSP_lib/Facebook_list_friend.jsp"></jsp:include>
        <div id="body_content">
            <div id="content_title">
                <div id="title_name">
                    <span>사진</span>
                </div>
            </div>
            <hr>
            <form action="board_photoAlbumDelete.do" onsubmit="return deleteCheck();" method="post">
            <div id="content_optionzone">
                <div id="optionzone_box">
                    <jsp:include page="JSP_lib/Facebook_myPageOptionzone.jsp"></jsp:include>
                    <div class="box_lastOne">
                    <c:if test="${hostID eq userID }">
                        <input type="button" id="manage_button" onclick="managePhoto()" value="사진관리">
                        <input type="submit" id="delete_submit" value="삭제">
                    </c:if>
                    </div>
                </div>
            </div>
            <hr>
            <div id="content_boardzone">
                <div id="boardzone_box">
                    <div class="box_chapter">
                        <div class="chapter_date"><span>2022</span></div>
                        <div class="chapter_album">
                        	<c:set var="i" value="0"/>
                        	<c:forEach var="dto" items="${list }">
                        	<c:set var="i" value="${i+1 }"/>
                        	<div>
                            <input type="checkbox" class="photo_checkbox" name="b_no_pk_list" value="${dto.b_no_pk }">
                            <div class="album_photo" id="album_photo${i }"><img src="${dto.b_image }" alt=""></div>                      	
                            <div class="album_popup" id="album_popup${i }"><img src="${dto.b_image }" alt=""></div>                      	
                        	</div>
                        	</c:forEach>
                        	<input type="hidden" id="album_cnt" value="${i }">
                        </div>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
<script type="text/javascript" src="JS/PhotoAlbum.js"></script>
</body>
</html>