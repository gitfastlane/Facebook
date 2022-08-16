package com.facebook.www.command.fb_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdatePostOKCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderName = "userFile";
		String path = request.getSession().getServletContext().getRealPath(folderName);
		int size = 1024*1024*10;
		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			String fileName = multi.getFilesystemName("b_image");
			String savePath;
			if(fileName==null) {
				savePath = multi.getParameter("before_image");
			}else {
				savePath = folderName+"/"+fileName;
			}
			System.out.println("UpdatePostOKCommand path: "+path);
            System.out.println("savePath: "+savePath);
            String b_no_pkStr = multi.getParameter("b_no_pk");
            int b_no_pk = Integer.parseInt(b_no_pkStr);
            String b_content = multi.getParameter("b_content");
            String t_hashTag = multi.getParameter("t_hashTag");
            String b_image = savePath;
            Fb_boardDTO dto = new Fb_boardDTO();
            dto.setB_no_pk(b_no_pk);
            dto.setB_content(b_content);
            dto.setB_image(b_image);
            Fb_boardDAO dao = Fb_boardDAO.getFb_boardDAO();
            dao.updatePostOK(dto, t_hashTag);
		}catch(Exception e) {
			
		}
	}

}
