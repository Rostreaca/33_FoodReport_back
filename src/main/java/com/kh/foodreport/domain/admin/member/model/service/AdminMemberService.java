package com.kh.foodreport.domain.admin.member.model.service;

import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberResponse;

public interface AdminMemberService {
	
	AdminMemberResponse findAllMember(int page);

	AdminMemberResponse findByNickname(int page, String nickname);
	
	void deleteMember(Long memberNO);
	
	void updateMember(Long memberNo,String role);
}
