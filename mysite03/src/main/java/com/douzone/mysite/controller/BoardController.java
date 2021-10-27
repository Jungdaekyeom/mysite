package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String list(@RequestParam(value = "p", required = true, defaultValue = "1") Long page,
			@RequestParam(value = "sec", required = true, defaultValue = "1") Long section, Model model) {

		model.addAttribute("total", boardService.findAll());
		System.out.println(boardService.findAll());

		model.addAttribute("maxGroupNo", boardService.maxGroupNo());
		System.out.println(boardService.maxGroupNo());

		// Action의 request.setAttribute("list", list)와 같다.
		model.addAttribute("list", boardService.findAllByTen(page));
		System.out.println(boardService.findAllByTen(page));

		return "board/list";
	}

	// 보기만 할건데 이 url을 다 요구할 필요가 있나?
	@RequestMapping("/view/{no}/{hit}/{p}/{sec}")
	public String view(@PathVariable(value = "no", required = true) Long no,
			@PathVariable(value = "hit", required = true) Long hit,
			@PathVariable(value = "p", required = true) Long page,
			@PathVariable(value = "sec", required = true) Long section, Model model) {

		BoardVo boardVo = boardService.findByNo(no);
		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}

	@RequestMapping("/delete/{no}")
	public String delete(@RequestParam(value="no", required=true) Long no) {
		return 
	}

}