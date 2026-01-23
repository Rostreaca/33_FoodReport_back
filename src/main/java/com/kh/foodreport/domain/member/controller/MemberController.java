package com.kh.foodreport.domain.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.member.model.dto.ChangePasswordDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.dto.MemberReviewResponse;
import com.kh.foodreport.domain.member.model.dto.RestaurantDTO;
import com.kh.foodreport.domain.member.model.service.MemberService;
import com.kh.foodreport.global.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping // 회원가입
	public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody MemberDTO member){
		log.info("멤버 : {}", member);		
		memberService.signUp(member);
		return ApiResponse.created("회원가입에 성공했습니다.");
	}

	@PutMapping	// 비밀번호 변경
	public ResponseEntity<ApiResponse<String>> updatePassword(@Valid @RequestBody ChangePasswordDTO password){
		// 1. 비밀번호 입력값에 대한 유효성 검증
		log.info("비밀번호 정보:{}", password);
		// 2. 기존 비밀번호 일치 여부
		// 3. 새 비밀번호에 대한 암호화
		// 4. 새 비밀번호로 변경
		memberService.updatePassword(password);
		return ApiResponse.ok(null, "비밀번호가 변경되었습니다.");
	}
	
	@DeleteMapping // 회원 탈퇴
	public ResponseEntity<ApiResponse<String>> deleteByPassword(@RequestBody Map<String, String> request){
		log.info("확인 {}", request);
		memberService.deleteByPassword(request.get("password"));
		return ApiResponse.ok(null, "회원 탈퇴가 정상적으로 처리되었습니다.");
	}
	
	@PostMapping("/images") // 프로필 이미지 등록
	public ResponseEntity<ApiResponse<String>> saveImage(@RequestParam(name="image", required = false) MultipartFile image, @AuthenticationPrincipal CustomUserDetails user) {		
		memberService.saveImage(user.getMemberNo(), image);		
		return ApiResponse.created("프로필 사진 등록에 성공하였습니다.");	
	}
	
	@GetMapping("/info") // 마이페이지 - 회원 정보 조회
	public ResponseEntity<ApiResponse<String>> findByMemberNo(@AuthenticationPrincipal CustomUserDetails user){
		MemberDTO response = memberService.findByMemberNo(user.getMemberNo());
		return ApiResponse.ok(response, "내 정보 조회 성공");
	}
	
	@PutMapping("/info") // 마이페이지 - 회원 정보 수정
	public ResponseEntity<ApiResponse<String>> updateMember(@AuthenticationPrincipal CustomUserDetails user
														   ,@ModelAttribute MemberDTO memberDTO
														   ,@RequestParam(name="file" , required = false) MultipartFile image){
		memberService.updateMember(user.getMemberNo(), memberDTO, image);
		return ApiResponse.ok(null, "회원 정보가 변경되었습니다.");
	}
	
	@GetMapping("/reviews") // 마이페이지 - 회원 리뷰 전체 조회
	public ResponseEntity<ApiResponse<MemberReviewResponse>> findAllReviews(@AuthenticationPrincipal CustomUserDetails user
																		   ,@RequestParam(name="page", defaultValue ="1") int page) {
		MemberReviewResponse reviews = memberService.findAllReviews(page, user.getMemberNo());
		return ApiResponse.ok(reviews, "리뷰 전체 조회 성공");
	}
	
	@PostMapping("/info") // 사장님 등록
	public ResponseEntity<ApiResponse<String>> saveOwner(@Valid @RequestBody RestaurantDTO restaurant
														,@AuthenticationPrincipal CustomUserDetails user){
		memberService.saveOwner(restaurant, user.getMemberNo());
		return ApiResponse.created("사장님 등록에 성공했습니다.");
	}
	
	
	

}
