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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// UserActionFactory에서 넘어옴(회원정보수정)
		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();

		// 다운캐스팅
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 로그인이 안됐다면
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			// 의미상으로는 여기서 끝났으나, 아래의 MvcUtil을 또 들어가므로 여기서 return 처리
			return;
		}
		////////////////////////////////////////////////////////////////////////

		// 유저 번호
		Long no = authUser.getNo();
		
		// findByNo 쿼리에서 이름, 이메일 갖고오기(비밀번호는 갖고오면 안됨)
		UserVo userVo = new UserDao().findByNo(no);
		
		// "user"로 명명해서 싸서 보냄
		request.setAttribute("user", userVo);

		MvcUtil.forward("user/updateform", request, response);
	}

}
