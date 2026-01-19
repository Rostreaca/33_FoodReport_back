package com.kh.foodreport.domain.admin.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewDTO;
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
	
	@GetMapping
	public ResponseEntity<ApiResponse<AdminReviewResponse>> findAllReviews(@RequestParam(name="page", defaultValue ="1") int page) {
		
		AdminReviewResponse reviews = reviewService.findAllReviews(page);
		
		return ApiResponse.ok(reviews, "리뷰 조회 성공!");
	}
}
