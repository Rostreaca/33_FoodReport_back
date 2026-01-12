package com.kh.foodreport.domain.member.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	@Insert("INSERT INTO FR_MEMBER VALUES(#{memberNo}, #{email}, #{password}, #{nickname}, #{phnoe}, #{introduce}, #{createDate}, #{updateDate}, #{deleteDate}, #{status}, #{role}")
	int signUp(MemberVO member);
	

}
