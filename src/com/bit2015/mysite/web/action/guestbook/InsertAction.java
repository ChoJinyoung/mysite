package com.bit2015.mysite.web.action.guestbook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.vo.GuestBookVo;
import com.bit2015.web.WebUtil;
import com.bit2015.web.action.Action;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		GuestBookDao dao=new GuestBookDao();
		dao.insert(vo);

		WebUtil.redirect(response, "/mysite/gb");
	}

}
