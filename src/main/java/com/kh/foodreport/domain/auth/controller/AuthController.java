package com.kh.foodreport.domain.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.auth.model.service.AuthService;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.global.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String,String>>> login(@Valid @RequestBody MemberDTO member){
		log.info("{}", member);
		Map<String, String> loginResponse = authService.login(member);
		return ApiResponse.ok(loginResponse, "로그인 성공했습니다.");
		// 서비스로부터 응답받은 맵을 다시 클라이언트에게 응답해줌
	}

}
