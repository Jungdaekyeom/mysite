package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* 로그아웃 처리 */
		HttpSession session = request.getSession();
		System.out.println("removeAttribute 전 세션값 : " + session);

		// 이 코드는 파라미터로 지정된 이름의 속성 값을 제거할 때 사용된다. 세션에 저장해둔 속성을 이미 모두 사용하였고 더 이상 사용할 필요가
		// 없을 때 제거하게 된다.
		session.removeAttribute("authUser");
		// 세션 초기화 : 세션의 모든 속성을 제거
		session.invalidate();
		
		System.out.println("removeAttribute 후 세션값 : " + session);

		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}