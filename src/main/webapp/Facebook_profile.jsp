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
    <link rel="stylesheet" href="CSS/Profile.css">
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
                    <div class="box_lastOne">
                    <c:if test="${hostID eq userID }">
                        <a href="member_myProfile.do"><input type="button" value="프로필관리"></a>
                    </c:if>
                    </div>
                </div>
            </div>
            <hr>
            <div id="content_boardzone">
                <div id="boardzone_box">
                    <div class="box_myboard">
                        <div class="myboard_myinfo">
                            <div class="myinfo_image">
                                <img src="${mdto.m_image }" onerror="this.src='img/kakao1.jpg'">
                            </div>
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>${mdto.m_lastName } ${mdto.m_name }</span>
                                </div>
                                <div class="content_lastinfo">
                                    <div><div><span class="content_span">이메일</span></div><span>${mdto.m_email }</span></div>
                                    <div><div><span class="content_span">생일</span></div><span>${mdto.m_birthYear }-${mdto.m_birthMonth }-${mdto.m_birthDay }</span></div>
                                    <div><div><span class="content_span">성별</span></div><span>${mdto.m_gender }</span></div>
                                </div>
                            </div> 
                        </div>
                    </div>
 <!-- ----------------로드 시 출력 내용--------------------------- -->
 					<form action="member_profileUpdate.do" id="form_id" method="post">
                    <c:if test="${not empty pdto.p_locationCountry }">
                    <div class="box_lastboard">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>지역</span>
                                    <div class="myinfo_update">
                                    <c:if test="${hostID eq userID }">
                                    	<input type="button" onclick="deleteLocation()" value="삭제">&nbsp
                                    	<input type="button" onclick="updateLocation()" value="수정">
                                    </c:if>
                                    </div>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span">나라</span></div>
                                        	<span class="up_location">${pdto.p_locationCountry }</span>
                                        	<input type="text" class="up_location_input" name="p_locationCountry" value="${pdto.p_locationCountry }">
                                    </div>
                                    <div>
                                        <div><span class="content_span">도시</span></div>
                                        	<span class="up_location">${pdto.p_locationCity }</span>
                                        	<input type="text" class="up_location_input" name="p_locationCity" value="${pdto.p_locationCity }">
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${not empty pdto.p_school }">
                    <div class="box_lastboard">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>학교</span>
                                    <div class="myinfo_update">
                                    <c:if test="${hostID eq userID }">
                                    	<input type="button" onclick="deleteSchool()" value="삭제">&nbsp
                                        <input type="button" onclick="updateSchool()" value="수정">
                                    </c:if>
                                    </div>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span up_school">${pdto.p_school }</span>
                                        	<select name="p_school" class="up_school_input">
                                        	<option value=""></option>
                                        	<option value="대학원" <c:if test="${not empty pdto.p_school && pdto.p_school eq '대학원' }"> selected </c:if> >대학원</option>
                                        	<option value="대학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '대학교' }"> selected </c:if> >대학교</option>
                                        	<option value="고등학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '고등학교' }"> selected </c:if> >고등학교</option>
                                        	<option value="중학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '중학교' }"> selected </c:if> >중학교</option>
                                        	<option value="초등학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '초등학교' }"> selected </c:if> >초등학교</option>
                                        </select>
                                        </div>
                                        	<span class="up_school">${pdto.p_schoolName }</span>
                                        	<input type="text" class="up_school_input" name="p_schoolName" value="${pdto.p_schoolName }">
                                    </div>
                                    <div>
                                        <div><span class="content_span">기간</span></div>
                                        	<span class="up_school">${pdto.p_schoolYear1 }년 ${pdto.p_schoolMonth1 }월 ${pdto.p_schoolDay1 }일 ~ 
                                        	${not empty pdto.p_schoolYear2 ? pdto.p_schoolYear2+="년" : ""} 
                                        	${not empty pdto.p_schoolMonth2 ? pdto.p_schoolMonth2+="월" : ""} 
                                        	${not empty pdto.p_schoolDay2 ? pdto.p_schoolDay2+="일" : ""}</span>
                                        	<select class="up_school_input" name="p_schoolYear1"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_schoolYear1 && pdto.p_schoolYear1 eq y }"> selected </c:if>>${y }</option></c:forEach></select><span class="up_school_input">년</span>
                                        	<select class="up_school_input" name="p_schoolMonth1"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_schoolMonth1 && pdto.p_schoolMonth1 eq m }"> selected </c:if>>${m }</option></c:forEach></select><span class="up_school_input">월</span>
                                        	<select class="up_school_input" name="p_schoolDay1"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_schoolDay1 && pdto.p_schoolDay1 eq d }"> selected </c:if>>${d }</option></c:forEach></select><span class="up_school_input">일&nbsp~&nbsp</span>
                                        	<select class="up_school_input" name="p_schoolYear2"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_schoolYear2 && pdto.p_schoolYear2 eq y }"> selected </c:if>>${y }</option></c:forEach></select><span class="up_school_input">년</span>
                                        	<select class="up_school_input" name="p_schoolMonth2"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_schoolMonth2 && pdto.p_schoolMonth2 eq m }"> selected </c:if>>${m }</option></c:forEach></select><span class="up_school_input">월</span>
                                        	<select class="up_school_input" name="p_schoolDay2"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_schoolDay2 && pdto.p_schoolDay2 eq d }"> selected </c:if>>${d }</option></c:forEach></select><span class="up_school_input">일</span>
                                    </div>
                                    <div>
                                        <div><span class="content_span">위치</span></div>
                                        	<span class="up_school">${pdto.p_schoolLocation }</span>
                                        	<input type="text" class="up_school_input" name="p_schoolLocation" value="${pdto.p_schoolLocation }">
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${not empty pdto.p_workName }">
                    <div class="box_lastboard">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>직업</span>
                                    <div class="myinfo_update">
                                    <c:if test="${hostID eq userID }">
                                    	<input type="button" onclick="deleteWork()" value="삭제">&nbsp
                                        <input type="button" onclick="updateWork()" value="수정">
                                    </c:if>
                                    </div>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span">직업</span></div>
                                        <span class="up_work">${pdto.p_workName }</span>
                                        <input type="text" class="up_work_input" name="p_workName" value="${pdto.p_workName }">
                                    </div>
                                    <div>
                                        <div><span class="content_span">회사</span></div>
                                        <span class="up_work">${pdto.p_workPlace }</span>
                                        <input type="text" class="up_work_input" name="p_workPlace" value="${pdto.p_workPlace }">
                                    </div>
                                    <div>
                                        <div><span class="content_span">기간</span></div>
                                        <span class="up_work">${pdto.p_workYear1 }년 ${pdto.p_workMonth1 }월 ${pdto.p_workDay1 }일 ~ 
                                        ${not empty pdto.p_workYear2 ? pdto.p_workYear2+="년" : ""} 
                                        ${not empty pdto.p_workMonth2 ? pdto.p_workMonth2+="월" : ""} 
                                        ${not empty pdto.p_workDay2 ? pdto.p_workDay2+="일" : ""}</span>
                                        <select name="p_workYear1" class="up_work_input"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_workYear1 && pdto.p_workYear1 eq y }"> selected </c:if>>${y }</option></c:forEach></select><span class="up_work_input">년</span>
                                        <select name="p_workMonth1" class="up_work_input"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_workMonth1 && pdto.p_workMonth1 eq m }"> selected </c:if>>${m }</option></c:forEach></select><span class="up_work_input">월</span>
                                        <select name="p_workDay1" class="up_work_input"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_workDay1 && pdto.p_workDay1 eq d }"> selected </c:if>>${d }</option></c:forEach></select><span class="up_work_input">일&nbsp~&nbsp</span>
                                        <select name="p_workYear2" class="up_work_input"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_workYear2 && pdto.p_workYear2 eq y }"> selected </c:if>>${y }</option></c:forEach></select><span class="up_work_input">년</span>
                                        <select name="p_workMonth2" class="up_work_input"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_workMonth2 && pdto.p_workMonth2 eq m }"> selected </c:if>>${m }</option></c:forEach></select><span class="up_work_input">월</span>
                                        <select name="p_workDay2" class="up_work_input"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_workDay2 && pdto.p_workDay2 eq d }"> selected </c:if>>${d }</option></c:forEach></select><span class="up_work_input">일</span>
                                    </div>
                                    <div>
                                        <div><span class="content_span">위치</span></div>
                                        <span class="up_work">${pdto.p_workLocation }</span>
                                        <input type="text" class="up_work_input" name="p_workLocation" value="${pdto.p_workLocation }">
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${not empty pdto.p_relation1 }">
                    <div class="box_lastboard">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>관계</span>
                                    <div class="myinfo_update">
                                    <c:if test="${hostID eq userID }">
                                    	<input type="button" onclick="deleteRelation()" value="삭제">&nbsp
                                        <input type="button" onclick="updateRelation()" value="수정">
                                    </c:if>
                                    </div>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span"><span class="up_relation">${pdto.p_relation1 }</span>
                                        <select name="p_relation1" class="up_relation_input">
                                        	<option value=""></option>
                                        	<option value="어머니" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '어머니' }"> selected </c:if>>어머니</option>
                                        	<option value="아버지" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '아버지' }"> selected </c:if>>아버지</option>
                                        	<option value="형제" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '형제' }"> selected </c:if>>형제</option>
                                        	<option value="자매" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '자매' }"> selected </c:if>>자매</option>
                                        	<option value="애인" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '애인' }"> selected </c:if>>애인</option>
                                        </select>
                                        </span></div>
                                        <span class="up_relation">${pdto.p_relationName1 }</span>
                                        <input type="text" class="up_relation_input" name="p_relationName1" value="${pdto.p_relationName1 }">
                                    </div>
                                    <c:if test="${not empty pdto.p_relation2 }">
                                    <div>
                                        <div><span class="content_span"><span class="up_relation">${pdto.p_relation2 }</span>
                                        <select name="p_relation2" class="up_relation_input">
                                        	<option value=""></option>
                                        	<option value="어머니" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '어머니' }"> selected </c:if>>어머니</option>
                                        	<option value="아버지" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '아버지' }"> selected </c:if>>아버지</option>
                                        	<option value="형제" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '형제' }"> selected </c:if>>형제</option>
                                        	<option value="자매" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '자매' }"> selected </c:if>>자매</option>
                                        	<option value="애인" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '애인' }"> selected </c:if>>애인</option>
                                        </select>
                                        </span></div>
                                        <span class="up_relation">${pdto.p_relationName2 }</span>
                                        <input type="text" class="up_relation_input" name="p_relationName2" value="${pdto.p_relationName2 }">
                                    </div>
                                    </c:if>
                                    <c:if test="${not empty pdto.p_relation3 }">
                                    <div>
                                        <div><span class="content_span"><span class="up_relation">${pdto.p_relation3 }</span>
                                        <select name="p_relation3" class="up_relation_input">
                                        	<option value=""></option>
                                        	<option value="어머니" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '어머니' }"> selected </c:if>>어머니</option>
                                        	<option value="아버지" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '아버지' }"> selected </c:if>>아버지</option>
                                        	<option value="형제" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '형제' }"> selected </c:if>>형제</option>
                                        	<option value="자매" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '자매' }"> selected </c:if>>자매</option>
                                        	<option value="애인" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '애인' }"> selected </c:if>>애인</option>
                                        </select>
                                        </span></div>
                                        <span>${pdto.p_relationName3 }</span>
                                        <input type="text" class="up_relation_input" name="p_relationName3" value="${pdto.p_relationName3 }">
                                    </div>
                                    </c:if>
                                </div>
                            </div> 
                        </div>
                    </div>
                    </c:if>
                    <div id="up_save">
	                    <input type="submit" value="저장">
                    </div>
 					</form>
                    
<!-- --------------------<항목 추가>버튼 클릭시 동작 내용----------------------- -->
                    <form action="member_profileUpdate.do" method="post">
                    <div class="box_lastboard" id="add_location">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>지역</span>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span">나라</span></div>
                                        <input type="text" name="p_locationCountry" <c:if test="${not empty pdto.p_locationCountry }"> value="${pdto.p_locationCountry }" </c:if> >
                                    </div>
                                    <div>
                                        <div><span class="content_span">도시</span></div>
                                        <input type="text" name="p_locationCity" <c:if test="${not empty pdto.p_locationCity }"> value="${pdto.p_locationCity }" </c:if> >
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="box_lastboard" id="add_school">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>학교</span>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span"><select name="p_school">
                                        	<option value=""></option>
                                        	<option value="대학원" <c:if test="${not empty pdto.p_school && pdto.p_school eq '대학원' }"> selected </c:if> >대학원</option>
                                        	<option value="대학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '대학교' }"> selected </c:if> >대학교</option>
                                        	<option value="고등학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '고등학교' }"> selected </c:if> >고등학교</option>
                                        	<option value="중학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '중학교' }"> selected </c:if> >중학교</option>
                                        	<option value="초등학교" <c:if test="${not empty pdto.p_school && pdto.p_school eq '초등학교' }"> selected </c:if> >초등학교</option>
                                        </select></span></div><input type="text" name="p_schoolName" <c:if test="${not empty pdto.p_schoolName }"> value="${pdto.p_schoolName }" </c:if>>
                                    </div>
                                    <div>
                                        <div><span class="content_span">기간</span></div>
                                        <select name="p_schoolYear1"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_schoolYear1 && pdto.p_schoolYear1 eq y }"> selected </c:if>>${y }</option></c:forEach></select>년
                                        <select name="p_schoolMonth1"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_schoolMonth1 && pdto.p_schoolMonth1 eq m }"> selected </c:if>>${m }</option></c:forEach></select>월
                                        <select name="p_schoolDay1"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_schoolDay1 && pdto.p_schoolDay1 eq d }"> selected </c:if>>${d }</option></c:forEach></select>일&nbsp~&nbsp
                                        <select name="p_schoolYear2"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_schoolYear2 && pdto.p_schoolYear2 eq y }"> selected </c:if>>${y }</option></c:forEach></select>년
                                        <select name="p_schoolMonth2"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_schoolMonth2 && pdto.p_schoolMonth2 eq m }"> selected </c:if>>${m }</option></c:forEach></select>월
                                        <select name="p_schoolDay2"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_schoolDay2 && pdto.p_schoolDay2 eq d }"> selected </c:if>>${d }</option></c:forEach></select>일
                                    </div>
                                    <div>
                                        <div><span class="content_span">위치</span></div><input type="text" name="p_schoolLocation" <c:if test="${not empty pdto.p_schoolLocation }"> value="${pdto.p_schoolLocation }" </c:if>>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="box_lastboard" id="add_work">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>직업</span>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span">직업</span></div><input type="text" name="p_workName" <c:if test="${not empty pdto.p_workName }"> value="${pdto.p_workName }" </c:if>>
                                    </div>
                                    <div>
                                        <div><span class="content_span">회사</span></div><input type="text" name="p_workPlace" <c:if test="${not empty pdto.p_workPlace }"> value="${pdto.p_workPlace }" </c:if>>
                                    </div>
                                    <div>
                                        <div><span class="content_span">기간</span></div>
                                        <select name="p_workYear1"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_workYear1 && pdto.p_workYear1 eq y }"> selected </c:if>>${y }</option></c:forEach></select>년
                                        <select name="p_workMonth1"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_workMonth1 && pdto.p_workMonth1 eq m }"> selected </c:if>>${m }</option></c:forEach></select>월
                                        <select name="p_workDay1"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_workDay1 && pdto.p_workDay1 eq d }"> selected </c:if>>${d }</option></c:forEach></select>일&nbsp~&nbsp
                                        <select name="p_workYear2"><option value="" selected></option>
                                        	<c:forEach var="y" begin="1922" end="2022">
                                        		<option value="${y }" <c:if test="${not empty pdto.p_workYear2 && pdto.p_workYear2 eq y }"> selected </c:if>>${y }</option></c:forEach></select>년
                                        <select name="p_workMonth2"><option value="" selected></option>
                                        	<c:forEach var="m" begin="1" end="12">
                                        		<option value="${m }" <c:if test="${not empty pdto.p_workMonth2 && pdto.p_workMonth2 eq m }"> selected </c:if>>${m }</option></c:forEach></select>월
                                        <select name="p_workDay2"><option value="" selected></option>
                                        	<c:forEach var="d" begin="1" end="31">
                                        		<option value="${d }" <c:if test="${not empty pdto.p_workDay2 && pdto.p_workDay2 eq d }"> selected </c:if>>${d }</option></c:forEach></select>일
                                    </div>
                                    <div>
                                        <div><span class="content_span">위치</span></div><input type="text" name="p_workLocation" <c:if test="${not empty pdto.p_workLocation }"> value="${pdto.p_workLocation }" </c:if>>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="box_lastboard" id="add_relation">
                        <div class="myboard_myinfo">
                            <div class="myinfo_content">
                                <div class="content_name">
                                    <span>관계</span>
                                </div>
                                <div class="content_lastinfo">
                                    <div>
                                        <div><span class="content_span"><select name="p_relation1">
                                        	<option value=""></option>
                                        	<option value="어머니" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '어머니' }"> selected </c:if>>어머니</option>
                                        	<option value="아버지" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '아버지' }"> selected </c:if>>아버지</option>
                                        	<option value="형제" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '형제' }"> selected </c:if>>형제</option>
                                        	<option value="자매" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '자매' }"> selected </c:if>>자매</option>
                                        	<option value="애인" <c:if test="${not empty pdto.p_relation1 && pdto.p_relation1 eq '애인' }"> selected </c:if>>애인</option>
                                        </select></span></div><input type="text" name="p_relationName1" <c:if test="${not empty pdto.p_relationName1 }"> value="${pdto.p_relationName1 }" </c:if>>
                                        <input type="button" onclick="openRelation2()" value="추가">
                                    </div>
                                    <div id="relation2">
                                        <div><span class="content_span"><select name="p_relation2">
                                        	<option value=""></option>
                                        	<option value="어머니" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '어머니' }"> selected </c:if>>어머니</option>
                                        	<option value="아버지" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '아버지' }"> selected </c:if>>아버지</option>
                                        	<option value="형제" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '형제' }"> selected </c:if>>형제</option>
                                        	<option value="자매" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '자매' }"> selected </c:if>>자매</option>
                                        	<option value="애인" <c:if test="${not empty pdto.p_relation2 && pdto.p_relation2 eq '애인' }"> selected </c:if>>애인</option>
                                        </select></span></div><input type="text" name="p_relationName2" <c:if test="${not empty pdto.p_relationName2 }"> value="${pdto.p_relationName2 }" </c:if>>
                                        <input type="button" onclick="openRelation3()" value="추가">
                                    </div>
                                    <div id="relation3">
                                        <div><span class="content_span"><select name="p_relation3">
                                        	<option value=""></option>
                                        	<option value="어머니" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '어머니' }"> selected </c:if>>어머니</option>
                                        	<option value="아버지" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '아버지' }"> selected </c:if>>아버지</option>
                                        	<option value="형제" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '형제' }"> selected </c:if>>형제</option>
                                        	<option value="자매" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '자매' }"> selected </c:if>>자매</option>
                                        	<option value="애인" <c:if test="${not empty pdto.p_relation3 && pdto.p_relation3 eq '애인' }"> selected </c:if>>애인</option>
                                        </select></span></div><input type="text" name="p_relationName3" <c:if test="${not empty pdto.p_relationName3 }"> value="${pdto.p_relationName3 }" </c:if>>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div id="add_save">
	                    <input type="submit" value="저장">
                    </div>
                    </form>
<!-- ------------------------------------------------------- -->
                </div>
            </div>
        </div>
        <aside id="body_aside">
            <div id="aside_headline">
                <span>추가 항목</span>
            </div>
            <c:if test="${empty pdto.p_school }">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/school.png" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">학교</span><br>
                        </div>
                        <div class="profile_button">
                        <c:if test="${hostID eq userID }">
                            <input type="button" onclick="schoolAdd()" value="추가">
                        </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty pdto.p_locationCountry }">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/location.jpg" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">지역</span><br>
                        </div>
                        <div class="profile_button">
                        <c:if test="${hostID eq userID }">
                            <input type="button" onclick="locationAdd()" value="추가">
                        </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty pdto.p_workName }">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/work.png" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">직업</span><br>
                        </div>
                        <div class="profile_button">
                        <c:if test="${hostID eq userID }">
                            <input type="button" onclick="workAdd()" value="추가">
                        </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty pdto.p_relation1 }">
                <div class="aside_block">
                    <div class="block_title">
                        <div class="profile_image">
                            <img src="img/kakao1.jpg" alt="">
                        </div>
                        <div class="profile_content">
                            <span class="profile_name">관계</span><br>
                        </div>
                        <div class="profile_button">
                        <c:if test="${hostID eq userID }">
                            <input type="button" onclick="relationAdd()" value="추가">
                        </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </aside>
    </div>
    <%@ include file="JSP_lib/Facebook_footer.jsp" %>
</div>
<script type="text/javascript" src="JS/Profile.js"></script>
</body>
</html>