package com.kh.foodreport.domain.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.token.model.dao.TokenMapper;
import com.kh.foodreport.domain.token.model.dto.RefreshTokenDTO;
import com.kh.foodreport.domain.token.model.vo.RefreshToken;
import com.kh.foodreport.domain.token.util.JwtUtil;
import com.kh.foodreport.global.exception.CustomAuthenticationException;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
	
	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;
	
	public Map<String, String> generateToken(String memberNo) {
		Map<String, String>tokens = createTokens(memberNo);
		saveToken(tokens.get("refreshToken"), memberNo);
		return tokens;
	}
	
	private Map<String, String> createTokens(String memberNo) {
		String accessToken = tokenUtil.getAccessToken(memberNo);
		String refreshToken = tokenUtil.getRefreshToken(memberNo);
		Map<String, String> tokens = new HashMap();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		return tokens;
	}
	
	private void saveToken(String refreshToken, String memberNo) {
		RefreshToken token = RefreshToken.builder()
										 .token(refreshToken)
										 .memberNo(memberNo)
										 .expiration(System.currentTimeMillis() + 3600000L * 72)
										 .build();	
		
		int result = tokenMapper.saveToken(token);
		if(result == 0) {
			throw new CustomAuthenticationException("토큰 저장 실패");
		}
	}
	
	public Map<String, String> validateToken(String refreshToken) {
		RefreshToken token = tokenMapper.findByToken(refreshToken);
		if(token == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new CustomAuthenticationException("CustomAuthenticationException");
		}
		Claims claims = tokenUtil.parseJwt(refreshToken);
		String memberNo = claims.getSubject();
		return createTokens(memberNo);
	}
	
	public int deleteToken(Long memberNo) {
	    return tokenMapper.deleteToken(memberNo);
	}

	public void deleteTokenByRefreshTokenDTO(RefreshTokenDTO refreshToken) {
	    tokenMapper.deleteTokenByRefreshTokenDTO(refreshToken);
	}
	
	

}
