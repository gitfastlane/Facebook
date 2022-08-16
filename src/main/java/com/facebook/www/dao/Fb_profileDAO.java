
package com.facebook.www.dao;

import com.facebook.www.dto.Fb_profileDTO;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class Fb_profileDAO
{
	
	private static Fb_profileDAO dao = new Fb_profileDAO();
	private DataSource datasource;
	private final String DATABASE = "facebook";
	private final String TABLE_NAME = "fb_profile";

    private Fb_profileDAO()
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

    public static Fb_profileDAO getFb_profileDAO()
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

    public Fb_profileDTO selectOneById(String id)
    {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Fb_profileDTO dto = new Fb_profileDTO();
        String sql = "select * from "+TABLE_NAME+" where p_id_fk=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                dto.setP_locationCity(rs.getString("p_locationCity"));
                dto.setP_locationCountry(rs.getString("p_locationCountry"));
                dto.setP_relation1(rs.getString("p_relation1"));
                dto.setP_relationName1(rs.getString("p_relationName1"));
                dto.setP_relation2(rs.getString("p_relation2"));
                dto.setP_relationName2(rs.getString("p_relationName2"));
                dto.setP_relation3(rs.getString("p_relation3"));
                dto.setP_relationName3(rs.getString("p_relationName3"));
                dto.setP_school(rs.getString("p_school"));
                dto.setP_schoolYear1(rs.getString("p_schoolYear1"));
                dto.setP_schoolMonth1(rs.getString("p_schoolMonth1"));
                dto.setP_schoolDay1(rs.getString("p_schoolDay1"));
                dto.setP_schoolYear2(rs.getString("p_schoolYear2"));
                dto.setP_schoolMonth2(rs.getString("p_schoolMonth2"));
                dto.setP_schoolDay2(rs.getString("p_schoolDay2"));
                dto.setP_schoolLocation(rs.getString("p_schoolLocation"));
                dto.setP_schoolName(rs.getString("p_schoolName"));
                dto.setP_workYear1(rs.getString("p_workYear1"));
                dto.setP_workMonth1(rs.getString("p_workMonth1"));
                dto.setP_workDay1(rs.getString("p_workDay1"));
                dto.setP_workYear2(rs.getString("p_workYear2"));
                dto.setP_workMonth2(rs.getString("p_workMonth2"));
                dto.setP_workDay2(rs.getString("p_workDay2"));
                dto.setP_workLocation(rs.getString("p_workLocation"));
                dto.setP_workName(rs.getString("p_workName"));
                dto.setP_workPlace(rs.getString("p_workPlace"));
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
    public void profileUpdate(Fb_profileDTO dto) {
    	Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "update "+TABLE_NAME+" set p_locationCountry=?, p_locationCity=?,"
        		+ "p_school=?, p_schoolName=?, p_schoolYear1=?, p_schoolMonth1=?, p_schoolDay1=?, p_schoolYear2=?, p_schoolMonth2=?, p_schoolDay2=?, p_schoolLocation=?, "
        		+ "p_workName=?, p_workPlace=?, p_workYear1=?, p_workMonth1=?, p_workDay1=?, p_workYear2=?, p_workMonth2=?, p_workDay2=?, p_workLocation=?, "
        		+ "p_relation1=?, p_relation2=?, p_relation3=?, "
        		+ "p_relationName1=?, p_relationName2=?, p_relationName3=? where p_id_fk=? ";
        int result = 0;
        try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getP_locationCountry());
			pstmt.setString(2, dto.getP_locationCity());
			pstmt.setString(3, dto.getP_school());
			pstmt.setString(4, dto.getP_schoolName());
			pstmt.setString(5, dto.getP_schoolYear1());
			pstmt.setString(6, dto.getP_schoolMonth1());
			pstmt.setString(7, dto.getP_schoolDay1());
			pstmt.setString(8, dto.getP_schoolYear2());
			pstmt.setString(9, dto.getP_schoolMonth2());
			pstmt.setString(10, dto.getP_schoolDay2());
			pstmt.setString(11, dto.getP_schoolLocation());
			pstmt.setString(12, dto.getP_workName());
			pstmt.setString(13, dto.getP_workPlace());
			pstmt.setString(14, dto.getP_workYear1());
			pstmt.setString(15, dto.getP_workMonth1());
			pstmt.setString(16, dto.getP_workDay1());
			pstmt.setString(17, dto.getP_workYear2());
			pstmt.setString(18, dto.getP_workMonth2());
			pstmt.setString(19, dto.getP_workDay2());
			pstmt.setString(20, dto.getP_workLocation());
			pstmt.setString(21, dto.getP_relation1());
			pstmt.setString(22, dto.getP_relation2());
			pstmt.setString(23, dto.getP_relation3());
			pstmt.setString(24, dto.getP_relationName1());
			pstmt.setString(25, dto.getP_relationName2());
			pstmt.setString(26, dto.getP_relationName3());
			pstmt.setString(27, dto.getP_id_fk());
			result = pstmt.executeUpdate();
			if(result<=0)System.out.println("profileInsert Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		} 
    }

}
