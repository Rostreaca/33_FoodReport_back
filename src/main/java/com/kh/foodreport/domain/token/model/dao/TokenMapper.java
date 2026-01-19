package com.kh.foodreport.domain.token.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {
	
	int saveToken(RefreshToken token);
	
	@Delete("DELETE FROM FR_MEMBER WHERE MEMBER_NO = #{memberNo}")
	void deleteToken(String Email);

}