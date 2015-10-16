package com.bit2015.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.vo.GuestBookVo;
import com.bit2015.web.WebUtil;
import com.bit2015.web.action.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		GuestBookDao dao=new GuestBookDao();
		List<GuestBookVo> list = dao.getList();

		request.setAttribute("list", list);
		WebUtil.forwarding(request, response, "/views/guestbook/list.jsp");
	}

}
