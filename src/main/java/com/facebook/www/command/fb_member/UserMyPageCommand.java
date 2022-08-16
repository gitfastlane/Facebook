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

public class UserMyPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	String userID = (String) session.getAttribute("userID");
    	session.removeAttribute("hostID");
    	String hostID = userID;
    	session.setAttribute("hostID", hostID);
    	
        Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
        Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
        Fb_tagDAO tdao = Fb_tagDAO.getFb_tagDAO();
        
        ArrayList<Fb_boardDTO> list = bdao.selectListById(hostID);
        HashMap<String, Fb_memberDTO> memberHM = mdao.pickMemberById(list);
        Fb_memberDTO mdto = mdao.selectOneById(hostID);
        HashMap<Integer, String> tagHM = tdao.pickTagFullNameByNo(list);
        ArrayList<String> topTenTagList = tdao.topTenTagListById(hostID);
        
        Fb_friendsDAO fdao = Fb_friendsDAO.getFb_friendsDAO();
        ArrayList<Fb_friendsDTO> friendsList_sub = fdao.selectListById(userID);
		HashMap<String, Fb_memberDTO> friendsListHM_sub = mdao.pickFriendById(friendsList_sub);
        
        request.setAttribute("m_lastName", mdto.getM_lastName());
        request.setAttribute("m_name", mdto.getM_name());
        request.setAttribute("m_image", mdto.getM_image());
        request.setAttribute("friendsList_sub", friendsList_sub);
		request.setAttribute("friendsListHM_sub", friendsListHM_sub);
        request.setAttribute("list", list);
        request.setAttribute("memberHM", memberHM);
        request.setAttribute("tagHM", tagHM);
        request.setAttribute("topTenTagList", topTenTagList);
        //request.setAttribute("userID", userID);
        //request.setAttribute("hostID", hostID);
	}

}
