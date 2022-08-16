// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindAccountByEmailCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.common.MailSendPW;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_memberDTO;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAccountByEmailCommand
    implements Command
{

    public FindAccountByEmailCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String id = request.getParameter("id");
        Fb_memberDAO dao = Fb_memberDAO.getFb_memberDAO();
        Fb_memberDTO dto = dao.findAccountByEmail(id);
        String email = dto.getM_email();
        if(email == null)
        {
            request.setAttribute("email", null);
        } else
        {
            int emailKey = (int)(Math.random() * 8999D + 1000D);
            System.out.println("pwMailSend \uC2E4\uD589 \uC2DC\uC791");
            MailSendPW.pwMailSend(email, emailKey);
            System.out.println("pwMailSend \uC2E4\uD589 \uC644\uB8CC");
            String star = "";
            for(int i = 1; i < email.indexOf("@") - 1; i++)
                star = (new StringBuilder(String.valueOf(star))).append("*").toString();

            String printEmail = (new StringBuilder(String.valueOf(email.substring(0, 1)))).append(star).append(email.substring(email.indexOf("@") - 1)).toString();
            request.setAttribute("id", id);
            request.setAttribute("email", printEmail);
            request.setAttribute("emailKey", Integer.valueOf(emailKey));
        }
    }
}
