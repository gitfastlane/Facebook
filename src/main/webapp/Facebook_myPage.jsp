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
    <link rel="stylesheet" href="CSS/Mypage.css" type="text/css">
    <link rel="stylesheet" href="JSP_lib/CSS/Facebook_header.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_footer.css">
    <link rel="stylesheet" type="text/css" href="JSP_lib/CSS/Facebook_myPageOptionzone.css">
</head>

<body>
<div id="wrap">
    <jsp:include page="JSP_lib/Facebook_header.jsp"></jsp:include>
    <div id="main_body">
        <div id="list_friend">
            <div id="friend_myprofile">
                <div id="myprofile_img">
                    <img src="${m_image }" alt="">
                </div>
            </div>
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
            <div id="content_title">
                <div id="title_name">
                    <span>${m_lastName}${m_name }</span>
                </div>
                <div id="title_tag">
                <c:if test="${!myFriends.contains(hostID) && hostID ne userID}">
                    <input type="button" id="${hostID }" onclick="requestSend('${hostID }')" value="친구추가">
                </c:if>
                <p>
                <c:forEach var="tagName" items="${topTenTagList }">
                	<span>${tagName }</span>
                </c:forEach>
                </p>
                </div>
            </div>
            <hr>
            <div id="content_optionzone">
                <div id="optionzone_box">
                    <jsp:include page="JSP_lib/Facebook_myPageOptionzone.jsp"></jsp:include>
                    <div class="box_lastOne">
                    <c:if test="${hostID eq userID }">
                        <a href="board_writePost.do"><input type="button" value="새글작성"></a>
                    </c:if>
                    </div>
                </div>
            </div>
            <hr>
            <div id="content_boardzone">
                <div id="boardzone_box">
                	<c:forEach var="dto" items="${list }">
                    <div class="box_myboard">
                        <div class="myboard_title">
                            <div class="profile_image">
                                <img src="${memberHM[ dto.b_id_fk ].m_image }" onerror="this.src='img/kakao1.jpg'">
                            </div>
                            <div class="profile_content">
                                <span class="profile_name">${memberHM[ dto.b_id_fk ].m_lastName}${memberHM[ dto.b_id_fk ].m_name }</span><br>
                                <span class="profile_time">작성: </span>
                                <span class="profile_time">${dto.b_wtime }</span>
                            </div>
                            <div class="profile_update">
                            <c:if test="${hostID eq userID }">
                                <a href="board_updatePost.do?b_no_pk=${dto.b_no_pk }"><input type="button" value="수정"></a>
                            </c:if>
                            </div>
                        </div>
                        <hr>
                        <div class="myboard_board">
                            <c:if test="${not empty dto.b_content }"><p>${dto.b_content }</p></c:if>
                            <c:if test="${dto.b_image ne null }">
                            	<div class="board_img"><img src="${dto.b_image }" alt=""></div>
                            </c:if>
                            <c:if test="${not empty tagHM[ dto.b_no_pk] }"><span>${tagHM[ dto.b_no_pk] }</span></c:if>
                        </div>
                        <hr>
                        <div class="myboard_comment">
                            <div class="comment_state">
                                <span>Like ${dto.b_like }</span>
                            </div>
                            <div class="comment_like">
                                <button>
                                    <div><img src="img/LikeOff.png" alt=""></div>
                                    Like
                                </button>
                            </div>
                            <div class="comment_write">
                                <button>
                                    <div><img src="img/comment.png" alt=""></div>
                                    Comment
                                </button>
                            </div>
                            <div class="comment_share">
                                <button>
                                    <div><img src="img/share.png" alt=""></div>
                                    Share
                                </button>
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
<script type="text/javascript" src="JS/MyPage.js"></script>
</body>
</html>