package com.ssafy.hh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hh.dto.BoardDto;
import com.ssafy.hh.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<BoardDto> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
  	@Transactional
	public boolean writeBoard(BoardDto boardDto) {
		return boardMapper.insertBoard(boardDto) == 1;
	}

	@Override
	public BoardDto detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(BoardDto boardDto) {
		return boardMapper.updateBoard(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public boolean countHit(int articleno) {
		return boardMapper.countHit(articleno) == 1;
	}
}