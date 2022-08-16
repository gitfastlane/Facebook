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
    <link rel="stylesheet" type="text/css" href="CSS/FriendsList.css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_header.css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_list_friend.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_myPageOptionzone.css">
</head>
<body>
<div id="wrap">
    <jsp:include page="JSP_lib/Facebook_header.jsp"></jsp:include>
    <div id="main_body">
        <jsp:include page="JSP_lib/Facebook_list_friend.jsp"></jsp:include>
        <div id="body_content">
            <div id="content_title">
                <div id="title_name">
                    <span>친구</span>
                </div>
            </div>
            <hr>
            <div id="content_optionzone">
                <div id="optionzone_box">
                    <jsp:include page="JSP_lib/Facebook_myPageOptionzone.jsp"></jsp:include>
                </div>
            </div>
            <hr>
            <div id="content_boardzone">
            <c:if test="${hostID eq userID }">
                <div id="boardzone_ask">
                	<div class="box_title">받은 친구요청</div>
                	<hr>
                	<c:forEach var="dto" items="${askList }">
                    <div class="box_myboard">
                        <div class="myboard_title">
                            <div class="profile_image">
                                <img src="${askListHM[dto.fr_id_fk].m_image }" alt="">
                            </div>
                            <div class="profile_content">
                                <span class="profile_name">${askListHM[dto.fr_id_fk].m_lastName } ${askListHM[dto.fr_id_fk].m_name }</span><br>
                            </div>
                            <div class="profile_update">
                                <a href="member_friendConfirm.do?fr_id_fk=${dto.fr_id_fk }"><input type="button" value="수락"></a>
                                <a href="member_friendReject.do?fr_id_fk=${dto.fr_id_fk }"><input type="button" value="요청 삭제"></a>
                            </div>
                        </div>
                    </div>
                	</c:forEach>
                </div>
            </c:if>
                <div id="boardzone_box">
                	<div class="box_title">내 친구</div>
                	<hr>
                	<c:forEach var="dto" items="${friendsList }">
                    <div class="box_myboard">
                        <div class="myboard_title">
                            <div class="profile_image">
                                <img src="${friendsListHM[dto.fr_friendId].m_image }" alt="">
                            </div>
                            <div class="profile_content">
                                <span class="profile_name">${friendsListHM[dto.fr_friendId].m_lastName } ${friendsListHM[dto.fr_friendId].m_name }</span><br>
                            </div>
                            <div class="profile_update">
                            <c:if test="${hostID eq userID }">
                                <a href="member_friendDelete.do?fr_friendId=${dto.fr_friendId }"><input type="button" value="삭제"></a>
                            </c:if>
                            </div>
                        </div>
                    </div>
                	</c:forEach>
                </div>
            </div>
        </div>
        <aside id="body_aside">
            <div id="aside_headline">
                <span>알 수도 있는 사람</span>
            </div>
            <a href="">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/profile.png" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">홍길동</span><br>
                            <span class="profile_fallow">팔로워: </span>
                            <span class="profile_fallow">120</span>
                        </div>
                        <div class="profile_button">
                            <input type="button" value="안보이기">
                        </div>
                    </div>
                </div>
            </a>
            <a href="">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/kakao1.jpg" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">홍길동</span><br>
                            <span class="profile_fallow">팔로워: </span>
                            <span class="profile_fallow">120</span>
                        </div>
                        <div class="profile_button">
                            <input type="button" value="안보이기">
                        </div>
                    </div>
                </div>
            </a>
            <a href="">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/kakao1.jpg" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">홍길동</span><br>
                            <span class="profile_fallow">팔로워: </span>
                            <span class="profile_fallow">120</span>
                        </div>
                        <div class="profile_button">
                            <input type="button" value="안보이기">
                        </div>
                    </div>
                </div>
            </a>
            <a href="">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/kakao1.jpg" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">홍길동</span><br>
                            <span class="profile_fallow">팔로워: </span>
                            <span class="profile_fallow">120</span>
                        </div>
                        <div class="profile_button">
                            <input type="button" value="안보이기">
                        </div>
                    </div>
                </div>
            </a>
            <a href="">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/kakao1.jpg" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">홍길동</span><br>
                            <span class="profile_fallow">팔로워: </span>
                            <span class="profile_fallow">120</span>
                        </div>
                        <div class="profile_button">
                            <input type="button" value="안보이기">
                        </div>
                    </div>
                </div>
            </a>
        </aside>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
</body>
</html>