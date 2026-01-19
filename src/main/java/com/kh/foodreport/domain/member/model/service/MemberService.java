package com.kh.foodreport.domain.member.model.service;

import com.kh.foodreport.domain.member.model.dto.ChangePasswordDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;

public interface MemberService {
	
	void signUp(MemberDTO member);
	
	void changePassword(ChangePasswordDTO password);
	
	void deleteByPassword(String password);

}
