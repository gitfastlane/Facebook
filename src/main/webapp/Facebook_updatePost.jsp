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
    <link rel="stylesheet" href="CSS/UpdatePost.css" type="text/css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_header.css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_list_friend.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
</head>

<body>
<div id="wrap">
    <jsp:include page="JSP_lib/Facebook_header.jsp"></jsp:include>
    <div id="main_body">
        <jsp:include page="JSP_lib/Facebook_list_friend.jsp"></jsp:include>
        <div id="body_content">
            <div id="content_title">
                <div id="title_name">
                    <span>${m_lastName } ${m_name }</span>
                </div>
            </div>
            <div id="content_optionzone">
                <div id="optionzone_box">
                    <form action="board_updatePostOK.do" method="post" enctype="multipart/form-data">
                        <div id="box_title">
                            <span>게시물 수정</span>
                        </div>
                        <div id="box_content"><input type="hidden" name="b_no_pk" value=${dto.b_no_pk }>
                            <textarea name="b_content" id="b_content" cols="80" rows="10">${dto.b_content }</textarea>
                        </div>
                        <div id="box_select">
                        	<div>해시테그&nbsp&nbsp&nbsp<span id="tagMessage">게시물을 해시테그로 표현해주세요.</span></div>
                            <span><input type="text" id="t_hashTag" name="t_hashTag" <c:if test="${not empty tagName }">value="${tagName }"</c:if>></span>
                        </div>
                        <div id="box_bottom">
                            <div id="bottom_list">
                            	<label for="b_image"><img src="img/photoicon.png" alt=""></label>
                            	<input type="file" id="b_image" name="b_image"><input type="hidden" name="before_image" value="${dto.b_image }">
                            	
                                <a href=""><img src="img/friendicon.png" alt=""></a>
                            </div>
                            <div id="bottom_submit">
                                <input type="submit" value="수정">
                                <a href="board_deletePost.do?b_no_pk=${dto.b_no_pk }"><input type="button" value="삭제"></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <aside id="body_aside">
            <div id="aside_headline">
                <span>알 수도 있는 사람</span>
            </div>
            <c:forEach var="dto" items="${mightFriendList }">
            <a href="javascript:moveMyPage('${dto.m_id_pk }')">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="${dto.m_image }" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">${dto.m_lastName } ${dto.m_name }</span><br>
                        </div>
                        <div class="profile_button">
                        <c:if test="${hostID eq userID }">
                            <a href="member_friendBlock.do?fr_friendId=${dto.m_id_pk }"><input type="button" value="안보이기"></a>
                        </c:if>
                        </div>
                    </div>
                </div>
            </a>
            </c:forEach>
        </aside>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
<script type="text/javascript" src="JS/UpdatePost.js"></script>
</body>
</html>