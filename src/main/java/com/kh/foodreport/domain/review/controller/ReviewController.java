package com.kh.foodreport.domain.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
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
	public ResponseEntity<ApiResponse<String>> saveReview(@ModelAttribute ReviewDTO review, @RequestParam(name = "images") List<MultipartFile> images){
		
		reviewService.saveReview(review, images);
		
		return ApiResponse.created("생성완료");
		
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Map<String,Object>>> findAllReviews(@RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="keyword", defaultValue = "") String keyword, @RequestParam(name="order", defaultValue = "createDate") String order){
		
		Map<String, Object> params = new HashMap();
		
		params.put("keyword", keyword);
		params.put("order", order);
		
		ReviewResponse response = reviewService.findAllReviews(page,params);
		
		return ApiResponse.ok(response, "전체 조회 성공");
	}
	
}
