package com.facebook.www.command.fb_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dto.Fb_boardDTO;

public class ReplySendCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String b_groupNumstr = request.getParameter("b_groupNum");
		String b_stepNumstr = request.getParameter("b_stepNum");
		String b_indentNumstr = request.getParameter("b_indentNum");
		int b_groupNum = Integer.parseInt(b_groupNumstr);
		int b_stepNum = 0;
		if(b_stepNumstr!=null&&b_stepNumstr!="") {
			b_stepNum = Integer.parseInt(b_stepNumstr);
		}
		int b_indentNum = Integer.parseInt(b_indentNumstr);
		String b_replyId = request.getParameter("b_replyId");
		String b_content = request.getParameter("b_content");
		
		Fb_boardDTO dto = new Fb_boardDTO();
		dto.setB_id_fk(userID);
		dto.setB_content(b_content);
		dto.setB_groupNum(b_groupNum);
		dto.setB_stepNum(b_stepNum);
		dto.setB_indentNum(b_indentNum);
		dto.setB_replyId(b_replyId);
		
		Fb_boardDAO dao = Fb_boardDAO.getFb_boardDAO();
		dao.writeReply(dto);
		
		request.setAttribute("b_no_pk", b_groupNum); // 본 게시글 번호
	}

}
