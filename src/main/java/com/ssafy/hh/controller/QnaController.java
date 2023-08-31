package com.ssafy.hh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hh.dto.QnaDto;
import com.ssafy.hh.service.QnaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/qna")
public class QnaController {
	
	private final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qnaService;
	
	@ApiOperation(value = "모든 문의글을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnaDto>> allQna() throws Exception {
		logger.debug("allQna - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.allQna(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "문의번호에 해당하는 문의의 정보를 반환한다.", response = QnaDto.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<QnaDto> detailQna(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<QnaDto>(qnaService.detailQna(articleno), HttpStatus.OK);
	}
		
	@ApiOperation(value = "특정 유저의 문의 리스트를 반환한다", response = List.class)    
	@GetMapping("/list/{userid}")
	public ResponseEntity<List<QnaDto>> userQna(@PathVariable String userid) throws Exception {
		logger.debug("userQna - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.userQna(userid),HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 문의를 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQna(@RequestBody QnaDto qnaDto) {
		logger.debug("writeQna - 호출");
		System.out.println(qnaDto.getUserid());
		System.out.println(qnaDto.getType());
		System.out.println(qnaDto.getState());
		if (qnaService.writeQna(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "문의번호에 해당하는 문의내용을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping("{articleno}")
	public ResponseEntity<String> updateQna(@RequestBody QnaDto qnaDto) {
		logger.debug("updateQna - 호출");
		logger.debug("" + qnaDto);
		if (qnaService.updateQna(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "문의번호에 해당하는 문의를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteQna(@PathVariable int articleno) {
		logger.debug("deleteQna - 호출");
		if (qnaService.deleteQna(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "문의 답변을 등록 및 수정한다.", response = String.class)
	@PatchMapping("/reply/{articleno}")
	public ResponseEntity<String> updateAnswer(@RequestBody QnaDto qnaDto) throws Exception {
		logger.debug("updateAnswer - 호출");
		if(qnaService.updateAnswer(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} 
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	
	@ApiOperation(value = "문의 답변을 조회한다.", response = String.class)
	@GetMapping("/reply/{articleno}")
	public ResponseEntity<?> getAnswer(@PathVariable("articleno") int articleno) throws Exception {
		logger.debug("getAnswer - 호출");
		if(qnaService.getAnswer(articleno) != null) {
			return new ResponseEntity<QnaDto>(qnaService.getAnswer(articleno), HttpStatus.OK);
		} 
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	
}
