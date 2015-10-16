package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.mysite.vo.BoardVo;

public class BoardDao {
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
	
	public void insert(BoardVo vo){
		try{
		Connection connection=getConnection();
		
		String sql="insert into board values(board_no_seq.nextval,?,?,?,0,sysdate)";
		PreparedStatement pstmt=connection.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getMemberNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		connection.close();
		}catch(SQLException e){
			System.out.println("error:" +e);
		}
	}
	public List<BoardVo> getListAll(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		try{
			Connection connection=getConnection();
			Statement stmt=connection.createStatement();
			
			String sql="select a.no, a.title, a.content,a.member_no, a.view_cnt,to_char(a.reg_date,'yyyy-mm-dd hh:mm:ss')"
					+ ",b.name as member_name from board a,member b where a.MEMBER_NO=b.no order by a.reg_date desc";
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no=rs.getLong(1);
				String title=rs.getString(2);
				String content=rs.getString(3);
				Long memberNo=rs.getLong(4);
				Long viewCnt=rs.getLong(5);
				String regDate=rs.getString(6);
				String memberName=rs.getString(7);
				
				BoardVo vo=new BoardVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setMemberNo(memberNo);
				vo.setMemberName(memberName);
				vo.setViewCnt(viewCnt);
				vo.setRegDate(regDate);
				list.add(vo);
			}
			rs.close();
			stmt.close();
			connection.close();
			
		}catch(SQLException e){
			System.out.println("error: "+e);
		}
		return list;
	}
	public void delete(String num){
		try{
			Connection conn=getConnection();
			Long no=Long.parseLong(num);
			String sql="delete from board where no=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("error:"+e);
		}
	}
	public BoardVo getList(long no){
		BoardVo vo=null;
		try{
			Connection conn=getConnection();
			String sql="select *from board where no=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				no=rs.getLong(1);
				String title=rs.getString(2);
				String content=rs.getString(3);
				Long memberNo=rs.getLong(4);
				
				
				vo=new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setMemberNo(memberNo);
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("error:"+e);
		}
		return vo;
	}

	public void increaseViewCount(Long no) {
		try{
			Connection conn=getConnection();
			String sql="update board set view_cnt=view_cnt+1 where no=? ";
			PreparedStatement pstmt= conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			System.out.println("error:"+e);
		}
		
	}
	public void update(BoardVo vo){
		try{
			Connection conn=getConnection();
			String sql="update board set title=?,content=? where no=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getMemberNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			System.out.println("error update: "+e);
		}
	}
	
}
