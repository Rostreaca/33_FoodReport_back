package com.kh.foodreport.domain.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;
import com.kh.foodreport.domain.review.model.service.ReviewService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

	private final ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<String>> saveReview(@ModelAttribute ReviewDTO review, @RequestParam(name = "tagNums", required = false) List<Long> tagNums, @RequestParam(name = "images", required = false) List<MultipartFile> images, @AuthenticationPrincipal CustomUserDetails user,@RequestParam(name="regionNo", required=false) Long regionNo){
		
		review.setReviewWriter(String.valueOf(user.getMemberNo()));
		
		reviewService.saveReview(review, tagNums, images, regionNo);
		
		return ApiResponse.created("생성완료");
		
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<ReviewResponse>> findAllReviews(@RequestParam(name="page", defaultValue = "1") int page
																	, @RequestParam(name="keyword", defaultValue = "") String keyword
																	, @RequestParam(name="order", defaultValue = "createDate") String order
																	, @RequestParam(name="tagNo", defaultValue="0") Long tagNo
																	, @RequestParam(name="regionNo", defaultValue = "0")Long regionNo){
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("regionNo", regionNo);
		params.put("tagNo", tagNo);
		params.put("keyword", keyword);
		params.put("order", order);
		
		ReviewResponse response = reviewService.findAllReviews(page,params);
		
		return ApiResponse.ok(response, "전체 조회 성공");
	}
	
	@GetMapping("/{reviewNo}")
	public ResponseEntity<ApiResponse<ReviewDTO>> findByReviewNo(@PathVariable(name = "reviewNo" ) Long reviewNo){
		
		ReviewDTO response = reviewService.findReviewByReviewNo(reviewNo);
		
		return ApiResponse.ok(response, "상세 조회 성공");
	}
	
	@PutMapping("/{reviewNo}")
	public ResponseEntity<ApiResponse<Void>> updateReview(@PathVariable(name = "reviewNo") Long reviewNo
														, @ModelAttribute ReviewDTO review
														, @RequestParam(name = "tagNums", required = false) List<Long> tagNums
														, @RequestParam(name = "images", required = false) List<MultipartFile> images
														, @AuthenticationPrincipal CustomUserDetails user){
		
		review.setReviewNo(reviewNo);
		
		review.setReviewWriter(String.valueOf(user.getMemberNo()));
		
		reviewService.updateReview(review,tagNums,images);
		
		return ApiResponse.ok(null, "리뷰 변경에 성공했습니다.");
	}
	
	@DeleteMapping("/{reviewNo}")
	public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable(name = "reviewNo") Long reviewNo){
		
		reviewService.deleteReview(reviewNo);
		
		return ApiResponse.ok(null, "리뷰 삭제에 성공했습니다.");
		
	}
	
	@PostMapping("/{reviewNo}/replies")
	public ResponseEntity<ApiResponse<Void>> saveReply(@PathVariable(name = "reviewNo") Long reviewNo, @RequestBody ReviewReplyDTO reply, @AuthenticationPrincipal CustomUserDetails user) {
		
		reply.setReplyWriter(String.valueOf(user.getMemberNo()));
		
		reviewService.saveReply(reviewNo ,reply);
		
		return ApiResponse.created("댓글 등록에 성공했습니다.");
	}
	
	@PostMapping("/{reviewNo}/likes")
	public ResponseEntity<ApiResponse<Void>> saveLike(@PathVariable(name= "reviewNo") Long reviewNo, @AuthenticationPrincipal CustomUserDetails user){
		
		
		reviewService.saveLike(reviewNo, user.getMemberNo());
		
		return ApiResponse.created("좋아요 등록에 성공하였습니다.");
	}
	
	@DeleteMapping("/{reviewNo}/likes")
	public ResponseEntity<ApiResponse<Void>> deleteLike(@PathVariable(name= "reviewNo") Long reviewNo, @AuthenticationPrincipal CustomUserDetails user){
		
		reviewService.deleteLike(reviewNo, user.getMemberNo());
		
		return ApiResponse.ok(null,"좋아요를 취소하셨습니다.");
	}
	
}
