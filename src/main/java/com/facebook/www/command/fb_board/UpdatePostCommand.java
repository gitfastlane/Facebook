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
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dao.Fb_tagDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_friendsDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class UpdatePostCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("b_no_pk");
		int b_no_pk = Integer.parseInt(no);
		Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
		Fb_tagDAO tdao = Fb_tagDAO.getFb_tagDAO();
		Fb_boardDTO bdto = bdao.selectOneByNo(b_no_pk);
		ArrayList<String> tagList = tdao.selectTagNameByNo(b_no_pk);
		String tagName = "";
		for(int i=0;i<tagList.size();i++) {
			tagName += tagList.get(i);
		}
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
		Fb_memberDTO mdto = mdao.selectOneById(userID);
		
		Fb_friendsDAO fdao = Fb_friendsDAO.getFb_friendsDAO();
		ArrayList<Fb_friendsDTO> friendsList_sub = fdao.selectListById(userID);
		HashMap<String, Fb_memberDTO> friendsListHM_sub = mdao.pickFriendById(friendsList_sub);
		
		request.setAttribute("m_lastName", mdto.getM_lastName());
        request.setAttribute("m_name", mdto.getM_name());
        request.setAttribute("m_image", mdto.getM_image());
        request.setAttribute("friendsList_sub", friendsList_sub);
		request.setAttribute("friendsListHM_sub", friendsListHM_sub);
		request.setAttribute("dto", bdto);
		request.setAttribute("tagName", tagName);
	}

}
