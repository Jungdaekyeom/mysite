package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

// 접근 권한 판별 로직을 수행하는 클래스
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. handler 종류 확인
		// 우리가 관심있는 것은 controller에 있는 메서드이므로, HandlerMethod 타입인지 체크
		if (handler instanceof HandlerMethod == false) {
			// return true면, controller에 있는 메서드가 아니므로, 그대로 컨트롤러로 진행
			return true;
		}

		// 2. casting(형변환)
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println("handlerMethod : " + handlerMethod);
		// 3. Handler Method의 @Auth 받아오기

		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		System.out.println("auth : " + auth);
		// 4. Handler Method에 @Auth가 없으면 Type에 있는 지 확인(과제)
		if(auth == null) {
			// 과제였지만 하지 못했습니다...
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		// 5. Type과 Method에 @Auth가 적용이 안되어 있는 경우
		// method에 @Auth가 없는 경우, 즉 인증이 필요 없는 요청.
		// 일반 유저는 여기서 반환됨.
		if (auth == null) {
			return true;
		}

		// 6. @Auth가 적용이 되어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		if (session == null) {
			// 인증되지 않은 접속. 로그인 화면으로 이동
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 7. 세션이 존재하면 유효한 유저인지 확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		//7. 권한(Authorization) 체크를 위해서 @Auth의 role 가져오기("USER", "ADMIN")
		String role = auth.role();
				
		//8. @Auth의 role이 "USER" 인 경우, authUser의 role은 상관없다.
		if("USER".equals(role)) {
			return true;
		}
				
		//9.@Auth의 role이 "ADMIN" 인 경우, authUser의 role은 "ADMIN" 이어야 한다.
		if("ADMIN".equals(authUser.getRole()) == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 옳은 관리자 권한
		// @Auth의 role: "ADMIN"
		// authUser의 role: "ADMIN"
		return true;

	}

}