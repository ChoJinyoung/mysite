package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.mysite.vo.GuestBookVo;


public class GuestBookDao {
	private Connection getConnection() throws SQLException{
		// 1.드라이버 로딩
		Connection connection=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.커넥션 만들기(oracle db)
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dburl, "webdb","webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패-" + e);
		}
		
		return connection;
	}
	public void insert(GuestBookVo vo) {
		try {
			//1.Conncetion 가져오기
			Connection connection=getConnection();
			// 2.Statement 준비
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			// 3.binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			// 4.쿼리 실행
			pstmt.executeUpdate();

			// 5.자원 정리
			pstmt.close();
			connection.close();

		}catch (SQLException e) {
			System.out.println("SQL 오류-" + e);
		}
	}
	public void delete(String num,String password){
		try{
			Connection connection=getConnection();
			Long no=Long.parseLong(num);
			String sql="delete from guestbook where no=? and password=?";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
		}catch(SQLException e){
			System.out.println("SQL 오류-" + e);
		}
	}
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		try {
			//1.Connection 가져오기
			Connection connection=getConnection();

			// 2.Statement 생성
			Statement stmt = connection.createStatement();

			// 3.SQL문 생성
			String sql = "select * from guestbook order by reg_date desc";
			ResultSet rs = stmt.executeQuery(sql);

			// 4.row 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String message = rs.getString(4);
				String regdate=rs.getString(5);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setRegDate(regdate);
				list.add(vo);
			}
			// 5.자원정리
			rs.close();
			stmt.close();
			connection.close();
		}catch (SQLException e) {
			System.out.println("SQL 오류-" + e);
		}
		return list;
	}
	
}
