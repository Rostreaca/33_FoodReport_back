package com.kh.foodreport.domain.token.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.token.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
	
	private final JwtUtil tokenUtil;
	
	public void generateToken(String username) {
		
		tokenUtil.getAccessToken(null);
		tokenUtil.getRefreshToken(null);
		
	}

}
