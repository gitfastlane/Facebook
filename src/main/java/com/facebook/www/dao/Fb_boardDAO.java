// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Fb_boardDAO.java

package com.facebook.www.dao;

import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_tagDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.*;
import javax.sql.DataSource;

public class Fb_boardDAO
{
	private static Fb_boardDAO dao = new Fb_boardDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_board";
	private final String TABLE_NAME_MEMBER = "fb_member";
	private final String TABLE_NAME_FRIENDS = "fb_friends";
	private final String TABLE_NAME_TAG = "fb_tag";
	private final String TABLE_NAME_BOARD_TAG = "fb_board_tag";

    private Fb_boardDAO()
    {
        try{
            Context context = new InitialContext();
            datasource = (DataSource)context.lookup("java:comp/env/jdbc/"+DATABASE);
        }catch(NamingException e){
            e.printStackTrace();
        }
    }

    public static Fb_boardDAO getFb_boardDAO()
    {
        return dao;
    }

    private Connection getConnection()
    {
        Connection conn = null;
        try{
            conn = datasource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    private void close(PreparedStatement pstmt, Connection conn){
        try{
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
        try{
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void writePostOK(Fb_boardDTO dto, String hashTag){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into "+TABLE_NAME+"(b_id_fk, b_content, b_image) values(?,?,?)";
        int result = 0;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getB_id_fk());
            pstmt.setString(2, dto.getB_content());
            pstmt.setString(3, dto.getB_image());
            result = pstmt.executeUpdate();
            if(result <= 0)
                System.out.println("writePostOK Error");
            int groupNum = findGroupNum(dto);
            setGroupNum(groupNum);
            if(hashTag!=null)setHashTag(groupNum, hashTag);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(pstmt, conn);
        }
    }
    private int findGroupNum(Fb_boardDTO dto){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select max(b_no_pk) from "+TABLE_NAME+" where b_id_fk=?";
        int groupNum = 0;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getB_id_fk());
            rs = pstmt.executeQuery();
            if(rs.next())
                groupNum = rs.getInt(1);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return groupNum;
    }
    private void setGroupNum(int groupNum){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "update "+TABLE_NAME+" set b_groupNum=? where b_no_pk=?";
        int result = 0;
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupNum);
            pstmt.setInt(2, groupNum);
            result = pstmt.executeUpdate();
            if(result <= 0)
                System.out.println("setGroupNum Error");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(pstmt, conn);
        }
    }
    private void setHashTag(int no, String hashTag) {
    	Fb_tagDAO dao = Fb_tagDAO.getFb_tagDAO();
    	ArrayList<Integer> list = dao.getTagId(hashTag);
    	for(int i=0;i<list.size();i++) {
    		Connection conn = getConnection();
        	PreparedStatement pstmt = null;
        	String sql = "insert into "+TABLE_NAME_BOARD_TAG+"(bt_no_fk, bt_tagId_fk) values(?,?)";
        	int result = 0;
        	try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setInt(2, list.get(i));
				result = pstmt.executeUpdate();
				if(result<=0)System.out.println("setHashTag Error");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt, conn);
			}
    	}
    }
    
    public void updatePostOK(Fb_boardDTO dto, String hashTag) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "update "+TABLE_NAME+" set b_content=?, b_image=? where b_no_pk=?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getB_content());
			pstmt.setString(2, dto.getB_image());
			pstmt.setInt(3, dto.getB_no_pk());
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("updatePostOK Error");
			}else {
				deleteHashTag(dto.getB_no_pk());
				setHashTag(dto.getB_no_pk(), hashTag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    private void deleteHashTag(int bt_no_fk) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "delete from "+TABLE_NAME_BOARD_TAG+" where bt_no_fk=?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bt_no_fk);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteHashTag Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    
    public Fb_boardDTO selectOneByNo(int b_no_pk) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Fb_boardDTO dto = new Fb_boardDTO();
    	String sql = "select b_no_pk, b_id_fk, b_wtime, b_content, b_image, b_like from "+TABLE_NAME+" where b_no_pk=?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, b_no_pk);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			dto.setB_no_pk(rs.getInt("b_no_pk"));
    			dto.setB_id_fk(rs.getString("b_id_fk"));
    			dto.setB_wtime(rs.getString("b_wtime"));
    			dto.setB_content(rs.getString("b_content"));
    			dto.setB_image(rs.getString("b_image"));
    			dto.setB_like(rs.getInt("b_like"));
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally {
    		close(rs, pstmt, conn);
    	}
    	return dto;
    }
    
    public ArrayList<Fb_boardDTO> selectListAddFriendsById(String id){
    	Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Fb_boardDTO> list = new ArrayList<>();
        String sql = "select b_no_pk, b_id_fk, b_wtime, b_content, b_image, b_like from "+TABLE_NAME+" "
        		+ "where b_id_fk=? or b_id_fk in (select fr_friendId from "+TABLE_NAME_FRIENDS+" where fr_id_fk=? and fr_confirm=1) order by b_wtime desc";
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(rs.getInt("b_no_pk"));
                dto.setB_id_fk(rs.getString("b_id_fk"));
                dto.setB_wtime(rs.getString("b_wtime"));
                dto.setB_content(rs.getString("b_content"));
                dto.setB_image(rs.getString("b_image"));
                dto.setB_like(rs.getInt("b_like"));
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
    
    public ArrayList<Fb_boardDTO> selectListById(String id)
    {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Fb_boardDTO> list = new ArrayList<>();
        String sql = "select b_no_pk, b_id_fk, b_wtime, b_content, b_image, b_like from "+TABLE_NAME+" where b_id_fk=? order by b_wtime desc";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	Fb_boardDTO dto = new Fb_boardDTO();
                dto.setB_no_pk(rs.getInt("b_no_pk"));
                dto.setB_id_fk(rs.getString("b_id_fk"));
                dto.setB_wtime(rs.getString("b_wtime"));
                dto.setB_content(rs.getString("b_content"));
                dto.setB_image(rs.getString("b_image"));
                dto.setB_like(rs.getInt("b_like"));
                list.add(dto);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
        	close(rs, pstmt, conn);
        }
        return list;
    }

    public ArrayList<Fb_boardDTO> photoListById(String id){
    	Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Fb_boardDTO> list = new ArrayList<>();
        String sql = "select b_no_pk, b_image, b_wtime from "+TABLE_NAME+" where b_id_fk=? and b_image is not null order by b_wtime desc";
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(rs.getInt("b_no_pk"));
				dto.setB_image(rs.getString("b_image"));
				dto.setB_wtime(rs.getString("b_wtime"));
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
    
    public void deleteListByNo(ArrayList<Fb_boardDTO> list) {
    	for(int i=0;i<list.size();i++) {
    		Fb_boardDTO dto = list.get(i);
    		deleteOneByNo(dto);
    	}
    }
    
    public void deleteOneByNo(Fb_boardDTO dto) {
    	deleteHashTag(dto.getB_no_pk());
    	Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "delete from "+TABLE_NAME+" where b_no_pk=?";
        int result = 0;
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_no_pk());
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteOneByNo Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }

    public ArrayList<Fb_boardDTO> searchBoardByTag(String searchTag){
    	Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Fb_boardDTO> list = new ArrayList<>();
        String sql = "select * from "+TABLE_NAME+" where b_no_pk in "
        		+ "(select bt_no_fk from "+TABLE_NAME_BOARD_TAG+" where bt_tagId_fk = "
        		+ "(select t_tagId_pk from "+TABLE_NAME_TAG+" where t_name = ? )) order by b_wtime desc";
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchTag);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(rs.getInt("b_no_pk"));
				dto.setB_id_fk(rs.getString("b_id_fk"));
				dto.setB_content(rs.getString("b_content"));
				dto.setB_image(rs.getString("b_image"));
				dto.setB_wtime(rs.getString("b_wtime"));
				dto.setB_like(rs.getInt("b_like"));
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
    
    
    
}
