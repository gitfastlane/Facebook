package com.facebook.www.command.fb_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_friendsDAO;

public class FriendAddCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String friendID = request.getParameter("friendID");
		System.out.println("friendID: "+friendID);
		Fb_friendsDAO dao = Fb_friendsDAO.getFb_friendsDAO();
		dao.askFriend(userID, friendID);
	}

}
