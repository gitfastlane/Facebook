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
    <link rel="stylesheet" href="CSS/MyProfile.css">
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
                    <span>프로필</span>
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
                <div id="boardzone_box">
                <form action="member_myProfileOK.do" method="post" enctype="multipart/form-data">
                    <div class="box_lastboard">
                        <div class="myinfo_content">
                            <div>
                                <div>
                                    <img src="${dto.m_image }" onerror="this.src='img/kakao1.jpg'">
                                </div>
                                <div class="content_button">
                                	<label for="m_image">
                                		<img id="button_img_off" src="img/imageadd.png">
                                		<img id="button_img_on" src="img/imageaddend.png">
                                	</label>
                                    <input type="file" name="m_image" id="m_image" ><input type="hidden" name="before_image" value="${dto.m_image }">
                                </div>
                            </div>
                            <div>
                                <div><span class="content_span">성</span></div><input type="text" name="m_lastName" value="${dto.m_lastName }">
                            </div>
                            <div>
                                <div><span class="content_span">이름</span></div><input type="text" name="m_name" value="${dto.m_name }">
                            </div>
                            <div>
                                <div><span class="content_span">아이디</span></div><span>${userID }</span>
                            </div>
                            <div>
                                <div><span class="content_span">이메일</span></div><span>${dto.m_email }</span>
                            </div>
                            <div>
                                <div><span class="content_span">생일</span></div>
                                <span>
                        			<select name="m_birthYear" id="year" required>
                        			<c:forEach var="y" begin="1922" end="2022">
			                        	<option value="${y }" <c:if test="${dto.m_birthYear eq y }">selected</c:if> >${y }</option>                					
                        			</c:forEach>
                        			</select>
                    			</span>
                    			<span>
                        			<select name="m_birthMonth" id="month" required>
                        				<c:forEach var="m" begin="1" end="12">
                        					<option value="${m }" <c:if test="${dto.m_birthMonth eq m }">selected</c:if> >${m }</option>
                        				</c:forEach>
                        			</select>
                    			</span>
                    			<span>
                        			<select name="m_birthDay" id="day" required>
                        				<c:forEach var="d" begin="1" end="31">
                        					<option value="${d }" <c:if test="${dto.m_birthDay eq d }">selected</c:if> >${d }</option>
                        				</c:forEach>
                        			</select>
                        		</span>
                            </div>
                            <div id="content_gender">
                                <div><span class="content_span">성별</span></div>
                                <span>
                        			<label for="female">female</label>
                        			<input type="radio" name="m_gender" id="female" value="여자" <c:if test="${dto.m_gender eq '여자' }">checked</c:if> >
                    			</span>
                    			<span>
                        			<label for="male">male</label>
                        			<input type="radio" name="m_gender" id="male" value="남자" <c:if test="${dto.m_gender eq '남자' }">checked</c:if> >
                    			</span>
                    			<span>
                        			<label for="none">none</label>
                        			<input type="radio" name="m_gender" id="none" value="미지정" <c:if test="${dto.m_gender eq '미지정' }">checked</c:if> >
                    			</span>
                            </div>
                            <div>
                                <div><span class="content_span">가입일</span></div><span>${dto.m_createDate }</span>
                            </div>
                         </div> 
                    </div>
                    <div id="box_button"><input type="button" value="계정 삭제"></div>
                    <div id="box_submit"><input type="submit" value="수정"></div>
                </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
</body>
</html>