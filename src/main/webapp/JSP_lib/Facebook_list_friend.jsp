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
</head>
<body>
        <div id="list_friend">
            <div id="friend_myprofile">
            <a href="javascript:moveMyPage('${hostID }')">
                <div id="myprofile_img">
                    <img src="${m_image }" alt="">
                </div>
                <div id="myprofile_name">
                    <span>${m_lastName } ${m_name }</span><br>
                    <span id="name_returntag">페이지로 돌아가기</span>
                </div>
            </a>
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
                            </div>
                        </div>
                </a></li>
            </c:forEach>
            </ul>
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
</body>
</html>