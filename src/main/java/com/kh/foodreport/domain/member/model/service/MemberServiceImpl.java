package com.kh.foodreport.domain.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.member.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public void signUp(MemberDTO member) {
		
		// 유효성 검사  ==> Validator에게 위임
		
		// 아이디 중복 검사
		
		// 비밀번호 암호화
		
		// ROLE 주기
		
		// 매퍼 호출

	}

}
