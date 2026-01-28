package com.kh.foodreport.domain.member.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.member.model.dto.ChangePasswordDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.dto.MemberReviewResponse;
import com.kh.foodreport.domain.member.model.dto.RestaurantDTO;

public interface MemberService {
	
	void signUp(MemberDTO member);
	
	void updatePassword(ChangePasswordDTO password);
	
	void deleteByPassword(String password);
	
	void saveImage(Long memberNo, MultipartFile image);
	
	MemberDTO findByMemberNo(Long memverNo);
	
	void updateMember(Long memberNo, MemberDTO updateMember, MultipartFile image);
	
	MemberReviewResponse findAllReviews(int page, Long memberNo);

	void saveOwner( RestaurantDTO restaurant, Long memberNo);

	Map<String, Object> findAllLikes(int page, Long memberNo, String likeType);

}
