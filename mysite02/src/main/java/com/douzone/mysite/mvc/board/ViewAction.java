package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 내가 여기서 받고 싶은 것은, URL에서 넘어온 no
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardDao().findByNo(no);
		request.setAttribute("vo", vo);

		MvcUtil.forward("board/view", request, response);

	}

}
