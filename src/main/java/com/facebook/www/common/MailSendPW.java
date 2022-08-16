// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MailSendPW.java

package com.facebook.www.common;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

// Referenced classes of package com.facebook.www.common:
//            MailAuth

public class MailSendPW
{

    public MailSendPW()
    {
    }

    public static void pwMailSend(String email, int emailKey)
    {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Authenticator auth = new MailAuth();
        Session session = Session.getDefaultInstance(prop, auth);
        MimeMessage msg = new MimeMessage(session);
        try
        {
            msg.setSentDate(new Date());
            msg.setFrom(new InternetAddress("abcdudals@gmail.com", "Project(facebook)"));
            InternetAddress to = new InternetAddress(email);
            msg.setRecipient(javax.mail.Message.RecipientType.TO, to);
            String mailSubject = "Project(facbook) \uBE44\uBC00\uBC88\uD638 \uC778\uC99D\uBC88\uD638 \uC804\uC1A1";
            String mailText = (new StringBuilder("\uC778\uC99D\uBC88\uD638: ")).append(emailKey).toString();
            msg.setSubject(mailSubject, "UTF-8");
            msg.setText(mailText, "UTF-8");
            Transport.send(msg);
        }
        catch(AddressException e)
        {
            System.out.println("AddressException !!!!!!!!!!!!");
            e.printStackTrace();
        }
        catch(MessagingException e)
        {
            System.out.println("MessagingException !!!!!!!!!!!!");
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("UnsupportedEncodingException !!!!!!!!!!!!");
            e.printStackTrace();
        }
    }
}
