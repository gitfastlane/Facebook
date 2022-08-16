// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MailAuth.java

package com.facebook.www.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator
{

    public MailAuth()
    {
        String mail_id = "abcdudals";
        String mail_pw = "nulv parg espm epnw";
        pa = new PasswordAuthentication(mail_id, mail_pw);
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return pa;
    }

    PasswordAuthentication pa;
}
