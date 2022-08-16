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
    <header id="main_header">
        <div id="header_bar">
            <a href="home.do">
                <div id="bar_img">
                    <img src="img/facebooklogo.png" alt="">
                </div>
            </a>
            <div id="bar_search">
                <div id="search_box">
                <form action="member_searchList.do" method="post" id="searchOn">
                    <input type="text" name="searchTag" placeholder="검색 입력">
                    <button type="submit" form="searchOn"><img src="img/searchlogo.png" alt=""></button>
                </form>
                </div>
            </div>
            <div id="bar_icon">
                <a href=""><div id="icon_message"><img src="img/message.png" alt=""></div></a>
                <a href=""><div id="icon_content"><img src="img/content.png" alt=""></div></a>
            </div>
            <div id="bar_mypage">
                <a href="board_userMyPage.do">
                    <div id="mypage_img">
                        <img src="${userImage }" alt="">
                    </div>
                </a>
            </div>
        </div>
    </header>
</body>
</html>