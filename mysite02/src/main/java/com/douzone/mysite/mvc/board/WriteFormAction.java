package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();

		// 다운캐스팅
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// 로그인이 안된 상태로 글쓰기를 누르면 로그인 폼으로 들어감.
		if (authUser == null) {
			MvcUtil.forward("user/loginform", request, response);
			// 의미상으로는 여기서 끝났으나, 아래의 MvcUtil을 또 들어가므로 여기서 return 처리
			return;
		}

		// 글쓰기에서 들어온 값인지, 댓글달기에서 들어온 값인지를 확인하기 위한 c
		Long comment = Long.parseLong(request.getParameter("c"));

		request.setAttribute("c", comment);

		if (request.getParameter("no") == null) {
			// no가 null 이라는 말은, 글쓰기에서 들어온 것이 되므로
			// maxGroup값을 1올려주는 계산을 하기위해서 이쪽 if문으로 빠짐.
			Long maxGroupNo = Long.parseLong(request.getParameter("g"));
			request.setAttribute("maxGroupNo", maxGroupNo);

		} else {
			// no가 null이 아니라는 말은 글쓰기가 아닌 댓글 달기의 URL에서 넘어왔다는
			// 뜻이므로 해당 else문으로 빠짐
			Long no = Long.parseLong(request.getParameter("no"));
			request.setAttribute("no", no);
		}

		MvcUtil.forward("board/write", request, response);
	}

}
