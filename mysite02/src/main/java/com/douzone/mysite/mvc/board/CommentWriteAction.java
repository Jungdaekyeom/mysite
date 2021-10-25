package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class CommentWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		Long page = Long.parseLong(request.getParameter("p"));
		Long section = Long.parseLong(request.getParameter("sec"));
		// groupNo / orderNo / depth 세 개를 변경해서 값을 넣어주기 위해, 일단 게시글 no로 시작하는 값들을 싸그리 불러옴
		BoardVo vo = new BoardDao().findByNo(no);
		System.out.println("답글을 달려던 정보 : " + vo);
		System.out.println("답글을 달려던 정보의 깊이값 : " + vo.getDepth());

		// 변경되는 내용인 제목과 내용
		String title = request.getParameter("title");
		String content = request.getParameter("contents");

		vo.setTitle(title);
		vo.setContents(content);
		vo.setDepth(vo.getDepth() + 1);
		vo.setOrderNo(vo.getOrderNo() + 1);
		
		System.out.println(vo);

		// comment 함수에 이 앞 글의 정보를 집어넣어, 결과를 계산한다.
		System.out.println("계산한 후의 정보 : " + new BoardDao().insert(vo));

		// 답글을 단 후에는, 글이 있었던 원래 페이지로 돌아간다
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&p=" + page + "&sec=" + section, request, response);

	}

}
