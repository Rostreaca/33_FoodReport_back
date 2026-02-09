package com.kh.foodreport.domain.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.token.model.dto.RefreshTokenDTO;
import com.kh.foodreport.domain.token.model.service.TokenService;
import com.kh.foodreport.global.exception.CustomAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public Map<String, String> login(MemberDTO member) { // 로그인 구현
		// 사용자 인증
		Authentication auth = null;
		try {
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword()));
		} catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호를 확인하세요.");
		}		
		
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		log.info("로그인성공 !");
		log.info("인증에 성공한 사용자의 정보 : {}", user);
		
		// 토큰 발급
		// JWT라이브러리 이용 AccessToken, RefreshToken 만들어서 발급
		
		Map<String, String> loginResponse = tokenService.generateToken(String.valueOf(user.getMemberNo()));
		loginResponse.put("memberNo", String.valueOf(user.getMemberNo()));
		loginResponse.put("email", user.getUsername());
		loginResponse.put("nickname", user.getNickname());
		loginResponse.put("phone", user.getPhone());
		loginResponse.put("role", user.getAuthorities().toString());
		
		return loginResponse;
	}

	@Override
	public void logout(RefreshTokenDTO refreshToken) { // 로그아웃
	    // RefreshToken 삭제
	    tokenService.deleteTokenByRefreshTokenDTO(refreshToken);
	}

}
