package com.ssafy.hh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.hh.dto.MemberDto;
import com.ssafy.hh.service.JwtService;
import com.ssafy.hh.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MemberService memberService;
	
	
	@ApiOperation(value = "모든 유저 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<MemberDto>> findAll() throws Exception{
		return new ResponseEntity<List<MemberDto>>(memberService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 유저의 정보를 반환한다.", response = MemberDto.class)
	@GetMapping("/{userid}")
	public ResponseEntity<Map<String, Object>> findById(@PathVariable("userid") String userid, HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.findById(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아이디가 중복되는지 확인하고 중복되면 success를, 중복안하면 fail을 반환한다.", response = String.class)
	@GetMapping("/id-check/{userid}")
	public ResponseEntity<String> isDuplicatedId(@PathVariable("userid") String userid) throws Exception {
		System.out.println(userid);
		System.out.println(memberService.isDuplicatedId(userid));
		if (userid.equals(memberService.isDuplicatedId(userid))) {
			System.out.println(1);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println(2);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "가입된 이메일인지 확인한다. 성공하면 success를, 실패하면 fail을 반환한다.", response = String.class)
	@GetMapping("/email-check/{email:.+}")
	public ResponseEntity<String> emailCheck(@PathVariable("email") String email) throws Exception {
		if(memberService.emailCheck(email)) {
			System.out.println(SUCCESS);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println(FAIL);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "비밀번호찾기시 아이디와 이메일 유효성검사. 성공하면 success를, 실패하면 fail을 반환한다.", response = String.class)
	@PostMapping("/find-pwd-check")
	public ResponseEntity<String> findPwdCheck(@RequestBody MemberDto memberDto) throws Exception {
		if(memberService.findPasswordCheck(memberDto)) {
			System.out.println(SUCCESS);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println(FAIL);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value = "임시 비밀번호 발급. 성공하면 success를, 실패하면 fail을 반환한다.", response = String.class)
	@PostMapping("/find-pwd")
	public ResponseEntity<String> findPwd(@RequestBody MemberDto memberDto) throws Exception {	
		if(memberService.findPassword(memberDto)) { // 임시 비밀번호 업데이트가 됐으면
			System.out.println(SUCCESS);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} 
		System.out.println(FAIL);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "이메일에 해당하는 아이디 리스트를 반환한다. 성공하면 아이디 리스트를, 실패하면 fail을 반환한다.", response = String.class)
	@GetMapping("/find-id/{email:.+}")
	public ResponseEntity<?> findIdByEmail(@PathVariable("email") String email) throws Exception{
		List idList = memberService.findIdByEmail(email);
		if(idList != null) {
			return new ResponseEntity<List<String>>(idList, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "로그인한다. 그리고 Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/signin")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.signIn(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				System.out.println(1);
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "새로운 회원을 등록한다. 그리고 회원등록 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody MemberDto memberDto) throws Exception{
		if (memberService.signUp(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value = "비밀번호를 제외한 회원 정보를 수정한다. 그리고 회원수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping("/info")
	public ResponseEntity<String> updateMember(@RequestBody MemberDto memberDto) throws Exception{
		if (memberService.updateMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); 
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "비밀번호를 수정한다. 그리고 비밀번호수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping("/password")
	public ResponseEntity<String> updatePassword(@RequestBody MemberDto memberDto, HttpServletRequest request) throws Exception{
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				if (memberService.updatePassword(memberDto)) {
					msg = SUCCESS;
					status = HttpStatus.OK;
				} 
			} catch (Exception e) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			msg = FAIL;
			status = HttpStatus.ACCEPTED;
		}		
		return new ResponseEntity<String>(msg, status);
	}
	
	@ApiOperation(value = "회원 정보를 삭제한다. 그리고 회원삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteMember(@PathVariable("userid") String userid) throws Exception{
		if (memberService.deleteMember(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); 
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "현재 입력한 비밀번호를 체크한다. 그리고 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/current-pwd-check")
	public ResponseEntity<String> curPwdCheck(@RequestBody Map<String, String> params) throws Exception {
		if(memberService.curPwdCheck(params)) {
			System.out.println(SUCCESS);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		System.out.println(FAIL);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}
