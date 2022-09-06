package com.facebook.www.command.fb_admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_adminDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class AdminMemberOneCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminID = (String) session.getAttribute("adminID");
		if(adminID==null||adminID=="") {
			return;
		}
		
		String m_id_pk = request.getParameter("m_id_pk");
		String searchText = request.getParameter("searchText");
		String contentAmountstr = request.getParameter("contentAmount");
		String pageNumstr = request.getParameter("pageNum");
		
		int contentAmount = 15;
		int pageNum = 1;
		
		if(contentAmountstr!=null&&contentAmountstr!="") contentAmount = Integer.parseInt(contentAmountstr);
		if(pageNumstr!=null&&pageNumstr!="") pageNum = Integer.parseInt(pageNumstr);
		int startPage = (pageNum-1)*contentAmount;
		
		Fb_adminDAO adao = Fb_adminDAO.getFb_adminDAO();
		int totalCount = adao.countSelectBoardById(m_id_pk, searchText);
		int pageAmount = (int)(Math.ceil((double)totalCount/contentAmount));
		ArrayList<Fb_boardDTO> blist = adao.selectBoardById(m_id_pk, searchText, startPage, contentAmount);
		Fb_memberDTO mdto = adao.selectOneById(m_id_pk);
		
		request.setAttribute("searchText", searchText);
		request.setAttribute("pageAmount", pageAmount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("blist", blist);
		request.setAttribute("mdto", mdto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("contentAmount", contentAmount);
	}

}
