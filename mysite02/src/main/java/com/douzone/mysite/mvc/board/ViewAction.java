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
		Long page = Long.parseLong(request.getParameter("p"));
		Long section = Long.parseLong(request.getParameter("sec"));
		BoardVo vo = new BoardDao().findByNo(no);
		// 조회수 카운팅을 위한 또 다른 객체
		BoardVo vo2 = new BoardDao().hitCountUp(no, vo.getHit());

		request.setAttribute("vo", vo);
		request.setAttribute("vo2", vo2);

		request.setAttribute("p", page);
		request.setAttribute("sec", section);

		MvcUtil.forward("board/view", request, response);

	}

}
