package com.kh.foodreport.domain.auth.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.auth.model.service.AuthService;
import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.token.model.dto.RefreshTokenDTO;
import com.kh.foodreport.domain.token.model.service.TokenService;
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
	private final TokenService tokenService;

	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String,String>>> login(@Valid @RequestBody MemberDTO member){
		log.info("{}", member);
		Map<String, String> loginResponse = authService.login(member);
		return ApiResponse.ok(loginResponse, "로그인 성공했습니다.");
		// 서비스로부터 응답받은 맵을 다시 클라이언트에게 응답해줌
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<Map<String,String>>> refresh(@RequestBody Map<String, String> token){
		String refreshToken = token.get("refreshToken");
		Map<String, String> tokens = tokenService.validateToken(refreshToken);
		return ApiResponse.created("토큰 재발급 성공했습니다.",tokens);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(@AuthenticationPrincipal CustomUserDetails user
												  , @RequestBody RefreshTokenDTO refreshToken){		
		refreshToken.setMemberNo(String.valueOf(user.getMemberNo()));
		
		authService.logout(refreshToken);
		return ApiResponse.ok(null, "로그아웃 성공했습니다");
	}
	

}