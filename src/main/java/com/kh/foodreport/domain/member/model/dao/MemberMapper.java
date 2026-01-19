package com.kh.foodreport.domain.member.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	// 회원가입
	int signUp(MemberVO member);
	
	// 이메일 중복 확인
	int countByEmail(String email);
	
	// 로그인
	MemberDTO loadUser(String username);
	
	// 회원 정보 수정
	void changePassword(Map<String, String> changeRequest);
		
	// 회원 탈퇴
	int deleteMember(String memberId);
	
	// 로그인 검증용
	MemberDTO loadUserByMemberNo(Long memberNo);


}
