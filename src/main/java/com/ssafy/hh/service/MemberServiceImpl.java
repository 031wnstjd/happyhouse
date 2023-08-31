package com.ssafy.hh.service;

import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hh.dto.MailDto;
import com.ssafy.hh.dto.MemberDto;
import com.ssafy.hh.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MemberDto signIn(MemberDto memberDto) throws Exception {
		MemberDto user = memberMapper.findById(memberDto.getUserid()); // 입력받은 아이디정보로 user 정보 찾기
		if(user == null || !passwordEncoder.matches(memberDto.getUserpwd(), user.getUserpwd())){ 
			System.out.println("비밀번호 불일치");
			return null; // 없는 user이거나 비밀번호가 일치하지 않으면 null을 반환
		}
		System.out.println("비밀번호 일치");
		return user; // 비밀번호가 일치하면 반환
	}

	@Override
	@Transactional
	public boolean signUp(MemberDto memberDto) throws Exception {
		String tmpPwd = memberDto.getUserpwd();
		String encodedPwd = passwordEncoder.encode(tmpPwd);
		memberDto.setUserpwd(encodedPwd);
		return memberMapper.signUp(memberDto) == 1;
	}

	@Override
	@Transactional
	public boolean updateMember(MemberDto memberDto) throws Exception {
		return memberMapper.updateMember(memberDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteMember(String userid) throws Exception {
		return memberMapper.deleteMember(userid) == 1;
	}

	@Override
	public List<MemberDto> findAll() throws Exception {
		return memberMapper.findAll();
	}

	@Override
	public MemberDto findById(String userid) throws Exception {
		return memberMapper.findById(userid);
	}

	@Override
	@Transactional
	public boolean updatePassword(MemberDto memberDto) throws Exception {
		String tmpPwd = memberDto.getUserpwd().trim();
		String encodedPwd = passwordEncoder.encode(tmpPwd);
		memberDto.setUserpwd(encodedPwd);
		return memberMapper.updatePassword(memberDto) == 1;
	}

	@Override
	public String isDuplicatedId(String userid) throws Exception {
		return memberMapper.isDuplicatedId(userid);
	}

	@Override
	public List<String> findIdByEmail(String email) throws Exception {
		return memberMapper.findIdByEmail(email);
	}

	@Override
	public boolean emailCheck(String email) throws Exception {
		return memberMapper.emailCheck(email) != 0;
	}

	@Override
	public boolean findPasswordCheck(MemberDto memberDto) throws Exception {
		return memberMapper.findPasswordCheck(memberDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean findPassword(MemberDto memberDto) throws Exception {
		if(memberMapper.findPasswordCheck(memberDto) == 1) { // 아이디 이메일 매칭 체크 (유효성 체크)
			String email = memberDto.getEmail().trim();
			String userid = memberDto.getUserid().trim();
			System.out.println(email);
	        MailDto dto = sendEmailService.createMailAndChangePassword(email, userid);
	        sendEmailService.mailSend(dto);
			return true;
		} 
		return false;
	}

	@Override
	public boolean curPwdCheck(Map<String, String> params) throws Exception {
		String userid = params.get("userid");
		String curpwd = params.get("curpwd");
		MemberDto user = memberMapper.findById(userid); // 입력받은 아이디정보로 user 정보 찾기
		if(!passwordEncoder.matches(curpwd, user.getUserpwd())){ 
			System.out.println("비밀번호 불일치");
			return false; //  비밀번호가 일치하지 않으면 false을 반환
		}
		System.out.println("비밀번호 일치");
		return true;
	}
}
