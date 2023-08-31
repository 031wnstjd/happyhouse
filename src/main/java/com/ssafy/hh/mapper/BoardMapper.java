package com.ssafy.hh.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hh.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoard();
	public BoardDto selectBoardByNo(int articleno);
	public int insertBoard(BoardDto boardDto);
	public int updateBoard(BoardDto boardDto);
	public int deleteBoard(int articleno);
	public int countHit(int articleno);
}