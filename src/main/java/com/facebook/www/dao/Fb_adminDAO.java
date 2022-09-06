package com.facebook.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.facebook.www.dto.Fb_adminDTO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_memberDTO;

public class Fb_adminDAO {
	private static Fb_adminDAO dao = new Fb_adminDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_admin";
	private final String TABLE_NAME_MEMBER = "fb_member";
	private final String TABLE_NAME_BOARD = "fb_board";

    private Fb_adminDAO()
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

    public static Fb_adminDAO getFb_adminDAO()
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
    
    public boolean adminLoginchk(Fb_adminDTO dto) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	boolean flag = false;
    	String sql = "select a_pw from "+TABLE_NAME+" where a_id_pk=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getA_id_pk());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getA_pw()!=null && rs.getString("a_pw").equals(dto.getA_pw())) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return flag;
    }
    
    public int[][] boardCountChart(int start, int end, int month, int year){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int totalDay = end - start + 1;
    	int[][] countArr = new int[totalDay][2];
    	String startDay = year+"-"+month+"-"+start;
    	String endDay = year+"-"+month+"-"+end;
    	ArrayList<String> dateList = new ArrayList<>();
    	String sql = "select date_format(b_wtime, '%y-%m-%d') as wtime from "+TABLE_NAME_BOARD+" where date(b_wtime) between ? and ?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dateList.add(rs.getString("wtime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	
    	int cnt = 0;
    	int arrCnt = 0;
    	for(int i=start;i<=end;i++) {
    		int chkDay = i;
    		String monthstr = ""+month;
    		if(month<10) monthstr = "0"+month;
    		String chkDaystr = ""+chkDay;
    		if(chkDay<10) chkDaystr = "0"+chkDay;
    		cnt = Collections.frequency(dateList, year+"-"+monthstr+"-"+chkDaystr);
    		countArr[arrCnt][0]= chkDay;
    		countArr[arrCnt][1]= cnt;
    		arrCnt++;
    	}
    	return countArr;
    }
    
    public int[][] memberCountChart(int start, int end, int month, int year){
    	int totalDay = end - start + 1;
    	int[][] countArr = new int[totalDay][5];
    	int cnt = 0;
    	for(int i=start;i<=end;i++) {
    		countArr[cnt][0] = i;
    		countArr[cnt][2] = getGenderCountOneDay(i, month, year, "여자");
    		countArr[cnt][3] = getGenderCountOneDay(i, month, year, "남자");
    		countArr[cnt][4] = getGenderCountOneDay(i, month, year, "미지정");
    		countArr[cnt][1] = countArr[cnt][2] + countArr[cnt][3] + countArr[cnt][4];
    		cnt++;
    	}
    	return countArr;
    }
    private int getGenderCountOneDay(int day, int month, int year, String gender){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int getCount = 0;
    	String oneDay = year+"-"+month+"-"+day;
    	String sql = "select count(m_createDate) as cnt from "+TABLE_NAME_MEMBER+" where date(m_createDate) = ? and m_gender = ?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oneDay);
			pstmt.setString(2, gender);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				getCount = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return getCount;
    }
    
    public String[][] genderCountChart(){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String[][] genderArr = new String[3][2];
    	String sql = "select count(case when m_gender='남자' then 1 end) as male, "
    			+ "count(case when m_gender='여자' then 1 end) as female, "
    			+ "count(case when m_gender='미지정' then 1 end) as nothing from "+TABLE_NAME_MEMBER;
    	try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				genderArr[0][0] = "여자";
				genderArr[0][1] = ""+rs.getInt("female");
				genderArr[1][0] = "남자";
				genderArr[1][1] = ""+rs.getInt("male");
				genderArr[2][0] = "미지정";
				genderArr[2][1] = ""+rs.getInt("nothing");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return genderArr;
    }
    
    public String[][] ageCountChart(){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	LocalDate date = LocalDate.now();
    	int yearNow = date.getYear();
    	String[][] ageArr = new String[9][2];
    	String sql = "select count(case when (? - m_birthYear) >= 0 and (? - m_birthYear) <10 then 1 end) as baby, "
    			+ "count(case when (? - m_birthYear) >= 10 and (? - m_birthYear) < 20 then 1 end) as ten, "
    			+ "count(case when (? - m_birthYear) >= 20 and (? - m_birthYear) < 30 then 1 end) as two, "
    			+ "count(case when (? - m_birthYear) >= 30 and (? - m_birthYear) < 40 then 1 end) as thir, "
    			+ "count(case when (? - m_birthYear) >= 40 and (? - m_birthYear) < 50 then 1 end) as fort, "
    			+ "count(case when (? - m_birthYear) >= 50 and (? - m_birthYear) < 60 then 1 end) as fift, "
    			+ "count(case when (? - m_birthYear) >= 60 and (? - m_birthYear) < 70 then 1 end) as sixt, "
    			+ "count(case when (? - m_birthYear) >= 70 and (? - m_birthYear) < 80 then 1 end) as seve, "
    			+ "count(case when (? - m_birthYear) >= 80 then 1 end) as eigh from "+TABLE_NAME_MEMBER;
    	try {
			pstmt = conn.prepareStatement(sql);
			for(int i=1;i<=17;i++) {
				pstmt.setInt(i, yearNow);				
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ageArr[0][0] = "유아";
				ageArr[0][1] = ""+rs.getInt("baby");
				ageArr[1][0] = "10대";
				ageArr[1][1] = ""+rs.getInt("ten");
				ageArr[2][0] = "20대";
				ageArr[2][1] = ""+rs.getInt("two");
				ageArr[3][0] = "30대";
				ageArr[3][1] = ""+rs.getInt("thir");
				ageArr[4][0] = "40대";
				ageArr[4][1] = ""+rs.getInt("fort");
				ageArr[5][0] = "50대";
				ageArr[5][1] = ""+rs.getInt("fift");
				ageArr[6][0] = "60대";
				ageArr[6][1] = ""+rs.getInt("sixt");
				ageArr[7][0] = "70대";
				ageArr[7][1] = ""+rs.getInt("seve");
				ageArr[8][0] = "80대 이상";
				ageArr[8][1] = ""+rs.getInt("eigh");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return ageArr;
    }
    
    public int totalMemberCount() { // 미사용 중
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int cnt = 0;
    	String sql = "select count(*) from "+TABLE_NAME_MEMBER;
    	try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return cnt;
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
        sql = "select * from "+TABLE_NAME_MEMBER+" where m_id_pk=?";
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
    
    public ArrayList<Fb_boardDTO> selectBoardById(String id, String searchText, int pageStart, int contentAmount){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Fb_boardDTO> list = new ArrayList<>();
    	String sql = "select b_no_pk, b_wtime, b_id_fk, b_content, b_image, b_groupNum, b_stepNum, b_indentNum, b_like from "+TABLE_NAME_BOARD+" "
    			+ "where b_id_fk=? and b_content like ? order by b_wtime desc limit ?, ?";
    	if(searchText==null) searchText="";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%"+searchText+"%");
			pstmt.setInt(3, pageStart);
			pstmt.setInt(4, contentAmount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(rs.getInt("b_no_pk"));
				dto.setB_wtime(rs.getString("b_wtime"));
				dto.setB_id_fk(rs.getString("b_id_fk"));
				dto.setB_content(rs.getString("b_content"));
				dto.setB_image(rs.getString("b_image"));
				dto.setB_groupNum(rs.getInt("b_groupNum"));
				dto.setB_stepNum(rs.getInt("b_stepNum"));
				dto.setB_indentNum(rs.getInt("b_indentNum"));
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
    
    public int countSelectBoardById(String id, String searchText) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int count = 0;
    	String sql = "select count(*) from "+TABLE_NAME_BOARD+" where b_id_fk=? and b_content like ? ";
    	if(searchText==null) searchText="";
    	try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, id);
			pstmt.setString(2, "%"+searchText+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return count;
    }
    
    public ArrayList<Fb_memberDTO> searchMember(String searchText, int pageStart, int contentAmount){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<Fb_memberDTO> list = new ArrayList<>();
    	String sql = "select m_id_pk, m_lastName, m_name, m_email, m_birthYear, m_birthMonth, m_birthDay, m_gender, m_createDate from "+TABLE_NAME_MEMBER+" where m_id_pk like ? order by m_createDate desc limit ?, ?";
    	if(searchText==null) searchText="";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setInt(2, pageStart);
			pstmt.setInt(3, contentAmount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_memberDTO dto = new Fb_memberDTO();
				dto.setM_id_pk(rs.getString("m_id_pk"));
				dto.setM_lastName(rs.getString("m_lastName"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_email(rs.getString("m_email"));
				dto.setM_birthYear(rs.getString("m_birthYear"));
				dto.setM_birthMonth(rs.getString("m_birthMonth"));
				dto.setM_birthDay(rs.getString("m_birthDay"));
				dto.setM_gender(rs.getString("m_gender"));
				dto.setM_createDate(rs.getString("m_createDate"));
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
    
    public int countSearchMember(String searchText) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int count = 0;
    	String sql = "select count(*) from "+TABLE_NAME_MEMBER+" where m_id_pk like ? ";
    	if(searchText==null) searchText="";
    	try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, "%"+searchText+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return count;
    }
    
    public ArrayList<Fb_boardDTO> searchBoard(int start, int end, int month, int year, String searchText, int pageStart, int contentAmount){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String startDay = year+"-"+month+"-"+start;
    	String endDay = year+"-"+month+"-"+end;
    	ArrayList<Fb_boardDTO> list = new ArrayList<>();
    	String sql = "select b_no_pk, b_wtime, b_id_fk, b_content, b_groupNum, b_stepNum, b_indentNum, b_like from "+TABLE_NAME_BOARD+" where date(b_wtime) between ? and ? and b_content like ? order by b_wtime desc limit ?, ?";
    	if(searchText==null) searchText="";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, "%"+searchText+"%");
			pstmt.setInt(4, pageStart);
			pstmt.setInt(5, contentAmount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(rs.getInt("b_no_pk"));
				dto.setB_wtime(rs.getString("b_wtime"));
				dto.setB_id_fk(rs.getString("b_id_fk"));
				dto.setB_content(rs.getString("b_content"));
				dto.setB_groupNum(rs.getInt("b_groupNum"));
				dto.setB_stepNum(rs.getInt("b_stepNum"));
				dto.setB_indentNum(rs.getInt("b_indentNum"));
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
    
    public int countSearchBoard(int start, int end, int month, int year, String searchText) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String startDay = year+"-"+month+"-"+start;
    	String endDay = year+"-"+month+"-"+end;
    	int count = 0;
    	String sql = "select count(*) from "+TABLE_NAME_BOARD+" where date(b_wtime) between ? and ? and b_content like ? ";
    	if(searchText==null) searchText="";
    	try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, "%"+searchText+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return count;
    }
    
    public ArrayList<Fb_boardDTO> photoBoard(int start, int end, int month, int year, int pageStart, int contentAmount){
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String startDay = year+"-"+month+"-"+start;
    	String endDay = year+"-"+month+"-"+end;
    	ArrayList<Fb_boardDTO> list = new ArrayList<>();
    	String sql = "select b_no_pk, b_wtime, b_id_fk, b_content, b_image, b_groupNum, b_stepNum, b_indentNum, b_like from "+TABLE_NAME_BOARD+" "
    			+ "where date(b_wtime) between ? and ? and b_image is not null order by b_wtime desc limit ?, ?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setInt(3, pageStart);
			pstmt.setInt(4, contentAmount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Fb_boardDTO dto = new Fb_boardDTO();
				dto.setB_no_pk(rs.getInt("b_no_pk"));
				dto.setB_wtime(rs.getString("b_wtime"));
				dto.setB_id_fk(rs.getString("b_id_fk"));
				dto.setB_content(rs.getString("b_content"));
				dto.setB_image(rs.getString("b_image"));
				dto.setB_groupNum(rs.getInt("b_groupNum"));
				dto.setB_stepNum(rs.getInt("b_stepNum"));
				dto.setB_indentNum(rs.getInt("b_indentNum"));
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
    
    public int countPhotoBoard(int start, int end, int month, int year) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String startDay = year+"-"+month+"-"+start;
    	String endDay = year+"-"+month+"-"+end;
    	int count = 0;
    	String sql = "select count(*) from "+TABLE_NAME_BOARD+" where date(b_wtime) between ? and ? and b_image is not null";
    	try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return count;
    }
    
    public void deleteListBoard(int[] arr) {
    	for(int i=0;i<arr.length;i++) {
    		deleteOneBoard(arr[i]);
    	}
    }
    private void deleteOneBoard(int no) {
    	int indentNum = getIndentNum(no);
    	if(indentNum==0) {
    		deleteIndentZero(no);
    	}else if(indentNum==1) {
    		deleteIndentOne(no);
    	}else if(indentNum==2) {
    		deleteIndentTwo(no);
    	}else {
    		System.out.println("deleteOneBoard Error");
    	}
    }
    private int getIndentNum(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int indentNum = 0;
    	String sql = "select b_indentNum from "+TABLE_NAME_BOARD+" where b_no_pk=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				indentNum = rs.getInt("b_indentNum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, pstmt, conn);
		}
    	return indentNum;
    }
    private void deleteIndentZero(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	int result = 0;
    	String sql = "delete from "+TABLE_NAME_BOARD+" where b_groupNum=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteIndentZero Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    private void deleteIndentOne(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	int result = 0;
    	String sql = "delete from "+TABLE_NAME_BOARD+" where b_no_pk=? or b_stepNum=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteIndentOne Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    private void deleteIndentTwo(int no) {
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	int result = 0;
    	String sql = "delete from "+TABLE_NAME_BOARD+" where b_no_pk=?";
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("deleteIndentTwo Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
    }
    
    
    
    
    
}
