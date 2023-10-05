package com.ssafy.hh.service;

import java.util.List;
import com.ssafy.hh.dto.QnaDto;

public interface QnaService {
	public List<QnaDto> allQna();
	public List<QnaDto> userQna(String userid);
	public boolean writeQna(QnaDto qnaDto);
	public boolean updateQna(QnaDto qnaDto);
	public boolean deleteQna(int articleno);
	public QnaDto detailQna(int articleno);
	public boolean updateAnswer(QnaDto qnaDto);
	public QnaDto getAnswer(int articleno);
}
