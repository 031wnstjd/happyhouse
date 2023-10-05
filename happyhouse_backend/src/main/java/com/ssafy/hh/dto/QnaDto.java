package com.ssafy.hh.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnaDto (1:1문의정보)", description = "문의번호, 문의제목, 문의내용, 작성자아이디, 작성일을 가진   Domain Class")
public class QnaDto {
	@ApiModelProperty(value = "문의번호")
	private int articleno;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "문의제목")
	private String subject;
	@ApiModelProperty(value = "문의내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	@ApiModelProperty(value = "문의유형")
	private String type;
	@ApiModelProperty(value = "답변상태")
	private String state;
	@ApiModelProperty(value = "답변")
	private String answer;
	
	public QnaDto(int articleno, String userid, String subject, String content, String regtime, String type,
			String state, String answer) {
		super();
		this.articleno = articleno;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
		this.type = type;
		this.state = state;
		this.answer = answer;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}