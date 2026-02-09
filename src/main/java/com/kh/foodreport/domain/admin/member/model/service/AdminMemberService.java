package com.kh.foodreport.domain.admin.member.model.service;

import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberPlaceResponse;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberResponse;

public interface AdminMemberService {
	
	AdminMemberResponse findAllMember(int page);

	AdminMemberResponse findByNickname(int page, String nickname);
	
	void deleteMember(Long memberNO);
	
	void grantMember(Long memberNo, String role);
	
	void updateMember(Long memberNo);
	
	AdminMemberPlaceResponse findByMemberPlace(int page);
}
