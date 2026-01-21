package com.kh.foodreport.domain.admin.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberPlaceResponse;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberResponse;
import com.kh.foodreport.domain.admin.member.model.service.AdminMemberService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/members")
public class AdminMemberController {
	
	private final AdminMemberService memberService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<AdminMemberResponse>> findAllMember(@RequestParam(name="page")int page) {
		
		AdminMemberResponse response = memberService.findAllMember(page);
		
		return ApiResponse.ok(response, "유저 전체 조회에 성공하였습니다.");
	}
	
	@GetMapping("/keyword")
	public ResponseEntity<ApiResponse<AdminMemberResponse>> findByNickname(@RequestParam(name="page")int page,
													    @RequestParam(name="nickname")String nickname) {
		
		AdminMemberResponse response = memberService.findByNickname(page, nickname);
		
		return ApiResponse.ok(response, "유저 이름 조회에 성공하였습니다.");
	}
	
	@DeleteMapping("/{memberNo}")
	public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable(name="memberNo")Long memberNo) {
		
		memberService.deleteMember(memberNo);
		
		return ApiResponse.noContent();
	}
	
	@PutMapping("/{memberNo}")
	public ResponseEntity<ApiResponse<Void>> updateMember(@PathVariable(name="memberNo")Long memberNo
														 ,@RequestBody Map<String, String> role) {
		
		memberService.updateMember(memberNo, role.get("role"));
		
		return ApiResponse.ok(null, "회원 역할 변경에 성공하였습니다");
	}
	
	@GetMapping("/place") // 회원 업장 등록된거 확인하는 메소드
	public ResponseEntity<ApiResponse<AdminMemberPlaceResponse>> findByMemberPlace(@RequestParam(name="page", defaultValue = "1")int page) {
		
		AdminMemberPlaceResponse response = memberService.findByMemberPlace(page);
		
		return ApiResponse.ok(response, "업장 조회에 성공하였습니다.");
	}
}
