package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public Long findAll() {
		return boardRepository.findAll();
	}

	public Long maxGroupNo() {
		return boardRepository.maxGroupNo();
	}

	public List<BoardVo> findAllByTen(Long page) {
		Long startPost = (page - 1) * 10;
		return boardRepository.findAllByTen(startPost);
	}

	public BoardVo findByNo(Long no) {
		return boardRepository.findByNo(no);
	}

	public Long hitCountUp(Long no) {
		return boardRepository.hitCountUp(no);
	}

	public Long delete(Long no) {
		return boardRepository.delete(no);
	}

	public BoardVo insert(BoardVo boardVo) {
		boardVo.setGroupNo(boardVo.getGroupNo() + 1);
		return boardRepository.insert(boardVo);
	}
}