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
        <c:choose>
        <c:when test="${not empty mlist }">
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
        </c:when>
        <c:when test="${not empty blist }">
        	<c:forEach var="dto" items="${blist }">
            <div class="content_box">
                <div class="box_title">
                	<a href="javascript:moveMyPage('${dto.b_id_fk }')">
                    <div class="profile_image">
                        <img src="${memberHM[dto.b_id_fk].m_image }" alt="">
                    </div>
                	</a>
                    <div class="profile_content">
                        <span class="profile_name">${memberHM[dto.b_id_fk].m_lastName } ${memberHM[dto.b_id_fk].m_name }</span><br>
                        <span class="profile_time">작성: </span>
                        <span class="profile_time">${dto.b_wtime }</span>
                    </div>
                </div>
                <hr>
                <div class="box_board">
                  	<c:if test="${not empty dto.b_content }"><p>${dto.b_content }</p></c:if>
                	<c:if test="${dto.b_image ne null }">
                		<div class="board_img"><img src="${dto.b_image }" alt=""></div>
                	</c:if>
                	<c:if test="${not empty tagHM[ dto.b_no_pk] }"><span>${tagHM[ dto.b_no_pk] }</span></c:if>
                </div>
                <hr>
                <div class="box_comment">
                    <div class="comment_state">
                        <span>Like ${dto.b_like } </span>
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
        </c:when>
        </c:choose>
        </div>
        <aside id="body_aside">
        	<div>추천 친구</div>
        	<c:forEach var="recommand" items="${recommandList }">
            <a href="javascript:moveMyPage('${recommand.m_id_pk }')">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="${recommand.m_image }" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">${recommand.m_lastName } ${recommand.m_name }</span><br>
                        </div>
                    </div>
                </div>
            </a>
        	</c:forEach>
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