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
    <link rel="stylesheet" href="CSS/WritePost.css" type="text/css">
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
                    <form action="board_writePostOK.do" onsubmit="return checkHash();" method="post" enctype="multipart/form-data">
                        <div id="box_title">
                            <span>Update Status</span>
                        </div>
                        <div id="box_content">
                            <textarea name="b_content" id="b_content" cols="80" rows="10"></textarea>
                        </div>
                        <div id="box_select">
                        	<div>해시테그&nbsp&nbsp&nbsp<span id="tagMessage">게시물을 해시테그로 표현해주세요.</span></div>
                            <span><input type="text" id="t_hashTag" name="t_hashTag"></span>
                        </div>
                        <div id="box_bottom">
                            <div id="bottom_list">
                            	<label for="b_image"><img src="img/photoicon.png" alt=""></label>
                            	<input type="file" id="b_image" name="b_image">
                            	
                                <a href=""><img src="img/friendicon.png" alt=""></a>
                            </div>
                            <div id="bottom_submit">
                                <input type="submit" value="Post">
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
<script type="text/javascript" src="JS/WritePost.js"></script>
</body>
</html>