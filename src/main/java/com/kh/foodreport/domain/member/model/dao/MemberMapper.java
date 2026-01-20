package com.kh.foodreport.domain.member.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.vo.MemberImage;
import com.kh.foodreport.domain.member.model.vo.MemberVO;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;

@Mapper
public interface MemberMapper {
	
	// 회원가입
	int signUp(MemberVO member);
	
	// 이메일 중복 확인
	int countByEmail(String email);
	
	// 로그인
	MemberDTO loadUser(String username);
	
	// 비밀번호 변경
	void updatePassword(Map<String, String> changeRequest);
		
	// 회원 탈퇴
	int deleteMember(String memberId);
	
	// 로그인 검증용
	MemberDTO loadUserByMemberNo(Long memberNo);
	
	// 프로필 이미지 저장
	int saveImage(MemberImage image);
	
	// 프로필 이미지 수정
	int updateImage(MemberImage image);
	
	// 프로필 이미지 삭제
	int deleteImage(Long imageNo);
	
	// 회원 정보 수정
	int updateMember(MemberDTO member);
	
	// 내 정보 조회
	MemberDTO findByMemberNo(Long memberNo);
	



}
