package com.ssafy.hh.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.hh.dto.MailDto;
import com.ssafy.hh.mapper.MemberMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SendEmailService{

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public MailDto createMailAndChangePassword(String email, String userid) throws Exception{
    	StringBuilder sb = new StringBuilder();
        String str = getTempPassword().trim();
        String title = sb.append(userid).append("님의 HappyHouse 임시비밀번호 안내 이메일 입니다.").toString();
        String message = sb.append("\n").append("[").append(userid).append("]")
        		.append("님의 임시 비밀번호는").append(str).append(" 입니다.").toString();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(title);
        dto.setMessage(message);
        updatePassword(str,userid);
        return dto;
    }

    public void updatePassword(String str,String userid) throws Exception{
        String userpwd = passwordEncoder.encode(str); // 임시 비밀번호를 암호화
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("userpwd", userpwd); // 암호화한 비밀번호를 저장
        if(memberMapper.updateUserPassword(map) == 1) {
        	System.out.println("비밀번호 수정 성공");
        } else {
        	System.out.println("비밀번호 수정 실패");
        }
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        		'!', '@', '#', '$', '%', '^', '&'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    
    public void mailSend(MailDto mailDto){
        System.out.println("이메일 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}