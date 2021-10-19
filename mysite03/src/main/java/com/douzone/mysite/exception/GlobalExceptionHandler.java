package com.douzone.mysite.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 해당 어노테이션으로 전부 해결함
@Controller
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void HandlerException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {

		// 1. 로깅
		StringWriter errors = new StringWriter();
		// ???????
		e.printStackTrace(new PrintWriter(errors));
		// LOGGER.error(errors.toString());

		// 2. 요청 구분

		// 3. 사과 페이지
		request.setAttribute("exception", errors.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
	}
}
