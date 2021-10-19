package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();

		// 다운캐스팅
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 로그인이 안된 상태로 확인을 누르면 로그인 폼으로 들어감.
		if (authUser == null) {
			MvcUtil.forward("user/loginform", request, response);
			// 의미상으로는 여기서 끝났으나, 아래의 MvcUtil을 또 들어가므로 여기서 return 처리
			return;
		}
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");

		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);

		new GuestbookDao().insert(vo);

		MvcUtil.redirect(request.getContextPath() + "/guestbook", request, response);

	}

}
