package com.ssafy.hh.service;

import java.util.List;

import com.ssafy.hh.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> retrieveBoard();
	public BoardDto detailBoard(int articleno);
	public boolean writeBoard(BoardDto boardDto);
	public boolean updateBoard(BoardDto boardDto);
	public boolean deleteBoard(int articleno);
	public boolean countHit(int articleno);
}
