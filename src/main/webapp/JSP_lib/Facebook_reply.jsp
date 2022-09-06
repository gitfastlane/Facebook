<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<!-- -----------댓글 상자------------ -->
                <div class="box_write" id="writeBox${dto.b_no_pk }">
                <!-- -----------댓글 입력------------ -->
                    <div class="write_textarea">
                    <form action="board_replySend.do" method="post" onsubmit="return sendReply(${dto.b_no_pk });">
                    	<input type="hidden" name="b_groupNum" value="${dto.b_no_pk }">
                    	<input type="hidden" name="b_stepNum">
                    	<input type="hidden" name="b_indentNum" value="1">
                    	<input type="hidden" name="b_replyId">
                        <div class="textarea_image">
                            <img src="${userImage }" alt="">
                        </div>
                        <div class="textarea_textbox">
                            <textarea name="b_content" id="content${dto.b_no_pk }" onkeydown="autoHeight(this)" onkeyup="autoHeight(this)" placeholder="댓글 추가..."></textarea>
                        </div>
                        <div class="textarea_submit"><input type="submit" value="댓글"></div>
                    </form>
                    </div>
                <!-- -----------댓글 입력 end------------ -->
                <!-- -----------댓글 내용------------ -->
                	<c:forEach var="rdto" items="${replyHM[dto.b_no_pk] }">
                	<c:if test="${rdto.b_indentNum eq 1 }">
                    <div class="write_replyarea">
                        <div class="replyarea_image">
                            <img src="${replyMemberHM[rdto.b_id_fk].m_image }" alt="">
                        </div>
                        <div class="replyarea_textbox">
                            <span>${replyMemberHM[rdto.b_id_fk].m_lastName } ${replyMemberHM[rdto.b_id_fk].m_name }</span>&nbsp<span>${rdto.b_wtime }</span>
                            <p>${rdto.b_content }</p>
                            <div class="textbox_write">
                                <div class="write_write">
                                	<input type="hidden" id="chkComment${rdto.b_no_pk}" value="0">
                                	<input type="hidden" id="reComment${rdto.b_no_pk}" value="0">
                                    <a class="replyA" onclick="replyBoxOpen(${rdto.b_no_pk})">답글</a>&nbsp
                                    <c:forEach var="rrdto" items="${replyHM[dto.b_no_pk] }">
                					<c:if test="${rrdto.b_indentNum eq 2 && rrdto.b_stepNum eq rdto.b_no_pk }">
                						<c:set var="flag" value="true"/>
                        			</c:if>
                        			</c:forEach>
                        			<c:if test="${flag eq true }">
                        			<a class="readMoreA" onclick="replyOpen(${rdto.b_no_pk})">더보기</a>
                						<c:set var="flag" value="false"/>
                        			</c:if>
                                </div>
                            </div>
                         <!-- -----------답글 입력------------ -->
                            <div class="textbox_textarea" id="textarea${rdto.b_no_pk }">
                    		<form action="board_replySend.do" method="post" onsubmit="return sendReply(${rdto.b_no_pk });">
                    			<input type="hidden" name="b_groupNum" value="${dto.b_no_pk }">
                    			<input type="hidden" name="b_stepNum" value="${rdto.b_no_pk }">
                    			<input type="hidden" name="b_indentNum" value="2">
                    			<input type="hidden" name="b_replyId">
                       	 		<div class="textarea_image">
                            		<img src="${userImage }" alt="">
                        		</div>
                        		<div class="textarea_textbox">
                            		<textarea name="b_content" id="content${rdto.b_no_pk }" onkeydown="autoHeight(this)" onkeyup="autoHeight(this)" placeholder="댓글 추가..."></textarea>
                        		</div>
                        		<div class="textarea_submit"><input type="submit" value="댓글"></div>
                    		</form>
                    		</div>
                    	<!-- -----------답글 입력 end------------ -->
                        </div>
                		<!-- -----------답글 내용------------ -->
                        <div class="replyarea_rereplyarea" id="rereplyarea${rdto.b_no_pk }">
                		<c:forEach var="rrdto" items="${replyHM[dto.b_no_pk] }">
                		<c:if test="${rrdto.b_indentNum eq 2 && rrdto.b_stepNum eq rdto.b_no_pk }">
                            <div class="rereplyarea_image"><img src="${replyMemberHM[rrdto.b_id_fk].m_image }" alt=""></div>
                            <div class="rereplyarea_textbox">
                                <span>${replyMemberHM[rrdto.b_id_fk].m_lastName } ${replyMemberHM[rrdto.b_id_fk].m_name }</span>&nbsp<span>${rrdto.b_wtime }</span>
                                <p>
                                	<c:if test="${not empty rrdto.b_replyId }">
                                		<span style="color : blue">@${replyMemberHM[rrdto.b_replyId].m_lastName } ${replyMemberHM[rrdto.b_replyId].m_name }</span>&nbsp
                                	</c:if>
                                	${rrdto.b_content }
                                </p>
                                <div class="textbox_write">
                                    <div class="write_write">
                                        <input type="hidden" id="rereComment${rrdto.b_no_pk}" value="0">
                                    	<a class="replyA" onclick="rereplyBoxOpen(${rrdto.b_no_pk})">답글</a><br>
                                    </div>
                                </div>
                            <!-- -----------답답글 입력------------ -->
                            	<div class="textbox_textarea" id="rereTextarea${rrdto.b_no_pk }">
                    			<form action="board_replySend.do" method="post" onsubmit="return sendReply(${rrdto.b_no_pk });">
                    				<input type="hidden" name="b_groupNum" value="${dto.b_no_pk }">
                    				<input type="hidden" name="b_stepNum" value="${rdto.b_no_pk }">
                    				<input type="hidden" name="b_indentNum" value="2">
                    				<input type="hidden" name="b_replyId" value="${rrdto.b_id_fk }">
                       	 			<div class="textarea_image">
                            			<img src="${userImage }" alt="">
                        			</div>
                        			<div class="textarea_textbox">
                            			<textarea name="b_content" id="content${rrdto.b_no_pk }" onkeydown="autoHeight(this)" onkeyup="autoHeight(this)" placeholder="댓글 추가..."></textarea>
                        			</div>
                        			<div class="textarea_submit"><input type="submit" value="댓글"></div>
                    			</form>
                    			</div>
                    		<!-- -----------답답글 입력 end------------ -->
                            </div>
                		</c:if>
                		</c:forEach>
                        </div>
                		<!-- -----------답글 내용 end------------ -->
                    </div>
                	</c:if>
                	</c:forEach>
               	<!-- -----------댓글 내용 end------------ -->
                </div>
      		<!-- -----------댓글 상자 end------------ -->
</body>
</html>