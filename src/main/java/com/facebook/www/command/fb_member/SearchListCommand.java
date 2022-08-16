package com.facebook.www.command.fb_member;

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
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dao.Fb_tagDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_friendsDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class SearchListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String searchTag = request.getParameter("searchTag");
		
		Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
		Fb_friendsDAO fdao = Fb_friendsDAO.getFb_friendsDAO();
		Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
		Fb_tagDAO tdao = Fb_tagDAO.getFb_tagDAO();
		
		//검색기능
//		if(searchTag.contains("#")) {
//			ArrayList<Fb_boardDTO> blist = bdao.searchBoardByTag(searchTag);
//			HashMap<Integer, String> tagHM = tdao.pickTagFullNameByNo(blist);
//			request.
//		}else {
//			ArrayList<Fb_memberDTO> mlist = mdao.searchMemberList(searchTag);
//			ArrayList<String> flist = fdao.printFriendIdList(userID);
//			request.setAttribute("mlist", mlist);
//			request.setAttribute("flist", flist);					
//		}
		
		//친구목록
        ArrayList<Fb_friendsDTO> friendsList_sub = fdao.selectListById(userID);
		HashMap<String, Fb_memberDTO> friendsListHM_sub = mdao.pickFriendById(friendsList_sub);
		
		request.setAttribute("friendsList_sub", friendsList_sub);
		request.setAttribute("friendsListHM_sub", friendsListHM_sub);
		
	}

}
