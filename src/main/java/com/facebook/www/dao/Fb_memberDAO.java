// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fb_memberDAO.java

package com.facebook.www.dao;

import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_friendsDTO;
import com.facebook.www.dto.Fb_memberDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.*;
import javax.sql.DataSource;

public class Fb_memberDAO
{
	
	private static Fb_memberDAO dao = new Fb_memberDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_member";
	private final String TABLE_NAME_PROFILE = "fb_profile";
	private final String TABLE_NAME_BOARD = "fb_board";
	private final String TABLE_NAME_BOARD_TAG = "fb_board_tag";
	private final String TABLE_NAME_FRIENDS = "fb_friends";
	private final String BASE_IMAGE = " userFile/baseImage.jpg";

    private Fb_memberDAO()
    {
        try
        {
            Context context = new InitialContext();
            datasource = (DataSource)context.lookup("java:comp/env/jdbc/"+DATABASE);
        }
        catch(NamingException e)
        {
            e.printStackTrace();
        }
    }

    public static Fb_memberDAO getFb_memberDAO()
    {
        return dao;
    }

    private Connection getConnection()
    {
        Connection conn = null;
        try
        {
            conn = datasource.getConnection();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    private void close(PreparedStatement pstmt, Connection conn)
    {
        try
        {
            if(pstmt != null)
                pstmt.close();
            if(conn != null)
                conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
    {
        try
        {
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            if(conn != null)
                conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void signupOK(Fb_memberDTO dto)
    {
        Connection conn;
        PreparedStatement pstmt;
        String sql;
        conn = getConnection();
        pstmt = null;
        sql = "insert into "+TABLE_NAME+"(m_id_pk, m_lastName, m_name, m_pw, m_email, m_birthYear, m_birthMonth, m_birthDay, m_gender, m_image) values(?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getM_id_pk());
            pstmt.setString(2, dto.getM_lastName());
            pstmt.setString(3, dto.getM_name());
            pstmt.setString(4, dto.getM_pw());
            pstmt.setString(5, dto.getM_email());
            pstmt.setString(6, dto.getM_birthYear());
            pstmt.setString(7, dto.getM_birthMonth());
            pstmt.setString(8, dto.getM_birthDay());
            pstmt.setString(9, dto.getM_gender());
            pstmt.setString(10, BASE_IMAGE);
            result = pstmt.executeUpdate();
            if(result <= 0) {
            	System.out.println("signupOK Error");
            }else {
            	createProfile(dto.getM_id_pk());            	
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(pstmt, conn);
        }
    }
    private void createProfile(String id) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "insert into "+TABLE_NAME_PROFILE+"(p_id_fk) values(?)";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("createProfile Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    	
    }
    
    public void deleteAccount(String id) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "delete from "+TABLE_NAME+" where m_id_pk=?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteAccount Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }

    public boolean idCheckOK(String newId)
    {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        String sql;
        boolean flag;
        conn = getConnection();
        pstmt = null;
        rs = null;
        sql = "select m_id_pk from "+TABLE_NAME+" where m_id_pk=?";
        flag = true;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newId);
            rs = pstmt.executeQuery();
            if(rs.next() && rs.getString("m_id_pk") != null && rs.getString("m_id_pk").equals(newId))
                flag = false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return flag;
    }

    public boolean loginOK(Fb_memberDTO dto)
    {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select m_pw from "+TABLE_NAME+" where m_id_pk=?";
        boolean flag = false;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getM_id_pk());
            rs = pstmt.executeQuery();
            if(rs.next() && rs.getString("m_pw") != null && rs.getString("m_pw").matches(dto.getM_pw()))
                flag = true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return flag;
    }

    public Fb_memberDTO findAccountByEmail(String id)
    {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        Fb_memberDTO dto;
        String sql;
        conn = getConnection();
        pstmt = null;
        rs = null;
        dto = new Fb_memberDTO();
        sql = "select m_email from "+TABLE_NAME+" where m_id_pk=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
                dto.setM_email(rs.getString("m_email"));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return dto;
    }

    public void findAccountChangePwOK(Fb_memberDTO dto)
    {
        Connection conn;
        PreparedStatement pstmt;
        String sql;
        conn = getConnection();
        pstmt = null;
        sql = "update "+TABLE_NAME+" set m_pw=? where m_id_pk=?";
        int result = 0;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getM_pw());
            pstmt.setString(2, dto.getM_id_pk());
            result = pstmt.executeUpdate();
            if(result <= 0)
                System.out.println("findAccountChangePwOK Error");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(pstmt, conn);
        }
    }

    public HashMap<String, Fb_memberDTO> pickMemberById(ArrayList<Fb_boardDTO> list)
    {
        HashMap<String, Fb_memberDTO> memberHM = new HashMap<>();
        String id = null;
        for(int i = 0; i < list.size(); i++)
        {
            Fb_boardDTO dto = (Fb_boardDTO)list.get(i);
            id = dto.getB_id_fk();
            if(!memberHM.containsKey(id))
                memberHM.put(id, getNameImageById(id));
        }

        return memberHM;
    }
    
    public HashMap<String, Fb_memberDTO> pickReplyMemberByNo(int no){
    	Fb_boardDAO bdao = Fb_boardDAO.getFb_boardDAO();
    	HashMap<String, Fb_memberDTO> memberHM = new HashMap<>();
    	ArrayList<Fb_boardDTO> blist = bdao.selectReplyListByNo(no);
    	String id = null;
    	for(int j = 0; j < blist.size(); j++)
           {
               Fb_boardDTO dto = (Fb_boardDTO)blist.get(j);
               id = dto.getB_id_fk();
               if(!memberHM.containsKey(id))
                   memberHM.put(id, getNameImageById(id));
           }
    	return memberHM;
    }
    
    public HashMap<String, Fb_memberDTO> pickFriendById(ArrayList<Fb_friendsDTO> list)
    {
    	HashMap<String, Fb_memberDTO> memberHM = new HashMap<>();
    	String id = null;
    	for(int i = 0; i < list.size(); i++)
    	{
    		Fb_friendsDTO dto = (Fb_friendsDTO)list.get(i);
    		id = dto.getFr_friendId();
    		if(!memberHM.containsKey(id))
    			memberHM.put(id, getNameImageById(id));
    	}
    	
    	return memberHM;
    }
    public HashMap<String, Fb_memberDTO> pickAskFriendById(ArrayList<Fb_friendsDTO> list)
    {
    	HashMap<String, Fb_memberDTO> memberHM = new HashMap<>();
    	String id = null;
    	for(int i = 0; i < list.size(); i++)
    	{
    		Fb_friendsDTO dto = (Fb_friendsDTO)list.get(i);
    		id = dto.getFr_id_fk();
    		if(!memberHM.containsKey(id))
    			memberHM.put(id, getNameImageById(id));
    	}
    	
    	return memberHM;
    }

    private Fb_memberDTO getNameImageById(String id)
    {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        Fb_memberDTO dto;
        String sql;
        conn = getConnection();
        pstmt = null;
        rs = null;
        dto = new Fb_memberDTO();
        sql = "select m_lastName, m_name, m_image from "+TABLE_NAME+" where m_id_pk=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                dto.setM_lastName(rs.getString("m_lastName"));
                dto.setM_name(rs.getString("m_name"));
                dto.setM_image(rs.getString("m_image"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return dto;
    }

    public Fb_memberDTO selectOneById(String id)
    {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        Fb_memberDTO dto;
        String sql;
        conn = getConnection();
        pstmt = null;
        rs = null;
        dto = new Fb_memberDTO();
        sql = "select * from "+TABLE_NAME+" where m_id_pk=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            	dto.setM_id_pk(rs.getString("m_id_pk"));
                dto.setM_lastName(rs.getString("m_lastName"));
                dto.setM_name(rs.getString("m_name"));
                dto.setM_email(rs.getString("m_email"));
                dto.setM_birthYear(rs.getString("m_birthYear"));
                dto.setM_birthMonth(rs.getString("m_birthMonth"));
                dto.setM_birthDay(rs.getString("m_birthDay"));
                dto.setM_gender(rs.getString("m_gender"));
                dto.setM_createDate(rs.getString("m_createDate"));
                dto.setM_image(rs.getString("m_image"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return dto;
    }
    public void myProfileUpdate(Fb_memberDTO dto) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "update "+TABLE_NAME+" set m_lastName=?, m_name=?, m_birthYear=?, m_birthMonth=?, m_birthDay=?, m_gender=?, m_image=? where m_id_pk=?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getM_lastName());
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_birthYear());
			pstmt.setString(4, dto.getM_birthMonth());
			pstmt.setString(5, dto.getM_birthDay());
			pstmt.setString(6, dto.getM_gender());
			pstmt.setString(7, dto.getM_image());
			pstmt.setString(8, dto.getM_id_pk());
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("myProfileUpdate Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    
    public ArrayList<Fb_memberDTO> searchMemberList(String searchTag){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Fb_memberDTO> list = new ArrayList<>();
    	String sql = "select m_id_pk, m_lastName, m_name, m_image from "+TABLE_NAME+" where m_id_pk like ? or m_lastName like ? "
    			+ "or m_name like ? or m_email like ?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTag+"%");
			pstmt.setString(2, "%"+searchTag+"%");
			pstmt.setString(3, "%"+searchTag+"%");
			pstmt.setString(4, "%"+searchTag+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_memberDTO dto = new Fb_memberDTO();
				dto.setM_id_pk(rs.getString("m_id_pk"));
				dto.setM_lastName(rs.getString("m_lastName"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_image(rs.getString("m_image"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return list;
    }
    
    public ArrayList<Fb_memberDTO> recommandMemberListById(String id){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Fb_memberDTO> list = new ArrayList<>();
    	String sql = "select b_id_fk from "+TABLE_NAME_BOARD+" where b_id_fk not in "
    			+ "(select fr_friendId from "+TABLE_NAME_FRIENDS+" where fr_id_fk=? and fr_reject=1) and b_no_pk in "
    			+ "(select bt_no_fk from "+TABLE_NAME_BOARD_TAG+" where bt_no_fk not in "
    			+ "(select b_no_pk from "+TABLE_NAME_BOARD+" where b_id_fk=? or b_id_fk in "
    			+ "(select fr_friendId from "+TABLE_NAME_FRIENDS+" where fr_id_fk=? ) ) and bt_tagId_fk in "
    			+ "(select bt_tagId_fk from "+TABLE_NAME_BOARD_TAG+" where bt_no_fk in "
    			+ "(select b_no_pk from "+TABLE_NAME_BOARD+" where b_id_fk=? ) group by bt_tagId_fk order by count(bt_no_fk) desc ) "
    			+ "group by bt_no_fk order by count(bt_no_fk) desc) group by b_id_fk order by count(b_id_fk) desc limit 10";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(selectOneById(rs.getString("b_id_fk")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return list;
    }
    
    public ArrayList<Fb_memberDTO> ifyouknowMemberListById(String id){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Fb_memberDTO> list = new ArrayList<>();
    	String sql = "select fr_friendId from "+TABLE_NAME_FRIENDS+" where fr_id_fk in "
    			+ "(select fr_friendId from "+TABLE_NAME_FRIENDS+" where fr_id_fk = ? and fr_confirm = 1) and fr_confirm=1 and "
    			+ "not fr_friendId= ? and fr_friendId not in "
    			+ "(select fr_friendId from "+TABLE_NAME_FRIENDS+" where fr_id_fk = ? and (fr_confirm = 1 or fr_ask = 1 or fr_reject = 1) ) "
    			+ "group by fr_friendId order by count(fr_friendId) desc limit 10";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			System.out.println("ifyouknowMemberListById rs.next 전"); 
			while(rs.next()) {
				list.add(selectOneById(rs.getString("fr_friendId")));
				System.out.println("list.add(selectOneById(rs.getString(\"fr_friendId\"))): "+rs.getString("fr_friendId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return list;
    }
    
  
    
}
