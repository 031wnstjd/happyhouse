package com.ssafy.hh.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value ="MemberDto (유저 정보)", description = "유저 아이디, 이름, 비밀번호 등이 담긴 Class")
public class MemberDto {
	
	@ApiModelProperty(value = "유저아이디")
	private String userid;
	@ApiModelProperty(value = "유저이름")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String userpwd;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "관심지역")
	private String interest;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}


}
