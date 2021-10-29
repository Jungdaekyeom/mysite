package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		// 로그인 안하고 들어오게 되면
		if (session == null) {
			return false;
		}

		session.removeAttribute("authUser");
		session.invalidate();

		response.sendRedirect(request.getContextPath());
		return false;
	}

}
