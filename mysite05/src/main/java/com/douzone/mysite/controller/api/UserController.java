package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

// 1. join.jsp에서 넘어옴
@Controller("userApiController")
@RequestMapping("/user/api")

public class UserController {
	// 의존성 주입
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/checkemail")
//	@GetMapping("/checkemail")
	//public Map<String, Object> checkemail(
	public JsonResult checkemail(@RequestParam(value = "email", required = true, defaultValue = "") String email) {
		UserVo userVo = userService.getUser(email);

		///////////////////////////////////////////
		// Map<String, Object> map = new HashMap<>();
		// map.put("result", "success");
		// map.put("data", userVo != null);
		// map.put("message", null);
		///////////////////////////////////////////

		return JsonResult.success(userVo != null);
		
	}
}
