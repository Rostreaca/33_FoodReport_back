package com.kh.foodreport.domain.admin.member.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.member.model.dao.AdminMemberMapper;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberDTO;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberPlaceDTO;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberPlaceResponse;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberResponse;
import com.kh.foodreport.domain.auth.model.vo.Role;
import com.kh.foodreport.global.exception.InvalidValueException;
import com.kh.foodreport.global.exception.MemberDeleteException;
import com.kh.foodreport.global.exception.MemberUpdateException;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminMemberServiceImpl implements AdminMemberService{

	private final AdminMemberMapper memberMapper;
	private final Pagenation pageNation;
	
	private AdminMemberResponse createFindResponse(List<AdminMemberDTO> members, Map<String, Object> pages) {
		AdminMemberResponse response = new AdminMemberResponse();
		
		response.setMember(members);
		response.setPageInfo((PageInfo)pages.get("pageInfo"));
		
		return response;
	}
	
	@Override
	public AdminMemberResponse findAllMember(int page) {
		
		GlobalValidator.validateNo(page, "0보다 작은값은 넣을 수 없습니다.");
		
		int listCount = memberMapper.countByMembers();
		
		Map<String, Object> pages = pageNation.getPageRequest(listCount, page, 10);

		List<AdminMemberDTO> members = memberMapper.findAllMember(pages);
		
		return createFindResponse(members, pages);
	}

	@Override
	public AdminMemberResponse findByNickname(int page, String nickname) {

		GlobalValidator.validateNo(page, "0보다 작은 값은 넣을 수 없습니다.");
		
		int listCount = memberMapper.countByNickname(nickname);
		
		Map<String, Object> pages = pageNation.getPageRequest(listCount, page, 10);
		
		pages.put("nickname", nickname);
		
		List<AdminMemberDTO> members = memberMapper.findByNickname(pages);
		
		return createFindResponse(members,pages);
	}

	@Override
	public void deleteMember(Long memberNo) {
		
		GlobalValidator.validateNo(memberNo, "0보다 작은 값은 들어올 수 없습니다.");
		
		int deleteResult = memberMapper.deleteMember(memberNo);
		
		if(deleteResult == 0) {
			throw new MemberDeleteException("회원 삭제에 실패하였습니다.");
		}
	}

	private void validateRole(String role) { // 역할 검증 메소드
		if("ROLE_ADMIN".equals(role)) {
			throw new IllegalArgumentException("관리자 권한은 부여할 수 없습니다.");
		}
		
		boolean isMatch = Arrays.stream(Role.values())
                .anyMatch(r -> r.name().equals(role));
		
		if(!isMatch) {
			throw new IllegalArgumentException("역할에 맞게 부여해주시길 바랍니다.");
		} 
	}
	
	@Override
	public void grantMember(Long memberNo, String role) {
		
		GlobalValidator.validateNo(memberNo, "0보다 작은값은 들어갈 수 없습니다.");
		
		validateRole(role); //  역할 값 검증
		
		Map<String, Object> params = new HashMap<>();
		params.put("memberNo", memberNo);
		params.put("role", role);
		int updateResult = memberMapper.grantMember(params);
		
		if(updateResult == 0) {
			throw new MemberUpdateException("회원 역할변경에 실패하였습니다.");
		}
	}

	@Override
	public AdminMemberPlaceResponse findByMemberPlace(int page) {
		
		GlobalValidator.validateNo(page, "0보다 작은 값은 들어갈 수 없습니다.");
		
		int listCount = memberMapper.countByMemberPlace();
		
		Map<String ,Object> pages= pageNation.getPageRequest(listCount, page, 10);
		
		List<AdminMemberPlaceDTO> places = memberMapper.findByMemberPlace(pages);
		
		AdminMemberPlaceResponse response = new AdminMemberPlaceResponse();
		response.setRestaurant(places);
		response.setPageInfo((PageInfo)pages.get("pageInfo"));
		
		return response;
	}

	@Override
	public void updateMember(Long memberNo) {
		GlobalValidator.validateNo(memberNo, "0보다 작은값은 들어갈 수 없습니다.");

		int result = memberMapper.updateMember(memberNo);
		
		if(result == 0) {
			throw new MemberUpdateException("회원 활성화에 실패하였습니다.");
		}
	}
	
	
	
}
