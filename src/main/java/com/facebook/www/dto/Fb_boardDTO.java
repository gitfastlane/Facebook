
package com.facebook.www.dto;


public class Fb_boardDTO
{
	
	private int b_no_pk;
	private String b_id_fk;
	private String b_wtime;
	private String b_content;
	private String b_image;
	private int b_groupNum;
	private int b_stepNum;
	private int b_indentNum;
	private int b_like;

    public Fb_boardDTO()
    {
    }

    public int getB_no_pk()
    {
        return b_no_pk;
    }

    public void setB_no_pk(int b_no_pk)
    {
        this.b_no_pk = b_no_pk;
    }

    public String getB_id_fk()
    {
        return b_id_fk;
    }

    public void setB_id_fk(String b_id_fk)
    {
        this.b_id_fk = b_id_fk;
    }

    public String getB_wtime()
    {
        return b_wtime;
    }

    public void setB_wtime(String b_wtime)
    {
        this.b_wtime = b_wtime;
    }

    public String getB_content()
    {
        return b_content;
    }

    public void setB_content(String b_content)
    {
        this.b_content = b_content;
    }

    public String getB_image()
    {
        return b_image;
    }

    public void setB_image(String b_image)
    {
        this.b_image = b_image;
    }

    public int getB_groupNum()
    {
        return b_groupNum;
    }

    public void setB_groupNum(int b_groupNum)
    {
        this.b_groupNum = b_groupNum;
    }

    public int getB_stepNum()
    {
        return b_stepNum;
    }

    public void setB_stepNum(int b_stepNum)
    {
        this.b_stepNum = b_stepNum;
    }

    public int getB_indentNum()
    {
        return b_indentNum;
    }

    public void setB_indentNum(int b_indentNum)
    {
        this.b_indentNum = b_indentNum;
    }

    public int getB_like()
    {
        return b_like;
    }

    public void setB_like(int b_like)
    {
        this.b_like = b_like;
    }
}
