// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignupOKCommand.java

package com.facebook.www.command.fb_member;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_memberDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupOKCommand
    implements Command
{

    public SignupOKCommand()
    {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Fb_memberDTO dto = new Fb_memberDTO();
        dto.setM_lastName(request.getParameter("lastName"));
        dto.setM_name(request.getParameter("name"));
        dto.setM_id_pk(request.getParameter("id"));
        dto.setM_pw(request.getParameter("pw"));
        dto.setM_email(request.getParameter("email"));
        dto.setM_birthYear(request.getParameter("birth_year"));
        dto.setM_birthMonth(request.getParameter("birth_month"));
        dto.setM_birthDay(request.getParameter("birth_day"));
        dto.setM_gender(request.getParameter("gender"));
        Fb_memberDAO mdao = Fb_memberDAO.getFb_memberDAO();
        mdao.signupOK(dto);
        
        Fb_boardDTO bdto = new Fb_boardDTO(); // 기본 board 작성
        bdto.setB_id_fk(dto.getM_id_pk());
        bdto.setB_content("페이스북에 오신걸 환영합니다.");
        Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
        bdao.writePostOK(bdto, null);
    }
}
