package com.bit2015.mysite.web.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.web.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String number = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestBookDao dao=new GuestBookDao();
		dao.delete(number, password);

		response.sendRedirect("/mysite/gb");
	}
}
