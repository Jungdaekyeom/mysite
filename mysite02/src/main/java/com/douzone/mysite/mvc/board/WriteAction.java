package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		// 쓰는 당시에는 userno(이 앞페이지에선 authUser)가 맞음
		String userNo = request.getParameter("userno");

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		// user번호를 받아와야 함
		// 문자열을 long으로 형변환
		vo.setUserNo(Long.parseLong(userNo));

		// 메인화면의 글쓰기
		new BoardDao().insert(vo);

		// 글을 쓴 순간에는, 자신의 글이 최상단에 있을 것이므로 p=1을 돌려줌
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=1&sec=1",
				request, response);

	}

}
