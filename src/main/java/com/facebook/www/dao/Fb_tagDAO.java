package com.facebook.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_tagDTO;

public class Fb_tagDAO {
	private static Fb_tagDAO dao = new Fb_tagDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_tag";
	private final String TABLE_NAME_BOARD_TAG = "fb_board_tag";
	private final String TABLE_NAME_BOARD = "fb_board";

    private Fb_tagDAO()
    {
        try{
            Context context = new InitialContext();
            datasource = (DataSource)context.lookup("java:comp/env/jdbc/"+DATABASE);
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
    public static Fb_tagDAO getFb_tagDAO()
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
    //#apple
    //#apple#banana
    public ArrayList<Integer> getTagId(String hashTag) {
    	ArrayList<String> list = new ArrayList<>();
    	ArrayList<Integer> idList = new ArrayList<>();
    	hashTag = hashTag.trim();
    	if(hashTag!=null&&!hashTag.isEmpty()) {
    		int start = 0;
    		while(true) {
    			if(hashTag.indexOf("#", start+1)>0) {
    				String tag = hashTag.substring(start, hashTag.indexOf("#", start+1));    
    				list.add(tag);
    				start = hashTag.indexOf("#", start+1);
    			}else {
    				String tag = hashTag.substring(start);
    				list.add(tag);
    				break;
    			}
    		}
    		createTagId(list);
    		for(int i=0;i<list.size();i++) {
    			Connection conn = getConnection();
    			PreparedStatement pstmt = null;
    			ResultSet rs = null;
    			String sql = "select t_tagId_pk from "+TABLE_NAME+" where t_name=?";
    			try {
    				pstmt = conn.prepareStatement(sql);
    				pstmt.setString(1, list.get(i));
    				rs = pstmt.executeQuery();
    				if(rs.next()) {
    					idList.add(rs.getInt("t_tagId_pk"));
    				}
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}finally {
    				close(rs, pstmt, conn);
    			}
    		}	
    	}
    	return idList;
    }
    private void createTagId(ArrayList<String> list) {
    	for(int i=0;i<list.size();i++) {
    		if(!checkTagName(list.get(i))) {
    			Connection conn = getConnection();
    	    	PreparedStatement pstmt = null;
    	    	String sql = "insert into "+TABLE_NAME+"(t_name) values(?)";
    	    	int result = 0;
    	    	try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, list.get(i));
					result = pstmt.executeUpdate();
					if(result<=0)System.out.println("createTagId Error");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(pstmt, conn);
				}
    		}
    	}
    }
    private boolean checkTagName(String tagName) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sql = "select t_tagId_pk from "+TABLE_NAME+" where t_name=?";
    	boolean flag = false;
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tagName);
			rs = pstmt.executeQuery();
			if(rs.next()) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return flag;
    }
    
    public HashMap<Integer, String> pickTagFullNameByNo(ArrayList<Fb_boardDTO> list){
    	HashMap<Integer, String> tagHM = new HashMap<>();
    	for(int i=0;i<list.size();i++) {
    		ArrayList<String> tagList = selectTagNameByNo(list.get(i).getB_no_pk());
    		if(tagList!=null) {
    			String tagFullName = "";
    			for(int j=0;j<tagList.size();j++) {
    				tagFullName += tagList.get(j);
    			}
    			tagHM.put(list.get(i).getB_no_pk(), tagFullName);
    		}
    	}
    	return tagHM;
    }
    
    public ArrayList<String> selectTagNameByNo(int bt_no_fk){
    	ArrayList<Integer> idList = printTagIdListByNo(bt_no_fk);
    	ArrayList<String> tagList = new ArrayList<>();
    	for(int i=0;i<idList.size();i++) {
    		Connection conn = getConnection();
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		String sql = "select t_name from "+TABLE_NAME+" where t_tagId_pk=?";
    		try {
    			pstmt = conn.prepareStatement(sql);
    			pstmt.setInt(1, idList.get(i));
    			rs = pstmt.executeQuery();
    			if(rs.next()) {
    				tagList.add(rs.getString("t_name"));
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally {
    			close(rs, pstmt, conn);
    		}
    	}
    	return tagList;
    }
    private ArrayList<Integer> printTagIdListByNo(int bt_no_fk){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Integer> list = new ArrayList<>();
    	String sql = "select bt_tagId_fk from "+TABLE_NAME_BOARD_TAG+" where bt_no_fk=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bt_no_fk);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("bt_tagId_fk"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return list;
    }
    
    public ArrayList<String> topTenTagListById(String id){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Integer> idList = new ArrayList<>();
    	ArrayList<String> tagList = null;
    	String sql = "select bt_tagId_fk, count(bt_no_fk) as cnt from "+TABLE_NAME_BOARD_TAG+" "
    			+ "where bt_no_fk in (select b_no_pk from "+TABLE_NAME_BOARD+" where b_id_fk=? ) "
    			+ "group by bt_tagId_fk order by cnt desc limit 10";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				idList.add(rs.getInt("bt_tagId_fk"));
			}
			tagList = getTagNameListByTagIdList(idList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return tagList;
    }
    
    private ArrayList<String> getTagNameListByTagIdList(ArrayList<Integer> idList){
    	ArrayList<String> list = new ArrayList<>();
    	for(int i=0;i<idList.size();i++) {
    		list.add(getTagNameByTagId(idList.get(i)));
    	}
    	return list;
    }
    private String getTagNameByTagId(int id) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String name = null;
    	String sql = "select t_name from "+TABLE_NAME+" where t_tagId_pk=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("t_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return name;
    }
    
}
