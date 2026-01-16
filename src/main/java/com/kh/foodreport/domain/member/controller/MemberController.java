package com.kh.foodreport.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.member.model.dto.ChangePasswordDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.service.MemberService;
import com.kh.foodreport.global.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody MemberDTO member){
		log.info("멤버 : {}", member);		
		memberService.signUp(member);
		return ApiResponse.created("회원가입에 성공했습니다.");
	}
	
	// 비밀번호 변경 기능 구현
	@PutMapping
	public ResponseEntity<ApiResponse<String>> changePassword(@Valid ChangePasswordDTO password){
		
		log.info("비밀번호 정보:{}", password);
		
		return null;
	}
	
}
