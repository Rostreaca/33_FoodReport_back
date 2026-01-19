package com.kh.foodreport.domain.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<ApiResponse<String>> changePassword(@Valid @RequestBody ChangePasswordDTO password){
		
		// 1. 비밀번호 입력값에 대한 유효성 검증
		log.info("비밀번호 정보:{}", password);
		// 2. 기존 비밀번호 일치 여부
		// 3. 새 비밀번호에 대한 암호화
		// 4. 새 비밀번호로 변경
		memberService.changePassword(password);
		return ApiResponse.created("비밀번호가 변경되었습니다.");
	}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse<String>> deleteByPassword(@RequestBody Map<String, String> request){
		log.info("확인 {}", request);
		memberService.deleteByPassword(request.get("password"));
		return ApiResponse.created("회원 탈퇴가 정상적으로 처리되었습니다.");
	}
	
}
