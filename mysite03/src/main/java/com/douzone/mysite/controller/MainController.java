package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
public class MainController {

	// @Auth(test=false) : Auth에서 디폴트를 false로 준다면 사용 가능
	// @Auth("USER") : USER로 접속한 사람만 사용 가능
	// @Auth("ADMIN") : ADMIN으로 접속한 사람만 사용 가능
	// @Auth("USER") : Auth.java에서 함수이름을 value로 주면 value라는 이름을 주지 않아도 사용 가능
	// @Auth(role = "ADMIN") // Auth.java에서 디폴트값으로 정의한 값( public String role()
	// default "USER"; )이 괄호 안에 들어감.
//	@RequestMapping({ "", "/main" })
//	public String index() {
//		return "main/index";
//	}
//
//	@ResponseBody
//	@RequestMapping("/msg01")
//	public String message01() {
//		return "안녕";
//	}
//
//	@ResponseBody
//	@RequestMapping("/msg02")
//	public Object message02(/* HttpServletResponse resp */) throws Exception {
////		resp.setContentType("application/json; charset=UTF-8");
////		resp.getWriter().print("{message:\"Hello World\"}");
//		Map<String, Object> map = new HashMap<>();
//		map.put("message", "Hello World");
//
//		return map;
//	}
//
//	@RequestMapping("/hello")
//	public void message(HttpServletResponse resp) throws Exception {
//		resp.setContentType("application/json; charset=UTF-8");
//		// 이 부분에서 JSON 포맷으로 응답하면 끝
//		resp.getWriter().print("{message:\"Hello World\"}");
//	}
//
//	@ResponseBody
//	@RequestMapping("/hello")
//	public Object message(/* HttpServletResponse resp */) throws Exception {
////		resp.setContentType("application/json; charset=UTF-8");
////		resp.getWriter().print("{message:\"Hello World\"}");
//		Map<String, Object> map = new HashMap<>();
//		map.put("message", "Hello World");
//
//		return map;
//	}
	
	// 용수에게 물어보쟈!
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;

	@RequestMapping({ "", "/main" })
	public String index(Model model) {

//		// site를 내놔
//		SiteVo site = servletContext.setAttribute("site");
//		
//		if(site == null) {
//			SiteVo vo = 
//			servletContext.setAttribute("site", vo);
//		}
		System.out.println("주이바보겟사이트 왜 널뜸?:" + siteService.getSite());
		model.addAttribute("site", siteService.getSite());

		return "main/index";
	}

	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}

	@ResponseBody
	@RequestMapping("/msg02")
	public Object message02(/* HttpServletResponse resp */) throws Exception {
		// resp.setContentType("application/json; charset=UTF-8");
		// resp.getWriter().print("{\"message\":\"Hello World\"}");

		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");

		return map;
	}
}