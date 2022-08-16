package com.facebook.www.command.fb_member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_friendsDAO;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_friendsDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class FriendsListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String hostID = (String) session.getAttribute("hostID");
		
		System.out.println("FriendsListCommand hostID: "+hostID);
		
		Fb_friendsDAO fdao = Fb_friendsDAO.getFb_friendsDAO();
		Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
		ArrayList<Fb_friendsDTO> friendsList = fdao.selectListById(hostID);
		HashMap<String, Fb_memberDTO> friendsListHM = mdao.pickFriendById(friendsList);
		ArrayList<Fb_friendsDTO> askList = fdao.selectAskListById(hostID);
		HashMap<String, Fb_memberDTO> askListHM = mdao.pickAskFriendById(askList);
		Fb_memberDTO mdto = mdao.selectOneById(hostID);
		
		ArrayList<Fb_friendsDTO> friendsList_sub = fdao.selectListById(userID);
		HashMap<String, Fb_memberDTO> friendsListHM_sub = mdao.pickFriendById(friendsList_sub);
		
		request.setAttribute("m_lastName", mdto.getM_lastName());
        request.setAttribute("m_name", mdto.getM_name());
        request.setAttribute("m_image", mdto.getM_image());
		request.setAttribute("friendsList", friendsList);
		request.setAttribute("friendsListHM", friendsListHM);
		request.setAttribute("friendsList_sub", friendsList_sub);
		request.setAttribute("friendsListHM_sub", friendsListHM_sub);
		request.setAttribute("askList", askList);
		request.setAttribute("askListHM", askListHM);
		//request.setAttribute("userID", userID);
        //request.setAttribute("hostID", hostID);
        
	}

}
