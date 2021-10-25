package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		Long page = Long.parseLong(request.getParameter("p"));
		Long section = Long.parseLong(request.getParameter("sec"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// modify 페이지에서 변경된 값 긁어온 것 확인
		// System.out.println(no);
		// System.out.println(title);
		// System.out.println(content);

		BoardVo vo = new BoardDao().modify(title, content, no);

		// 리퀘스트에 modify에서 받아온 것들을 vo로 묶어서 보냄
		request.setAttribute("vo", vo);

		// 글을 수정한 순간에는, 수정된 글을 확인해야 하므로 그 이전 페이지로 돌아감
		MvcUtil.redirect(request.getContextPath() + "/board?a=view&no=" + no + "&p=" + page + "&sec=" + section,
				request, response);
	}

}
