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
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class PostDao {
//	public int getCount(long userNo) {
//
//		int result = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;		
//		
//		try {
//			 conn = getConnection();	
//			 
//			String sql="select count(*)\r\n" + 
//					"     from post a, category b\r\n" + 
//					"	where a.CATEGORY_NO = b.no \r\n" + 
//					"      and b.user_no = ? ";
//								
//			pstmt = conn.prepareCall(sql);
//			pstmt.setLong(1, userNo);
//
//			
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				result = rs.getInt(1);
//			}		
//			 
//		}  catch (SQLException e) {
//			System.out.println( "error: "+e );
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//			} catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public long get(long userNo) {

		long result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			 conn = getConnection();	
			 
			String sql="  select max(a.no) \r\n" + 
					"     from post a, category b\r\n" + 
					"	where a.CATEGORY_NO = b.no \r\n" + 
					"      and b.user_no = ? ";
								
			pstmt = conn.prepareCall(sql);
			pstmt.setLong(1, userNo);

			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getLong(1);
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
	
	public PostVo get(long userNo,long categoryNo, long postNo) {

		PostVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			 conn = getConnection();	
			 
			String sql="select a.no, a.title, a.content, a.reg_date, a.category_no\r\n" + 
					"  from post a, category b \r\n" + 
					" where a.category_no = b.no\r\n" + 
					"   and a.category_no = ? " + 
					"   and b.user_no = ?" + 
					"   and a.no=? ";
								
			pstmt = conn.prepareCall(sql);
			pstmt.setLong(1, categoryNo);
			pstmt.setLong(2, userNo);
			pstmt.setLong(3, postNo);
			
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = new PostVo();
				
				result.setNo(rs.getLong(1));
				result.setTitle(rs.getString(2));
				result.setContent(rs.getString(3));
				result.setRegDate(rs.getString(4));
				result.setCategoryNo(rs.getLong(5));
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
	
//	public PostVo get(long userNo, long postNo) {
//
//		PostVo result = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;		
//		
//		try {
//			 conn = getConnection();	
//			 
//			String sql="select a.no, a.title, a.content, a.reg_date, a.category_no\r\n" + 
//					"  from post a, category b \r\n" + 
//					" where a.category_no = b.no\r\n" + 
//					"   and b.user_no = ?" + 
//					"   and a.no=? ";
//								
//			pstmt = conn.prepareCall(sql);
//			pstmt.setLong(1, userNo);
//			pstmt.setLong(2, postNo);
//			
//			
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				result = new PostVo();
//				
//				result.setNo(rs.getLong(1));
//				result.setTitle(rs.getString(2));
//				result.setContent(rs.getString(3));
//				result.setRegDate(rs.getString(4));
//				result.setCategoryNo(9999);
//			}		
//			 
//		}  catch (SQLException e) {
//			System.out.println( "error: "+e );
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//			} catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public boolean insert(long categoryNo, PostVo vo) /*throws UserDaoException*/ {
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();	
			 
			String sql="insert into post values(null, ?,?,now(),?)";
			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, categoryNo);
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		
				

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
	

public List<PostVo> getList(long userNo,long categoryNo, long postNo){
		
		List<PostVo> list = new ArrayList<PostVo>();
		
		Connection conn = null;
		ResultSet rs =null;
		PreparedStatement pstmt = null;
		
		try {
			 conn = getConnection();
			 pstmt = null;
			 rs = null;
			 
			 String sql = "SET @rownum:=0";			 
			 pstmt = conn.prepareCall(sql);
			 pstmt.executeQuery();
	 
	 		sql = "select indexNo from (select @rownum:=@rownum+1 as indexNo, a.no " + 
			 	  "				  from post a, category b " + 
			 	  "				 where a.category_no = b.no " + 
			 	  "				   and a.category_no = ? " + 
			 	  "				   and b.user_no = ? " + 
			 	  "				   order by a.reg_date desc) a " + 
			 	  "where a.no = ?";
			 
				 pstmt = conn.prepareCall(sql);
			  	 pstmt.setLong(1, categoryNo);
			 	 pstmt.setLong(2, userNo);	
			 	 pstmt.setLong(3, postNo);	
			 	 
				 rs= pstmt.executeQuery();
			  	 rs.next();		  	 
			 	 int index = rs.getInt(1)-5; //---------------인덱스구함

		 	 sql = "select count(*)\r\n" + 
		 	 		"  from post a, category b \r\n" + 
		 	 		" where a.category_no = b.no\r\n" + 
		 	 		"   and a.category_no = ?\r\n" + 
		 	 		"   and b.user_no = ?";
		 	 
				 pstmt = conn.prepareCall(sql);
			  	 pstmt.setLong(1, categoryNo);
			 	 pstmt.setLong(2, userNo);	
			 	 
				 rs= pstmt.executeQuery();
			  	 rs.next();		  	 
			 	 int count = rs.getInt(1); //-----------------총개수 구함
		 	 		  	 			 	 
		 	if(index > (count-9) ) {
		 		index =  count-9;
		 	 }
		 	 if(index < 0 ) {
		 		 index = 0;
		 	 }
		 	  
		 	 
			 sql = "select a.no, a.title, a.content, a.reg_date, a.category_no\r\n" + 
			 		"  from post a, category b \r\n" + 
			 		" where a.category_no = b.no\r\n" + 
			 		"   and a.category_no = ?\r\n" + 
			 		"   and b.user_no = ?\r\n" + 
			 		"   order by reg_date desc\r\n" + 
			 		"   limit ?,9";
				
			pstmt = conn.prepareCall(sql);
			pstmt.setLong(1, categoryNo);
			pstmt.setLong(2, userNo);		
			pstmt.setInt(3, index);		
			
			rs= pstmt.executeQuery();
			  			
			while(rs.next()) {
				
				PostVo vo = new PostVo();
				
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setRegDate(rs.getString(4));
				vo.setCategoryNo(rs.getLong(5));

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

//public List<PostVo> getList(long userNo){
//	
//	List<PostVo> list = new ArrayList<PostVo>();
//	
//	Connection conn = null;
//	ResultSet rs =null;
//	PreparedStatement pstmt = null;
//	
//	try {
//		 conn = getConnection();
//		 pstmt = null;
//		 rs = null;
//		
//		 
//		String sql = "select a.no, a.title, a.content, a.reg_date, a.category_no\r\n" + 
//				"  from post a, category b \r\n" + 
//				" where a.category_no = b.no\r\n" + 
//				"   and b.user_no = ? " + 
//				"   order by reg_date desc";
//			
//		pstmt = conn.prepareCall(sql);
//		pstmt.setLong(1, userNo);			
//		rs= pstmt.executeQuery();
//		  			
//		while(rs.next()) {
//			
//			PostVo vo = new PostVo();
//			
//			vo.setNo(rs.getLong(1));
//			vo.setTitle(rs.getString(2));
//			vo.setContent(rs.getString(3));
//			vo.setRegDate(rs.getString(4));
//			vo.setCategoryNo(9999);
//
//			list.add(vo);
//		}
//		
//	} catch (SQLException e) {
//		System.out.println( "error: "+e );
//	} finally {
//		try {
//			if(rs != null) {
//				rs.close();
//			}
//			if(pstmt != null) {
//				pstmt.close();
//			}
//			if(conn != null) {
//				conn.close();
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	return list;
//}
	
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
