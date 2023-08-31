package com.ssafy.hh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hh.dto.QnaDto;

@Mapper
public interface QnaMapper {
	
	public List<QnaDto> selectAllQna();
	public List<QnaDto> selectUserQna(String userid);
	public int insertQna(QnaDto qnaDto);
	public int updateQna(QnaDto qnaDto);
	public int deleteQna(int articleno);
	public QnaDto selectQnaByNo(int articleno);
	public int updateAnswer(QnaDto qnaDto);
	public QnaDto getAnswer(int articleno);
}
