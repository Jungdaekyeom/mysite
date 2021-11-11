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

	// Service 이름을 지을 때는, CRUD 이름에 맞게 지어야 한다.
	// ex) getBoardList()
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

	public BoardVo write(BoardVo boardVo) {
		// 그룹넘버를 1 추가시킨다.
		boardVo.setGroupNo(boardVo.getGroupNo() + 1);
		return boardRepository.write(boardVo);
	}

	public BoardVo update(BoardVo boardVo) {
		System.out.println("1번 서비스" + boardVo);
		return boardRepository.update(boardVo);
	}

	public BoardVo comment(BoardVo boardVo) {
		return boardRepository.comment(boardVo);
	}

}