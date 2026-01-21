package com.kh.foodreport.domain.member.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.member.model.dto.ChangePasswordDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;

public interface MemberService {
	
	void signUp(MemberDTO member);
	
	void updatePassword(ChangePasswordDTO password);
	
	void deleteByPassword(String password);
	
	void saveImage(Long memberNo, MultipartFile image);
	
	MemberDTO findByMemberNo(Long memverNo);
	
	void updateMember(Long memberNo, MemberDTO updateMember, MultipartFile image);

}
