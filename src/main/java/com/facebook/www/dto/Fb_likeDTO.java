// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fb_likeDTO.java

package com.facebook.www.dto;


public class Fb_likeDTO
{

    public Fb_likeDTO()
    {
    }

    public int getLi_no_fk()
    {
        return li_no_fk;
    }

    public void setLi_no_fk(int li_no_fk)
    {
        this.li_no_fk = li_no_fk;
    }

    public String getLi_id_fk()
    {
        return li_id_fk;
    }

    public void setLi_id_fk(String li_id_fk)
    {
        this.li_id_fk = li_id_fk;
    }

    private int li_no_fk;
    private String li_id_fk;
}
