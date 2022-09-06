package com.facebook.www.command.fb_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_memberDAO;

public class DeleteAccountCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
		mdao.deleteAccount(userID);
		session.invalidate();
	}

}
