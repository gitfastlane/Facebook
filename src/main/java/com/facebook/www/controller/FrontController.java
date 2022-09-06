
package com.facebook.www.controller;

import com.facebook.www.command.Command;
import com.facebook.www.command.fb_admin.AdminBoardCommand;
import com.facebook.www.command.fb_admin.AdminBoardContentDeleteCommand;
import com.facebook.www.command.fb_admin.AdminHomeCommand;
import com.facebook.www.command.fb_admin.AdminMemberCommand;
import com.facebook.www.command.fb_admin.AdminMemberOneCommand;
import com.facebook.www.command.fb_admin.AdminPhotoBoardCommand;
import com.facebook.www.command.fb_board.DeletePostCommand;
import com.facebook.www.command.fb_board.DeleteReplyCommand;
import com.facebook.www.command.fb_board.HomeCommand;
import com.facebook.www.command.fb_board.MyPageCommand;
import com.facebook.www.command.fb_board.PhotoAlbumCommand;
import com.facebook.www.command.fb_board.PhotoAlbumDeleteCommand;
import com.facebook.www.command.fb_board.PushLikeCommand;
import com.facebook.www.command.fb_board.ReplySendCommand;
import com.facebook.www.command.fb_board.ReplyShowCommand;
import com.facebook.www.command.fb_board.UpdatePostCommand;
import com.facebook.www.command.fb_board.UpdatePostOKCommand;
import com.facebook.www.command.fb_board.WritePostCommand;
import com.facebook.www.command.fb_board.WritePostOKCommand;
import com.facebook.www.command.fb_member.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("*.do")
public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
    public FrontController()
    {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doAction(request, response);
    }

    protected void doAction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        Command command = null;
        String commandName = request.getServletPath();
        String pageName = null;
        boolean isRedirect = false;
        System.out.println("commandName: "+commandName);
        
      //------------------회원가입 및 로그인------------------
        if(commandName.equals("/member_signup.do")) {	// 회원가입
        	pageName = "Facebook_signup.jsp";        	
        } else if(commandName.equals("/member_signupOK.do")){
            command = new SignupOKCommand();
            command.execute(request, response);
            pageName = "Facebook_login.jsp";
            isRedirect = true;
        } else if(commandName.equals("/JSP_lib/member_idCheckOK.do")){
            command = new IdCheckOKCommand();
            command.execute(request, response);
            pageName = "Facebook_idCheckFormOK.jsp";
        } else if(commandName.equals("/JSP_lib/member_emailCheck.do")){
            command = new EmailCheckCommand();
            command.execute(request, response);
            pageName = "Facebook_emailCheckOK.jsp";
        } else if(commandName.equals("/member_login.do")){	// 로그인
            pageName = "Facebook_login.jsp";
            isRedirect = true;
        } else if(commandName.equals("/member_loginOK.do")){
            command = new LoginCommand();
            command.execute(request, response);
            pageName = "Facebook_loginOK.jsp";
        } else if(commandName.equals("/member_findAccount.do")){	//비밀번호 찾기
            command = new FindAccountCommand();
            command.execute(request, response);
            pageName = "Facebook_findAccount.jsp";
        } else if(commandName.equals("/member_findAccountByEmail.do")){
            command = new FindAccountByEmailCommand();
            command.execute(request, response);
            pageName = "Facebook_findAccountByEmail.jsp";
        } else if(commandName.equals("/member_findAccountByEmailOK.do")){
            command = new FindAccountByEmailOKCommand();
            command.execute(request, response);
            pageName = "Facebook_findAccountByEmailOK.jsp";
        } else if(commandName.equals("/member_findAccountCheckKey.do")){
            command = new FindAccountCheckKeyCommand();
            command.execute(request, response);
            pageName = "Facebook_findAccountCheckKey.jsp";
        } else if(commandName.equals("/member_findAccountChangePw.do")){
            command = new FindAccountChangePwCommand();
            command.execute(request, response);
            pageName = "Facebook_findAccountChangePw.jsp";
        } else if(commandName.equals("/member_findAccountChangePwOK.do")){
            command = new FindAccountChangePwOKCommand();
            command.execute(request, response);
            pageName = "member_login.do";
        } else if(commandName.equals("/member_logout.do")){	// 로그아웃
        	command = new LogoutCommand();
        	command.execute(request, response);
        	pageName = "Facebook_login.jsp";
        	isRedirect = true;
        } else if(commandName.equals("/member_deleteAccount.do")){	// 계정 삭제
        	command = new DeleteAccountCommand();
        	command.execute(request, response);
        	pageName = "Facebook_login.jsp";
        	isRedirect = true;
            //--------------------------------------------
        } else if(commandName.equals("/home.do")) {
        	command = new HomeCommand();
        	command.execute(request, response);
        	pageName = "Facebook_home.jsp";     
        } else if(commandName.equals("/member_searchList.do")) {
        	command = new SearchListCommand();
        	command.execute(request, response);
        	pageName = "Facebook_searchList.jsp";
        	//-----------------내 정보관련--------------------
        } else if(commandName.equals("/board_userMyPage.do")){	// 접속자 페이지
        	command = new UserMyPageCommand();
        	command.execute(request, response);
        	pageName = "Facebook_myPage.jsp";        	
        } else if(commandName.equals("/board_myPage.do")){	// 내 페이지(외부인)
            command = new MyPageCommand();
            command.execute(request, response);
            pageName = "Facebook_myPage.jsp";
        } else if(commandName.equals("/board_writePost.do")) {	// 새글작성
        	command = new WritePostCommand();
        	command.execute(request, response);
        	pageName = "Facebook_writePost.jsp";        	
        } else if(commandName.equals("/board_writePostOK.do")){
            command = new WritePostOKCommand();
            command.execute(request, response);
            pageName = "board_userMyPage.do";
            isRedirect = true;
        } else if(commandName.equals("/board_updatePost.do")){
        	command = new UpdatePostCommand();
        	command.execute(request, response);
        	pageName = "Facebook_updatePost.jsp";
        } else if(commandName.equals("/board_updatePostOK.do")){
        	command = new UpdatePostOKCommand();
        	command.execute(request, response);
        	pageName = "board_userMyPage.do";
        	isRedirect = true;
        } else if(commandName.equals("/board_deletePost.do")){
        	command = new DeletePostCommand();
        	command.execute(request, response);
        	pageName = "board_userMyPage.do";
        	isRedirect = true;
        } else if(commandName.equals("/board_profile.do")){	// 프로필
            command = new ProfileCommand();
            command.execute(request, response);
            pageName = "Facebook_profile.jsp";
        } else if(commandName.equals("/member_profileUpdate.do")){
        	command = new ProfileUpdateCommand();
        	command.execute(request, response);
        	pageName = "board_profile.do";
        } else if(commandName.equals("/member_profileDelete.do")){
        	
        }else if(commandName.equals("/member_myProfile.do")) {
        	command = new myProfileCommand();
        	command.execute(request, response);
        	pageName = "Facebook_myProfile.jsp";
        }else if(commandName.equals("/member_myProfileOK.do")) {
        	command = new myProfileOKCommand();
        	command.execute(request, response);
        	pageName = "board_profile.do";
        }else if(commandName.equals("/board_photoAlbum.do")) { //사진첩
        	command = new PhotoAlbumCommand();
        	command.execute(request, response);
        	pageName = "Facebook_photoAlbum.jsp";
        }else if(commandName.equals("/board_photoAlbumDelete.do")) {
        	command = new PhotoAlbumDeleteCommand();
        	command.execute(request, response);
        	pageName = "board_photoAlbum.do";
        }else if(commandName.equals("/member_friendsList.do")) { //친구목록	
        	command = new FriendsListCommand();
        	command.execute(request, response);
        	pageName = "Facebook_friendsList.jsp";
        }else if(commandName.equals("/member_friendConfirm.do")) { 
        	command = new FriendConfirmCommand();
        	command.execute(request, response);
        	pageName = "member_friendsList.do";
        	isRedirect = true;
        }else if(commandName.equals("/member_friendReject.do")) { 
        	command = new FriendRejectCommand();
        	command.execute(request, response);
        	pageName = "member_friendsList.do";
        	isRedirect = true;
        }else if(commandName.equals("/member_friendCancel.do")) { 
        	command = new FriendCancelCommand();
        	command.execute(request, response);
        	pageName = "member_friendsList.do";
        	isRedirect = true;
        }else if(commandName.equals("/member_friendDelete.do")) {
        	command = new FriendDeleteCommand();
        	command.execute(request, response);
        	pageName = "member_friendsList.do";
        	isRedirect = true;
        }else if(commandName.equals("/member_friendBlock.do")) {
        	command = new FriendBlockCommand();
        	command.execute(request, response);
        	pageName = "member_friendsList.do";
        	isRedirect = true;        	
        }else if(commandName.equals("/member_friendRemoveBlock.do")) {
        	command = new FriendRemoveBlockCommand();
        	command.execute(request, response);
        	pageName = "member_friendsList.do";
        	isRedirect = true;        	        	
        }else if(commandName.equals("/member_friendAdd.do")) {
        	command = new FriendAddCommand();
        	command.execute(request, response);
        	return;
        }else if(commandName.equals("/board_pushLike.do")) {	//like 추가/제거
        	command = new PushLikeCommand();
        	command.execute(request, response);
        	return;       	
        }else if(commandName.equals("/board_replyShow.do")) {	//댓글 보기
        	command = new ReplyShowCommand();
        	command.execute(request, response);
        	pageName = "Facebook_replyOK.jsp";
        }else if(commandName.equals("/board_replySend.do")) {	//댓글 작성
        	command = new ReplySendCommand();
        	command.execute(request, response);
        	pageName = "board_replyShow.do";
        	//pageName = "home.do";
        }else if(commandName.equals("/board_replyDelete.do")) {	//댓글 삭제
        	command = new DeleteReplyCommand();
        	command.execute(request, response);
        	pageName = "board_replyShow.do";        	
        	//------------- 관리자 모드 ------------------------
        }else if(commandName.equals("/admin_home.do")) {
        	command = new AdminHomeCommand();
        	command.execute(request, response);
        	pageName = "Facebook_adminHome.jsp";
        }else if(commandName.equals("/admin_manageBoard.do")) {	//게시글 관리
        	command = new AdminBoardCommand();
        	command.execute(request, response);
        	pageName = "Facebook_adminBoard.jsp";
        }else if(commandName.equals("/admin_deleteBoardContent.do")) {	//게시글 삭제
        	command = new AdminBoardContentDeleteCommand();
        	command.execute(request, response);
        	pageName = "admin_manageBoard.do";
        }else if(commandName.equals("/admin_managePhoto.do")) {	//사진 관리
        	command = new AdminPhotoBoardCommand();
        	command.execute(request, response);
        	pageName = "Facebook_adminPhoto.jsp";
        }else if(commandName.equals("/admin_deletePhotoContent.do")) {	//사진 삭제
        	command = new AdminBoardContentDeleteCommand();
        	command.execute(request, response);
        	pageName = "admin_managePhoto.do";
        }else if(commandName.equals("/admin_manageMember.do")) {	//회원 관리
        	command = new AdminMemberCommand();
        	command.execute(request, response);
        	pageName = "Facebook_adminMember.jsp";
        }else if(commandName.equals("/admin_memberOne.do")) {	//회원 선택
        	command = new AdminMemberOneCommand();
        	command.execute(request, response);
        	pageName = "Facebook_adminMemberOne.jsp";
        }else if(commandName.equals("/admin_deleteMemberOne.do")) {	//회원 게시글&사진 삭제
        	command = new AdminBoardContentDeleteCommand();
        	command.execute(request, response);
        	pageName = "admin_memberOne.do";
        }
        
        
        if(isRedirect){
            response.sendRedirect(pageName);
        } else{
            RequestDispatcher dispatcher = request.getRequestDispatcher(pageName);
            dispatcher.forward(request, response);
        }
    }

}
