package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping({ "", "/main" })
	public String index() {

		// spring-servlet.xml에서 ViewResolver 설정 후
		// return "/WEB-INF/views/main/index.jsp"; 를 다음과 같이 바꿀 수 있다.
		return "main/index";
	}
	
}