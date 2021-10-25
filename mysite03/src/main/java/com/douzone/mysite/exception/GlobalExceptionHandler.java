package com.douzone.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	// 스프링에서는 예외 처리를 위해 ExceptionHandler 와 @ControllerAdvice를 사용한다.
	@ExceptionHandler(Exception.class)
	public void HandlerException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {

		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());

		// 2. 요청 구분
		// 만약, json 요청인 경우 request header의 accept에 application/json
		// 만약, html 요청인 경우 request header의 accept에 text/html
		// => header를 뽑아 내면 됨

		String accept = request.getHeader("accept");
		if (accept.matches(".*application/json.*") == true) {
			// 3. 응답은 json으로 한다.
			JsonResult result = JsonResult.fail(errors.toString());
			new ObjectMapper().writeValueAsString(result);
			String jsonString = new ObjectMapper().writeValueAsString(result);
			
			response.setStatus(HttpServletResponse.SC_OK);
			// 소켓스트림을 내놔라
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));
			os.close();
		} else if (accept.matches(".*text/html.*") == true) {
			// 4. 사과페이지(html 응답, 정상종료)
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);

		}

	}
}
