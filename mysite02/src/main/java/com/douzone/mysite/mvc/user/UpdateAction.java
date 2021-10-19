package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();

		// 다운캐스팅
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 사용자가 없으면 원래대로 돌아감
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		//////////////////////////////////////////////////////

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(authUser.getNo());

		new UserDao().update(vo);
		// 세션 문제로 인해 해당 부분이 없으면 이름을 변경해도 header부분이 변경되지 않음
		authUser.setName(name);

		MvcUtil.redirect("/mysite02", request, response);

	}
}