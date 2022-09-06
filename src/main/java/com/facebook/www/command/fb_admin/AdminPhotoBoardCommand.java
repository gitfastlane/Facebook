package com.facebook.www.command.fb_admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_adminDAO;
import com.facebook.www.dto.Fb_boardDTO;

public class AdminPhotoBoardCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminID = (String) session.getAttribute("adminID");
		if(adminID==null||adminID=="") {
			return;
		}
		
		String yearstr = request.getParameter("year");
		String monthstr = request.getParameter("month");
		String startstr = request.getParameter("start");
		String endstr = request.getParameter("end");
		String contentAmountstr = request.getParameter("contentAmount");
		String pageNumstr = request.getParameter("pageNum");
		
		LocalDate now = LocalDate.now();
		int year = now.getYear() - 2000;
		int month = now.getMonthValue();
		int start = 1;
		int end = 31;
		int contentAmount = 15;
		int pageNum = 1;
		
		if(yearstr!=null&&yearstr!="") year = Integer.parseInt(yearstr);
		if(monthstr!=null&&monthstr!="") month = Integer.parseInt(monthstr);
		if(startstr!=null&&startstr!="") start = Integer.parseInt(startstr);
		if(endstr!=null&&endstr!="") end = Integer.parseInt(endstr);
		if(start>end) {
			int temp = start;
			start = end;
			end = temp;
		}
		if(month==2||month==4||month==6||month==9||month==11) {
			if(month==2) {
				if(end>28) end=28;
			}else {
				if(end>30) end=30;				
			}
		}
		
		if(contentAmountstr!=null&&contentAmountstr!="") contentAmount = Integer.parseInt(contentAmountstr);
		if(pageNumstr!=null&&pageNumstr!="") pageNum = Integer.parseInt(pageNumstr);
		int startPage = (pageNum-1)*contentAmount;
		
		Fb_adminDAO adao = Fb_adminDAO.getFb_adminDAO();
		int totalCount = adao.countPhotoBoard(start, end, month, year);
		int pageAmount = (int)(Math.ceil((double)totalCount/contentAmount));
		ArrayList<Fb_boardDTO> blist = adao.photoBoard(start, end, month, year, startPage, contentAmount);
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("pageAmount", pageAmount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("blist", blist);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("contentAmount", contentAmount);
	}

}
