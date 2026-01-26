package com.kh.foodreport.global.common.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.global.common.ApiResponse;
import com.kh.foodreport.global.common.model.dto.BoardResponse;
import com.kh.foodreport.global.common.model.service.GlobalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/global")
@RequiredArgsConstructor
public class GlobalController {

	private final GlobalService globalService;
	
	@GetMapping("/searchList")
	public ResponseEntity<ApiResponse<BoardResponse>> findAllByKeyword(@RequestParam(name="keyword") String keyword){
		
		BoardResponse response = globalService.findAllByKeyword(keyword);
		
		return ApiResponse.ok(response, "검색 결과 조회 완료.");
	}
	
	@GetMapping("/searchReviews")
	public ResponseEntity<ApiResponse<List<ReviewDTO>>> findAllReviewsByKeyword(@RequestParam(name="keyword") String keyword
																				, @RequestParam(name="offset") int offset){
		List<ReviewDTO> response = globalService.findAllReviewsByKeyword(keyword, offset);
		
		return ApiResponse.ok(response, "추가 조회 성공");
	}
	
	@GetMapping("/searchPlaces")
	public ResponseEntity<ApiResponse<List<PlaceDTO>>> findAllPlacesByKeyword(@RequestParam(name="keyword") String keyword
																			, @RequestParam(name="offset") int offset){
		List<PlaceDTO> response = globalService.findAllPlacesByKeyword(keyword, offset);

		return ApiResponse.ok(response, "추가 조회 성공");
}
}
