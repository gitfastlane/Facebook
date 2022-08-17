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

import com.facebook.www.dto.Fb_friendsDTO;

public class Fb_friendsDAO {
	private static Fb_friendsDAO dao = new Fb_friendsDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_friends";
	
	private Fb_friendsDAO() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/"+DATABASE);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Fb_friendsDAO getFb_friendsDAO() {
		return dao;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	private void close(PreparedStatement pstmt, Connection conn) {
		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {			
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Fb_friendsDTO> selectListById(String id){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Fb_friendsDTO> list = new ArrayList<>();
		String sql = "select fr_friendId from "+TABLE_NAME+" where fr_id_fk=? and fr_confirm=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_friendsDTO dto = new Fb_friendsDTO();
				dto.setFr_friendId(rs.getString("fr_friendId"));
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
	
	public ArrayList<Fb_friendsDTO> selectAskListById(String id){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Fb_friendsDTO> list = new ArrayList<>();
		String sql = "select fr_id_fk from "+TABLE_NAME+" where fr_friendId=? and fr_ask=1 and fr_id_fk not in (select fr_friendId from "+TABLE_NAME+" where fr_id_fk=? and fr_reject=1)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_friendsDTO dto = new Fb_friendsDTO();
				dto.setFr_id_fk(rs.getString("fr_id_fk"));
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
	
	public ArrayList<Fb_friendsDTO> selectMyAskListById(String id){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Fb_friendsDTO> list = new ArrayList<>();
		String sql = "select fr_friendId from "+TABLE_NAME+" where fr_id_fk=? and fr_ask=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_friendsDTO dto = new Fb_friendsDTO();
				dto.setFr_friendId(rs.getString("fr_friendId"));
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
	
	public ArrayList<Fb_friendsDTO> selectBlockListById(String id){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Fb_friendsDTO> list = new ArrayList<>();
		String sql = "select fr_friendId from "+TABLE_NAME+" where fr_id_fk=? and fr_reject=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_friendsDTO dto = new Fb_friendsDTO();
				dto.setFr_friendId(rs.getString("fr_friendId"));
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
	
	public ArrayList<String> printFriendIdList(String id){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();
		String sql = "select fr_friendId from "+TABLE_NAME+" where fr_id_fk=? union select fr_id_fk from "+TABLE_NAME+" where fr_friendId =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("fr_friendId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	public void askFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+TABLE_NAME+"(fr_id_fk, fr_friendId, fr_ask) value(?,?,1)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("askFriend Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void confirmFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+TABLE_NAME+"(fr_id_fk, fr_friendId, fr_confirm) values(?,?,1)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("confirmFriend Error");
			}else {
				changeAskToConfirm(userID, friendID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	private void changeAskToConfirm(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update "+TABLE_NAME+" set fr_confirm=1, fr_ask=0 where fr_id_fk=? and fr_friendId=?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friendID);
			pstmt.setString(2, userID);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("changeAskToConfirm Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void rejectFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from "+TABLE_NAME+" where fr_id_fk=? and fr_friendId=? and fr_ask=1";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friendID);
			pstmt.setString(2, userID);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("rejectFriend Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void cancelFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from "+TABLE_NAME+" where fr_id_fk=? and fr_friendId=? and fr_ask=1";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("rejectFriend Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void deleteFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from "+TABLE_NAME+" where fr_id_fk=? and fr_friendId=? and fr_confirm=1";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friendID);
			pstmt.setString(2, userID);
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("rejectFriend Error");
			}else {
				deleteMeAtFriend(userID, friendID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	private void deleteMeAtFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from "+TABLE_NAME+" where fr_id_fk=? and fr_friendId=? and fr_confirm=1";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("rejectFriend Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void blockFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+TABLE_NAME+"(fr_id_fk, fr_friendId, fr_reject) values(?,?,1)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("rejectFriend Error");
			}else {
				rejectFriend(userID, friendID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void removeBlockFriend(String userID, String friendID) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from "+TABLE_NAME+" where fr_id_fk=? and fr_friendId=? and fr_reject=1";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			result = pstmt.executeUpdate();
			if(result<=0) {
				System.out.println("rejectFriend Error");
			}else {
				rejectFriend(userID, friendID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	
	
	
}
