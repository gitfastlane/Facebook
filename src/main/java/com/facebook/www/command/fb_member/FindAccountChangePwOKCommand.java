// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindAccountChangePwOKCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_memberDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAccountChangePwOKCommand
    implements Command
{

    public FindAccountChangePwOKCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        Fb_memberDTO dto = new Fb_memberDTO();
        dto.setM_id_pk(id);
        dto.setM_pw(pw);
        Fb_memberDAO dao = Fb_memberDAO.getFb_memberDAO();
        dao.findAccountChangePwOK(dto);
    }
}
