package com.kh.foodreport.domain.member.model.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.member.model.dao.MemberMapper;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.vo.MemberVO;
import com.kh.foodreport.global.exception.EmailDuplicateException;
import com.kh.foodreport.global.exception.SignUpFailedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void signUp(MemberDTO member) {
		
		// 유효성 검사  ==> Validator에게 위임
		// 이메일 중복 검사
		int count = memberMapper.countByEmail(member.getEmail());
		
		if(1 == count) {
			throw new EmailDuplicateException("이미 존재하는 이메일입니다.");
		}
		// 비밀번호 암호화
		
		// ROLE 주기
		MemberVO memberBuilder = MemberVO.builder()
										 .email(member.getEmail())
										 .password(passwordEncoder.encode(member.getPassword()))
										 .nickname(member.getNickname())
										 .phone(member.getPhone())
										 .role("ROLE_USER")
										 .build();

		// 매퍼 호출
		int result = memberMapper.signUp(memberBuilder);
		log.info("사용자 등록 성공 : {} ", memberBuilder);
		 
		if(0 == result) {
			throw new SignUpFailedException("회원가입에 실패했습니다.");
		}

	}

}
