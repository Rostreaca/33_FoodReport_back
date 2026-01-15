package com.kh.foodreport.domain.token.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {
	
	@Insert("INSERT INTO FR_TOKEN VALUES(SEQ_TOKEN_NO.NEXTVAL, #{token}, #{expiration}, #{memberNo})")
	int saveToken(RefreshToken token);

}
