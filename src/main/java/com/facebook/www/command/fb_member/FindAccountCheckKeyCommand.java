// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindAccountCheckKeyCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAccountCheckKeyCommand
    implements Command
{

    public FindAccountCheckKeyCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String emailKey = request.getParameter("emailKey");
        String userEmailKey = request.getParameter("userEmailKey");
        String id = request.getParameter("id");
        boolean confirmation = false;
        if(emailKey.matches(userEmailKey))
            confirmation = true;
        request.setAttribute("confirmation", Boolean.valueOf(confirmation));
        request.setAttribute("id", id);
    }
}
