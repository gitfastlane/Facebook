package com.facebook.www.command.fb_board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dto.Fb_boardDTO;

public class PhotoAlbumDeleteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] b_no_pk_list = request.getParameterValues("b_no_pk_list");
		if(b_no_pk_list!=null) {
			ArrayList<Fb_boardDTO> list = new ArrayList<>();
			for(int i=0;i<b_no_pk_list.length;i++) {
				String b_no_pkStr = b_no_pk_list[i];
				int b_no_pk = Integer.parseInt(b_no_pkStr);
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(b_no_pk);
				list.add(dto);
			}
			Fb_boardDAO dao = Fb_boardDAO.getFb_boardDAO();
			dao.deleteListByNo(list);			
		}
	}

}
