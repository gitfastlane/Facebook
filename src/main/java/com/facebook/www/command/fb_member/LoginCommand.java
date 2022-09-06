
package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_adminDAO;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_adminDTO;
import com.facebook.www.dto.Fb_memberDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginCommand implements Command{

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	
    	//admin check
    	Fb_adminDTO adto = new Fb_adminDTO();
    	adto.setA_id_pk(id);
    	adto.setA_pw(pw);
    	Fb_adminDAO adao = Fb_adminDAO.getFb_adminDAO();
    	
    	//member check
        Fb_memberDTO dto = new Fb_memberDTO();
        dto.setM_id_pk(id);
        dto.setM_pw(pw);
        Fb_memberDAO dao = Fb_memberDAO.getFb_memberDAO();
        
        HttpSession session = request.getSession();
        session.invalidate();
        if(adao.adminLoginchk(adto)) {
        	session = request.getSession();
        	session.setAttribute("adminID", adto.getA_id_pk());
        }else {
        	if(dao.loginOK(dto)) {
        		session = request.getSession();
        		session.setAttribute("userID", dto.getM_id_pk());
        		Fb_memberDTO mdto = dao.selectOneById(dto.getM_id_pk());
        		session.setAttribute("userImage", mdto.getM_image());
        	}//로그인        	
        }
    }
}
