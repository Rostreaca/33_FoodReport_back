package com.kh.foodreport.domain.token.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {
	
	// 토큰 저장
	int saveToken(RefreshToken token);
	
	// 토큰 삭제
	int deleteToken(Long memberNo);
	
	// 토큰 조회
	RefreshToken findByToken(String refreshToken);

}