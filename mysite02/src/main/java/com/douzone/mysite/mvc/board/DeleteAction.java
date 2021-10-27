package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		Long page = Long.parseLong(request.getParameter("p"));
		Long section = Long.parseLong(request.getParameter("sec"));
		BoardVo vo = new BoardDao().delete(no);

		request.setAttribute("vo", vo);

		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=" + page + "&sec=" + section, request, response);
	}

}
