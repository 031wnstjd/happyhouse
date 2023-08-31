package com.ssafy.hh.service;

import java.util.List;
import java.util.Map;

import com.ssafy.hh.dto.MemberDto;

public interface MemberService {
	MemberDto signIn(MemberDto memberDto) throws Exception;
	boolean signUp(MemberDto memberDto) throws Exception;
	boolean updateMember(MemberDto memberDto) throws Exception;
	boolean updatePassword(MemberDto memberDto) throws Exception;
	boolean deleteMember(String userid) throws Exception;
	List<MemberDto> findAll() throws Exception;
	MemberDto findById(String userid) throws Exception;
	String isDuplicatedId(String userid) throws Exception;
	List<String> findIdByEmail(String email) throws Exception;
	boolean emailCheck(String email) throws Exception;
	boolean findPassword(MemberDto memberDto) throws Exception;
	boolean curPwdCheck(Map<String, String> params) throws Exception;
	boolean findPasswordCheck(MemberDto memberDto) throws Exception;
}
