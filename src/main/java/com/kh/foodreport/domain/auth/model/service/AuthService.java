package com.kh.foodreport.domain.auth.model.service;

import java.util.Map;

import com.kh.foodreport.domain.member.model.dto.MemberDTO;

import jakarta.validation.Valid;

public interface AuthService {
	
	Map<String, String> login(@Valid MemberDTO member);


}
