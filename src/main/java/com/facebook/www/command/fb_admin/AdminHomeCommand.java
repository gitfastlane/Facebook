package com.facebook.www.command.fb_admin;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_adminDAO;

public class AdminHomeCommand implements Command{

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
		
		LocalDate now = LocalDate.now();
		int year = now.getYear() - 2000;
		int month = now.getMonthValue();
		int start = 1;
		int end = 31;
		
		System.out.println("yearstr: "+yearstr);
		System.out.println("monthstr: "+monthstr);
		System.out.println("startstr: "+startstr);
		System.out.println("endstr: "+endstr);
		
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
		
		String countDate = "20"+year+" - "+month;
		Fb_adminDAO adao = Fb_adminDAO.getFb_adminDAO();
		int[][] countArr = adao.boardCountChart(start, end, month, year);	// 게시글 차트
		int[][] memberArr = adao.memberCountChart(start, end, month, year);	// 가입인원 차트
		String[][] genderArr = adao.genderCountChart();						// 성비 차트
		String[][] ageArr = adao.ageCountChart();							// 나이 차트
		
		request.setAttribute("countArr", countArr);
		request.setAttribute("memberArr", memberArr);
		request.setAttribute("genderArr", genderArr);
		request.setAttribute("ageArr", ageArr);
		
		request.setAttribute("countDate", countDate);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
	}

}
