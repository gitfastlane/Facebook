// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindAccountChangePwCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAccountChangePwCommand
    implements Command
{

    public FindAccountChangePwCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String id = (String)request.getAttribute("id");
        request.setAttribute("id", id);
    }
}
