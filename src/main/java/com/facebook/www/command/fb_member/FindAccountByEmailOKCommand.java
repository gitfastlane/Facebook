// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindAccountByEmailOKCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAccountByEmailOKCommand
    implements Command
{

    public FindAccountByEmailOKCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setAttribute("id", request.getAttribute("id"));
        request.setAttribute("email", request.getAttribute("email"));
        request.setAttribute("emailKey", request.getAttribute("emailKey"));
    }
}
