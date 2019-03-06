package com.douzone.jblog.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserDao {

	
	public boolean insert(UserVo vo) /*throws UserDaoException*/ {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count;
		
		try {
			conn = getConnection();	
			 
			String sql="insert into user values(null, ?,?,?,now());";
			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			
			count = pstmt.executeUpdate();
			result = count == 1;			

			if(result) {
				sql = "select last_insert_id()";
				pstmt = conn.prepareStatement(sql);						
				ResultSet rs= pstmt.executeQuery();
				rs.next();
				long no = rs.getLong(1);
				
				sql="insert into blog values(?,?,'/assets/images/default.png')";
				pstmt = conn.prepareCall(sql);
				pstmt.setLong(1,no);
				pstmt.setString(2, vo.getName() + "님의 블로그 입니다.");
				
				count = pstmt.executeUpdate();
				
				result = count == 1;

				if(result) {
					sql = "insert into category values(null, '미분류','카테고리를 추가해 주세요',now(),?)";
					pstmt = conn.prepareCall(sql);
					pstmt.setLong(1,no);
					count = pstmt.executeUpdate();
					
					sql = "select last_insert_id()";
					pstmt = conn.prepareStatement(sql);						
					rs= pstmt.executeQuery();
					rs.next();
					no = rs.getLong(1);
					
					sql = "insert into post values(null, '아직 글이없습니다.','글을 작성해주세요',now(),?);";
					pstmt = conn.prepareCall(sql);
					pstmt.setLong(1,no);
					count = pstmt.executeUpdate();
					
					result = count == 1;					
				}
				
				
			}
			
			
			
		}  catch (SQLException e) {
//			throw new UserDaoException("회원정보 저장 실패");
			System.out.println("error:" + e);
		} finally {
			try {
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
	
	
	public UserVo get(UserVo vo) {
		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			 conn = getConnection();	
			 
			String sql="select no,id,name"
					+ " from user "
					+ " where id=? and password=?";
										//이름,패스워드,글,날짜
			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();		
			
			if(rs.next()) {				
			long no = rs.getLong(1);
			String id = rs.getString(2);
			String name = rs.getString(3);
			
			result = new UserVo();
			result.setNo(no);
			result.setId(id);
			result.setName(name);	
			System.out.println(result.toString());
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
	
	
	
	   // 커넥트함수
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
