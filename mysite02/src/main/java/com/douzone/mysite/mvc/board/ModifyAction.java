package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// URL에서 userno를 가져옴
		String userNo = request.getParameter("userno");
		BoardVo vo = new BoardDao().findByNo(Long.parseLong(userNo));

		// 리퀘스트에 findByNo에서 받아온 것들을 vo로 묶어서 보냄
		request.setAttribute("vo", vo);

		MvcUtil.forward("board/modify", request, response);
	}
}
