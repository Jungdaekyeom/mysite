package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;

	public Long findAll() {
		return sqlSession.selectOne("board.findAll");
	}

	public Long maxGroupNo() {
		return sqlSession.selectOne("board.maxGroupNo");
	}

	public List<BoardVo> findAllByTen(Long startPost) {
		return sqlSession.selectList("board.findAllByTen", startPost);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public Long hitCountUp(Long no) {
		return sqlSession.selectOne("board.hitCountUp", no);
	}

	public Long delete(Long no) {
		return sqlSession.selectOne("board.delete", no);
	}

	public BoardVo write(BoardVo boardVo) {
		return sqlSession.selectOne("board.write", boardVo);
	}

	public BoardVo update(BoardVo boardVo) {
		System.out.println("2번 리포지토리" + boardVo);
		return sqlSession.selectOne("board.update", boardVo);
	}

	public BoardVo comment(BoardVo boardVo) {
		return sqlSession.selectOne("board.comment", boardVo);
	}

}