package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	

	// Validation 추가
	// 회원가입 시에 제약을 주기 위해서 해당 것들을 제작
	// 바인딩이 된 vo들을 갖고옴(@Valid)
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			// List<ObjectError> list = result.getAllErrors();
			// for (ObjectError error : list) {
			// 	System.out.println(error);
			// }
			
			// Map<String, Object> map = result.getModel();
			// model.addAttribute("userVo", map.get("userVo"));
			// model.addAllAttributes(map);
			
			// 밑 한 줄이 위와 같은 과정인가?
			
			// result에서 모델을 끄집어냄
			model.addAllAttributes(result.getModel());
			
			// 함수에 @ModelAttribute 를 쓰거나, 밑의 주석을 지우거나.
			// model.addAttribute("userVo", vo);
			// 에러가 있는 경우
			return "user/join";
		}
		
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}	

	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		userVo.setNo(authUser.getNo());

		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}	
}