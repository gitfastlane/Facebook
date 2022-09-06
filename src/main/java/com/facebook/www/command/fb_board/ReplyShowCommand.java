package com.facebook.www.command.fb_board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class ReplyShowCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String b_no_pkstr = request.getParameter("b_no_pk");
		int b_no_pk = 0;
		if(b_no_pkstr==null) {
			b_no_pk = (int) request.getAttribute("b_no_pk");
		}else {
			b_no_pk = Integer.parseInt(b_no_pkstr);			
		}
		
		Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
		Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
		
		HashMap<Integer, ArrayList<Fb_boardDTO>> replyHM = bdao.pickReplyListByNo(b_no_pk);	//댓글
		HashMap<String, Fb_memberDTO> replyMemberHM = mdao.pickReplyMemberByNo(b_no_pk);	//댓글 member
	
		request.setAttribute("b_no_pk", b_no_pk);
		request.setAttribute("replyHM", replyHM);
		request.setAttribute("replyMemberHM", replyMemberHM);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("Facebook_replyOK.jsp");
	}

}
