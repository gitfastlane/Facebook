package com.facebook.www.command.fb_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;

public class DeleteReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_no_pkstr = request.getParameter("b_no_pk");
		String b_no_pk_deletestr = request.getParameter("b_no_pk_delete");
		int b_no_pk = Integer.parseInt(b_no_pkstr);
		int b_no_pk_delete = Integer.parseInt(b_no_pk_deletestr);
		Fb_boardDAO dao = Fb_boardDAO.getFb_boardDAO();
		dao.deleteOneBoard(b_no_pk_delete);
		request.setAttribute("b_no_pk", b_no_pk);
	}

}
