package com.kh.foodreport.domain.admin.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.member.model.dao.AdminMemberMapper;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberDTO;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberResponse;
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
	
}
