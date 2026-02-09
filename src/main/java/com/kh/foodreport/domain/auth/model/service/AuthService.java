package com.kh.foodreport.domain.auth.model.service;

import java.util.Map;

import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.token.model.dto.RefreshTokenDTO;

import jakarta.validation.Valid;

public interface AuthService {
	
	Map<String, String> login(@Valid MemberDTO member);
	
	void logout(RefreshTokenDTO refreshToken);

}