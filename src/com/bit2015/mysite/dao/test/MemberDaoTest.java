package com.bit2015.mysite.dao.test;

import com.bit2015.mysite.dao.MemberDao;
import com.bit2015.mysite.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insertTest();
		getTest();
		updateTest();
	}

	public static void insertTest() {
		// TODO Auto-generated method stub
		MemberDao dao=new MemberDao();
		MemberVo vo=new MemberVo();
		vo.setName("홍");
		vo.setEmail("길.com");
		vo.setPassword("123");
		vo.setgender("male");
		
		dao.insert(vo);
	}
	public static void getTest(){
		MemberDao dao=new MemberDao();
		MemberVo vo=dao.get("길.com", "123");
		System.out.println(vo);
	}
	public static void updateTest(){
		MemberDao dao=new MemberDao();
		MemberVo vo=new MemberVo();
		vo.setName("홍2");
		vo.setEmail("길2.com");
		vo.setPassword("123");
		vo.setgender("M");
		vo.setNo(3L);
		
		dao.update(vo);
	}

}
