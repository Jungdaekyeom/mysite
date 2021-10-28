package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

import ch.qos.logback.core.net.SyslogOutputStream;

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
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") Long page,
			@RequestParam(value = "sec", required = true, defaultValue = "1") Long section, Model model) {

		BoardVo vo = boardService.findByNo(no);
		boardService.hitCountUp(no); // 조회수 올리기용

		model.addAttribute("vo", vo);
		model.addAttribute("p", page);
		model.addAttribute("sec", section);

		return "board/view";
	}

	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") Long page,
			@RequestParam(value = "sec", required = true, defaultValue = "1") Long section) {
		boardService.delete(no);
		return "redirect:/board?p=" + page + "&sec=" + section;
	}

	// list.jsp에서 넘어옴
	// 글쓰기에서 들어온 것인지, 답글달기에서 들어온 것인지를 판단하기 위해 c 사용
	@RequestMapping("/write/{c}")
	public String write(@PathVariable("c") Long c,
			@RequestParam(value = "m", required = false, defaultValue = "0") Long maxGroupNo,
			@RequestParam(value = "no", required = false, defaultValue = "0") Long no,
			@RequestParam(value = "p", required = false, defaultValue = "1") Long p,
			@RequestParam(value = "sec", required = false, defaultValue = "1") Long section,
			Model model) {
		model.addAttribute("c", c);
		model.addAttribute("maxGroupNo", maxGroupNo);
		model.addAttribute("no", no);
		model.addAttribute("p", p);
		model.addAttribute("sec", section);
		
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@RequestParam(value = "userno", required = true) Long userNo,
			@RequestParam(value = "comment") Long comment, 
			@RequestParam(value = "no", required = false, defaultValue = "0") Long no, 
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "contents", required = false, defaultValue = "  ") String contents,
			@RequestParam(value = "maxGroupNo", required = false) Long maxGroupNo,
			@RequestParam(value = "p", required = false, defaultValue = "1") Long page,
			@RequestParam(value = "sec", required = false, defaultValue = "1") Long section) {

		if (comment == 0) {
			BoardVo boardVo = new BoardVo();
			boardVo.setUserNo(userNo);
			boardVo.setTitle(title);
			boardVo.setContents(contents);
			// 그룹값 추가
			System.out.println("글쓰기 시작");
			boardVo.setGroupNo(maxGroupNo);
			boardService.write(boardVo);
			// 일반 글을 쓴 이후에는 자신의 글이 최상단에 있을 것이므로, 1p 1sec으로 이동
			return "redirect:/board?p=1&sec=1";
		} else {
			System.out.println("답글쓰기 시작");
			
			// 답글을 다는 친구의 정보를 갖고옴
			BoardVo vo = boardService.findByNo(no);
			System.out.println("답글을 달려던 정보 : " + vo);
			System.out.println("답글을 달려던 정보의 깊이값 : " + vo.getDepth());
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setDepth(vo.getDepth() + 1);
			vo.setOrderNo(vo.getOrderNo() + 1);
			// 답글을 > < 계산하는 업데이트
			boardService.update(vo);
			boardService.comment(vo);
			// 답글을 쓴 이후에는 쓴 글이 이전 페이지에 있을 것이므로, 이전 페이지로 돌아감.
			return "redirect:/board?p=" + page + "&sec=" + section;
		}
	}
}