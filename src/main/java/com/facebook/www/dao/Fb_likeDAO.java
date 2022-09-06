package com.facebook.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.facebook.www.command.Command;
import com.facebook.www.dto.Fb_boardDTO;

public class Fb_likeDAO {
	private static Fb_likeDAO dao = new Fb_likeDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_like";
	private final String TABLE_NAME_BOARD = "fb_board";

    private Fb_likeDAO()
    {
        try{
            Context context = new InitialContext();
            datasource = (DataSource)context.lookup("java:comp/env/jdbc/"+DATABASE);
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
    public static Fb_likeDAO getFb_likeDAO()
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
    
    public int checkNPushReturnLike(int no, String id) {
    	int likeNo = getIsLikeNo(no, id);
    	if(likeNo==0) {
    		pushLike(no, id);
    	}else {
    		pullLike(no, id);
    	}
    	int like = getCountLike(no);
    	return like;
    }
    
    public int getCountLike(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int like = 0;
    	String sql = "select count(li_no_fk) from "+TABLE_NAME+" where li_no_fk = ?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				like = rs.getInt("count(li_no_fk)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return like;
    }
    
    public void pushLike(int no, String id) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "insert into "+TABLE_NAME+" values(?,?)";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("pushLike Error");
			}else {
				updateLike(no);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    public void pullLike(int no, String id) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "delete from "+TABLE_NAME+" where li_no_fk=? and li_id_fk=?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("pullLike Error");
			}else {
				updateLike(no);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    private void updateLike(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "update "+TABLE_NAME_BOARD+" set b_like=(select count(li_no_fk) from "+TABLE_NAME+" where li_no_fk=?) where b_no_pk = ?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("updateLike Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    
    public ArrayList<Integer> pickIsLikeByNo(ArrayList<Fb_boardDTO> blist, String userID){
    	ArrayList<Integer> list = new ArrayList<>();
    	for(int i=0;i<blist.size();i++) {
    		int likeNo = getIsLikeNo(blist.get(i).getB_no_pk(), userID);
    		if(likeNo!=0) {
    			list.add(likeNo);
    		}
    	}
    	return list;
    }
    private int getIsLikeNo(int no, String id) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int likeNo = 0;
    	String sql = "select li_no_fk from "+TABLE_NAME+" where li_no_fk = ? and li_id_fk = ?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				likeNo = rs.getInt("li_no_fk");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return likeNo;
    }
    
    public void deleteAllLikeByNo(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	String sql = "delete from "+TABLE_NAME+" where li_no_fk=?";
    	int result = 0;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteAllLikeByNo Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }// 아직 사용안함 => DB에서 fr키로 다 삭제 가능? (board)
}
