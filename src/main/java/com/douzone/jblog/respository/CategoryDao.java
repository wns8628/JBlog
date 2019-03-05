package com.douzone.jblog.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;


@Repository
public class CategoryDao {
	
	public List<CategoryVo> getList(long uNo){
		
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		
		Connection conn = null;
		ResultSet rs =null;
		PreparedStatement pstmt = null;
		
		try {
			 conn = getConnection();
			 pstmt = null;
			 rs = null;
			
			 
			String sql = "select distinct(b.no), b.name, b.DESCRIPTION , b.REG_DATE, b.USER_NO, (select c.no\r\n" + 
					"															   from post c, category d \r\n" + 
					"															  where c.category_no = d.no\r\n" + 
					"															    and c.category_no = b.no\r\n" + 
					"															    and d.user_no = ? \r\n" + 
					"                                                                order by c.reg_date desc\r\n" + 
					"															    limit 0,1 ) as top_post\r\n" + 
					"  from post a right join category b\r\n" + 
					"	on a.category_no = b.no\r\n" + 
					" WHERE user_no = ? ";
			
			pstmt = conn.prepareCall(sql);			
			pstmt.setLong(1, uNo);
			pstmt.setLong(2, uNo);
			
			rs= pstmt.executeQuery();
			  			
			while(rs.next()) {
				
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				String regDate = rs.getString(4);
				long userNo = rs.getLong(5);
				long topPostNo = rs.getLong(6);
				
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setDescription(description);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setTopPostNo(topPostNo);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
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
