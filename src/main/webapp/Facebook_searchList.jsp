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
    <link rel="stylesheet" href="CSS/SearchList.css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_header.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
</head>
<body>
<div id="wrap">
    <jsp:include page="JSP_lib/Facebook_header.jsp"></jsp:include>
    <div id="main_body">
        <div id="list_friend">
            <ul>
            <c:forEach var="dto" items="${friendsList_sub }">
                <li><a href="javascript:moveMyPage('${dto.fr_friendId }')">
                        <div class="friend_profile">
                            <div class="profile_image">
                                <img src="${friendsListHM_sub[dto.fr_friendId].m_image }" alt="">
                            </div>
                            <div class="profile_content">
                                <span class="profile_name">${friendsListHM_sub[dto.fr_friendId].m_lastName } ${friendsListHM_sub[dto.fr_friendId].m_name }</span><br>
                                <span class="profile_time">Online: </span>
                                <span class="profile_time">2일전</span>
                            </div>
                        </div>
                    </a></li>
            </c:forEach>
            </ul>
        </div>
        <div id="body_content">
        	<c:forEach var="dto" items="${mlist }">
            <div class="content_box">
                <div class="box_title">
                    <div class="profile_image">
                        <a href="javascript:moveMyPage('${dto.m_id_pk }')"><img src="${dto.m_image }" alt=""></a>
                    </div>
                    <div class="profile_content">
                        <span class="profile_name">${dto.m_lastName } ${dto.m_name }</span><br>
                    </div>
                    <c:if test="${!flist.contains(dto.m_id_pk) && dto.m_id_pk!=userID}">
                    <div class="profile_button">
                   	<input type="button" id="${dto.m_id_pk }" onclick="requestSend('${dto.m_id_pk }')" value="친구추가">
                    </div>
                    </c:if>
                </div>
            </div>
        	</c:forEach>
        </div>
        <aside id="body_aside">
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
                    </div>
                    <div class="block_content">
                        <hr>
                        <div class="box_board">
                            <div class="board_img">
                                <img src="img/cycle.PNG" alt="">
                            </div>
                            <div class="board_img">
                                <img src="img/drive.PNG" alt="">
                            </div>
                            <div class="board_img">
                                <img src="img/plane.PNG" alt="">
                            </div>
                        </div>
                        <hr>
                        <div class="box_comment">
                            <div class="comment_state">
                                <span>Like 5</span>
                                <span>조회수 10</span>
                            </div>
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
                    </div>
                    <div class="block_content">
                        <hr>
                        <div class="box_board">
                            <div class="board_img">
                                <img src="img/phote.PNG" alt="">
                            </div>
                            <div class="board_img">
                                <img src="img/plane.PNG" alt="">
                            </div>
                        </div>
                        <hr>
                        <div class="box_comment">
                            <div class="comment_state">
                                <span>Like 5</span>
                                <span>조회수 10</span>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
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
                    </div>
                    <div class="block_content">
                        <hr>
                        <div class="box_board">
                            <div class="board_img">
                                <img src="img/cycle.PNG" alt="">
                            </div>
                            <div class="board_img">
                                <img src="img/drive.PNG" alt="">
                            </div>
                            <div class="board_img">
                                <img src="img/plane.PNG" alt="">
                            </div>
                        </div>
                        <hr>
                        <div class="box_comment">
                            <div class="comment_state">
                                <span>Like 5</span>
                                <span>조회수 10</span>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </aside>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
<script type="text/javascript">
	function moveMyPage(m_id_pk){
		let page = document.createElement('form');
	
		let input;
		input = document.createElement('input');
		input.setAttribute('type', 'hidden');
		input.setAttribute('value', m_id_pk);
	
		page.appendChild(input);
		page.setAttribute('method', 'post');
		input.setAttribute('name', 'hostID');
		page.setAttribute('action', 'board_myPage.do');
		document.body.appendChild(page);
		page.submit();
	}
</script>
<script type="text/javascript" src="JS/SearchList.js"></script>
</body>
</html>