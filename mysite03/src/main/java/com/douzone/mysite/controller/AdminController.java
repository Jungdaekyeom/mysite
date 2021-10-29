package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	// 용수에게 물어보쟈!
	@Autowired
	ServletContext servletContext;

	@RequestMapping("")
	public String main() {
		return "admin/main";
	}

//	@RequestMapping("/main/update")
//	public String main(SiteVo siteVo) {
//		siteService.update(siteVo);
//		return "admin/main";
//	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		System.out.println("여길 지나가나?");
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

}