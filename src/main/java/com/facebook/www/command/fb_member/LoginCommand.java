// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_memberDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginCommand
    implements Command
{

    public LoginCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Fb_memberDTO dto = new Fb_memberDTO();
        dto.setM_id_pk(request.getParameter("id"));
        dto.setM_pw(request.getParameter("pw"));
        Fb_memberDAO dao = Fb_memberDAO.getFb_memberDAO();
        HttpSession session = request.getSession();
        if(dao.loginOK(dto)) {
        	session.setAttribute("userID", dto.getM_id_pk());
        	Fb_memberDTO mdto = dao.selectOneById(dto.getM_id_pk());
        	session.setAttribute("userImage", mdto.getM_image());
        }
    }
}
