package com.kh.foodreport.domain.member.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	@Insert("INSERT INTO FR_MEMBER VALUES(SEQ_MEMBER_NO.NEXTVAL, #{email}, #{password}, #{nickname}, #{phone}, #{introduce}, SYSDATE, NULL, NULL, 'Y', 'ROLE_USER')")
	int signUp(MemberVO member);
	
	@Select("SELECT COUNT(*) FROM FR_MEMBER WHERE EMAIL = #{email}")
	int countByEmail(String email);
	
	@Select("""
			SELECT 
				   MEMBER_NO memberNo
				 , EMAIL email
				 , PASSWORD password
				 , NICKNAME nickname
				 , PHONE phone
				 , status
				 , role
			  FROM 
			       FR_MEMBER
			 WHERE 
			       EMAIL = #{username}
		""")
	MemberDTO loadUser(String username);


}
