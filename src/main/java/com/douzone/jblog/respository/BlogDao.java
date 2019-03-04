package com.douzone.jblog.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	
	public long get(String name) {
		long result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			conn = getConnection();				 
			String sql= "select no from user where name = ?";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();		
			
			if(rs.next()) {			
				long no = rs.getLong(1);
				result = no;
			}
			
		}  catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public BlogVo get(long no) {
		BlogVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			conn = getConnection();				 
			String sql= "select user_no,title,logo from blog where user_no = ?";
			pstmt = conn.prepareCall(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();		
			
			if(rs.next()) {			
				long userNo = rs.getLong(1);
				String title = rs.getString(2);
				String logo = rs.getString(3);
				
				result = new BlogVo();
				result.setUserNo(userNo);
				result.setTitle(title);
				result.setLogo(logo);			
			}
			
		}  catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private Connection getConnection() throws SQLException {
	      Connection conn = null;
	
	      try {
	         Class.forName("com.mysql.jdbc.Driver"); // 패키지 이름
	
	         String url = "jdbc:mysql://localhost:3306/jblog"; // DB 종류마다 url이 다르다
	         conn = DriverManager.getConnection(url, "jblog", "jblog"); // interface
	      } catch (ClassNotFoundException e) {
	         System.out.println("드라이버 로딩 실패" + e);
	      }
	      return conn;
	}
}
