package com.facebook.www.command.fb_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dto.Fb_boardDTO;

public class DeletePostCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_no_pkStr = request.getParameter("b_no_pk");
		int b_no_pk = Integer.parseInt(b_no_pkStr);
		Fb_boardDTO dto = new Fb_boardDTO();
		dto.setB_no_pk(b_no_pk);
		Fb_boardDAO dao = Fb_boardDAO.getFb_boardDAO();
		dao.deleteOneByNo(dto);
	}

}
