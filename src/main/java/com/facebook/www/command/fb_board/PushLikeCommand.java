package com.facebook.www.command.fb_board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_likeDAO;

public class PushLikeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String b_no_pkStr = request.getParameter("b_no_pk");
		System.out.println("b_no_pkStr: "+b_no_pkStr);
		int b_no_pk = 0;
		if(b_no_pkStr!=null) {
			b_no_pk = Integer.parseInt(b_no_pkStr);			
		}
		
		Fb_likeDAO ldao = Fb_likeDAO.getFb_likeDAO();
		int like = ldao.checkNPushReturnLike(b_no_pk, userID);
		
		PrintWriter pw = response.getWriter();
		pw.print(like);
	}

}
