// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IdCheckOKCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_memberDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdCheckOKCommand
    implements Command
{

    public IdCheckOKCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String newId = request.getParameter("idchk");
        Fb_memberDAO dao = Fb_memberDAO.getFb_memberDAO();
        boolean result = false;
        if(dao.idCheckOK(newId))
            result = true;
        request.setAttribute("newId", newId);
        request.setAttribute("result", Boolean.valueOf(result));
    }
}
