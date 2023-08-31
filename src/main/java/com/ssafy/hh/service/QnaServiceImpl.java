package com.ssafy.hh.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hh.dto.QnaDto;
import com.ssafy.hh.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public List<QnaDto> allQna() {
		return qnaMapper.selectAllQna();
	}
	
	@Override
	public List<QnaDto> userQna(String userid) {
		return qnaMapper.selectUserQna(userid);
	}
	
	@Override
	@Transactional
	public boolean writeQna(QnaDto qnaDto) {
		return qnaMapper.insertQna(qnaDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean updateQna(QnaDto qnaDto) {
		return qnaMapper.updateQna(qnaDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteQna(int articleno) {
		return qnaMapper.deleteQna(articleno) == 1;
	}
	
	@Override
	public QnaDto detailQna(int articleno) {
		return qnaMapper.selectQnaByNo(articleno);
	}

	@Override
	public boolean updateAnswer(QnaDto qnaDto) {
		return qnaMapper.updateAnswer(qnaDto) == 1;
	}

	@Override
	public QnaDto getAnswer(int articleno) {
		return qnaMapper.getAnswer(articleno);
	}
	
}
