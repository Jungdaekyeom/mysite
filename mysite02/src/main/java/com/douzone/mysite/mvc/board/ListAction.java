package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<BoardVo> list = new BoardDao().findAll();
		
		// BoardDao의 findAll 함수를 이용해 만든 리스트 객체를 list라는 곳에 담고
		// list를 request.setAttribute해서 "list"라는 이름으로 포장해서 보낸다.
		// 글쓴이 이름이 필요하므로, findAll에서 조인한 테이블에서 이름까지 갖고온다.
		request.setAttribute("list", list);

		MvcUtil.forward("board/list", request, response);

	}

}
