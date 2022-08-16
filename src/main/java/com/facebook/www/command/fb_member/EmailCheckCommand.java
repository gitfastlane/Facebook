// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EmailCheckCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.common.MailSendPW;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailCheckCommand
    implements Command
{

    public EmailCheckCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String email = (String)request.getAttribute("email");
        int emailKey = (int)(Math.random() * 8999D + 1000D);
        System.out.println("pwMailSend \uC2E4\uD589 \uC2DC\uC791");
        MailSendPW.pwMailSend(email, emailKey);
        request.setAttribute("email", email);
        request.setAttribute("emailKey", Integer.valueOf(emailKey));
    }
}
