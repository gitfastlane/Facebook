package com.facebook.www.command.fb_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_profileDAO;
import com.facebook.www.dto.Fb_profileDTO;

public class ProfileUpdateCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		Fb_profileDTO dto = new Fb_profileDTO();
		dto.setP_locationCountry(request.getParameter("p_locationCountry"));
		dto.setP_locationCity(request.getParameter("p_locationCity"));
		dto.setP_school(request.getParameter("p_school"));
		dto.setP_schoolName(request.getParameter("p_schoolName"));
		dto.setP_schoolYear1(request.getParameter("p_schoolYear1"));
		dto.setP_schoolMonth1(request.getParameter("p_schoolMonth1"));
		dto.setP_schoolDay1(request.getParameter("p_schoolDay1"));
		dto.setP_schoolYear2(request.getParameter("p_schoolYear2"));
		dto.setP_schoolMonth2(request.getParameter("p_schoolMonth2"));
		dto.setP_schoolDay2(request.getParameter("p_schoolDay2"));
		dto.setP_schoolLocation(request.getParameter("p_schoolLocation"));
		dto.setP_workName(request.getParameter("p_workName"));
		dto.setP_workPlace(request.getParameter("p_workPlace"));
		dto.setP_workYear1(request.getParameter("p_workYear1"));
		dto.setP_workMonth1(request.getParameter("p_workMonth1"));
		dto.setP_workDay1(request.getParameter("p_workDay1"));
		dto.setP_workYear2(request.getParameter("p_workYear2"));
		dto.setP_workMonth2(request.getParameter("p_workMonth2"));
		dto.setP_workDay2(request.getParameter("p_workDay2"));
		dto.setP_workLocation(request.getParameter("p_workLocation"));
		dto.setP_relation1(request.getParameter("p_relation1"));
		dto.setP_relation2(request.getParameter("p_relation2"));
		dto.setP_relation3(request.getParameter("p_relation3"));
		dto.setP_relationName1(request.getParameter("p_relationName1"));
		dto.setP_relationName2(request.getParameter("p_relationName2"));
		dto.setP_relationName3(request.getParameter("p_relationName3"));
		dto.setP_id_fk(userID);
		Fb_profileDAO dao = Fb_profileDAO.getFb_profileDAO();
		dao.profileUpdate(dto);
	}

}
