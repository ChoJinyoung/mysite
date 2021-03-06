package com.bit2015.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.web.WebUtil;
import com.bit2015.web.action.Action;


public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Long mno = Long.parseLong(request.getParameter("mno"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		BoardVo vo=new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMemberNo(mno);
		BoardDao dao=new BoardDao();
		dao.insert(vo);
		
		WebUtil.redirect(response, "/mysite/board");
	}
}
