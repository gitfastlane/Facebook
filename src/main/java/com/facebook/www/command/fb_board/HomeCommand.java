package com.facebook.www.command.fb_board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dao.Fb_friendsDAO;
import com.facebook.www.dao.Fb_likeDAO;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dao.Fb_tagDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_friendsDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class HomeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		session.removeAttribute("userImage");
		//request.setAttribute("userImage", userImage);
		
		Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
		Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
		Fb_tagDAO tdao = Fb_tagDAO.getFb_tagDAO();
		Fb_friendsDAO fdao = Fb_friendsDAO.getFb_friendsDAO();
		Fb_likeDAO ldao = Fb_likeDAO.getFb_likeDAO();
		
		Fb_memberDTO mdto = mdao.selectOneById(userID);
		session.setAttribute("userImage", mdto.getM_image()); // userImage 업로드
		
		//메인내용 불러오기
		ArrayList<Fb_boardDTO> blist = bdao.selectListAddFriendsById(userID);
		HashMap<String, Fb_memberDTO> memberHM = mdao.pickMemberById(blist);
		HashMap<Integer, String> tagHM = tdao.pickTagFullNameByNo(blist);
		ArrayList<Integer> llist = ldao.pickIsLikeByNo(blist, userID);	//좋아요
		
		//친구목록 불러오기
        ArrayList<Fb_friendsDTO> friendsList_sub = fdao.selectListById(userID);
		HashMap<String, Fb_memberDTO> friendsListHM_sub = mdao.pickFriendById(friendsList_sub);
		
		//추천친구목록 불러오기
		ArrayList<Fb_memberDTO> recommandList = mdao.recommandMemberListById(userID);
		
		//데이터 전송
		request.setAttribute("list", blist);
		request.setAttribute("memberHM", memberHM);
		request.setAttribute("tagHM", tagHM);
		request.setAttribute("llist", llist);
		//request.setAttribute("userID", userID);
		
		request.setAttribute("friendsList_sub", friendsList_sub);
		request.setAttribute("friendsListHM_sub", friendsListHM_sub);
		
		request.setAttribute("recommandList", recommandList);
	}

}
