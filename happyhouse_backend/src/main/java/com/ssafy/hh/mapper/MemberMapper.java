package com.ssafy.hh.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hh.dto.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto signIn(MemberDto memberDto) throws Exception;
	int signUp(MemberDto memberDto) throws Exception;
	int updateMember(MemberDto memberDto) throws Exception;
	int updatePassword(MemberDto memberDto) throws Exception;
	int deleteMember(String userid) throws Exception;
	List<MemberDto> findAll() throws Exception;
	MemberDto findById(String userid) throws Exception;
	String isDuplicatedId(String userid) throws Exception;
	List<String> findIdByEmail(String email) throws Exception;
	int emailCheck(String email) throws Exception;
	int findPassword(MemberDto memberDto) throws Exception;
	int findPasswordCheck(MemberDto memberDto) throws Exception; // 비밀번찾기용
	int updateUserPassword(Map<String, String> map) throws Exception; // 비밀번호찾기용
}
