package com.kh.foodreport.domain.admin.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewResponse;
import com.kh.foodreport.domain.admin.review.model.service.AdminReviewService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/reviews")
public class AdminReviewController {
	
	private final AdminReviewService reviewService;
	
	@GetMapping // 전체 리뷰 목록 조회
	public ResponseEntity<ApiResponse<AdminReviewResponse>> findAllReviews(@RequestParam(name="page", defaultValue ="1") int page) {
		
		AdminReviewResponse reviews = reviewService.findAllReviews(page);
		
		return ApiResponse.ok(reviews, "리뷰 전체 조회 성공!");
	}
	
	@GetMapping("/keyword")
	public ResponseEntity<ApiResponse<AdminReviewResponse>> findByReviewTitle(@RequestParam(name="page", defaultValue ="1") int page
																			,@RequestParam(name="reviewTitle")String reviewTitle) {
		
		AdminReviewResponse reviews = reviewService.findByReviewTitle(page, reviewTitle);
		
		return ApiResponse.ok(reviews, "리뷰 제목 조회 성공!");
	}
	
	@DeleteMapping("/{reviewNo}")
	public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable(name="reviewNo")Long reviewNo) {
		
		reviewService.deleteReview(reviewNo);
		
		return ApiResponse.noContent();
	}
	
}
