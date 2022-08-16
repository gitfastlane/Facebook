package com.facebook.www.command.fb_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_memberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class myProfileOKCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		Fb_memberDTO dto = new Fb_memberDTO();
		
		String folderName = "userFile";
		String path = request.getSession().getServletContext().getRealPath(folderName);
		int size = 1024*1024*10;
		System.out.println("myProfileOKCommand path: "+path);
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
		String fileName = multi.getFilesystemName("m_image");
		String savePath;
		if(fileName==null) {
			savePath = multi.getParameter("before_image");
		}else {
			savePath = folderName+"/"+fileName;
		}
		dto.setM_lastName(multi.getParameter("m_lastName"));
		dto.setM_name(multi.getParameter("m_name"));
		dto.setM_birthYear(multi.getParameter("m_birthYear"));
		dto.setM_birthMonth(multi.getParameter("m_birthMonth"));
		dto.setM_birthDay(multi.getParameter("m_birthDay"));
		dto.setM_gender(multi.getParameter("m_gender"));
		dto.setM_image(savePath);
		dto.setM_id_pk(userID);
		
		Fb_memberDAO dao = Fb_memberDAO.getFb_memberDAO();
		dao.myProfileUpdate(dto);
	}

}
