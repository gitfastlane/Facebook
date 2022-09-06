package com.facebook.www.command.fb_admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_adminDAO;

public class AdminBoardContentDeleteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] b_no_pkstrArr = request.getParameterValues("board_check");
		if(b_no_pkstrArr!=null) {
			int[] b_no_pkArr = new int[b_no_pkstrArr.length];
			for(int i=0;i<b_no_pkstrArr.length;i++) {
				b_no_pkArr[i] = Integer.parseInt(b_no_pkstrArr[i]);
			}
			Fb_adminDAO dao = Fb_adminDAO.getFb_adminDAO();
			dao.deleteListBoard(b_no_pkArr);			
		}
	}

}
