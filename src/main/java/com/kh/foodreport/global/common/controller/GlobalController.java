package com.kh.foodreport.global.common.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.place.model.dto.PlaceResponse;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;
import com.kh.foodreport.global.common.ApiResponse;
import com.kh.foodreport.global.common.model.service.GlobalService;
import com.kh.foodreport.global.region.model.dto.RegionDTO;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/global")
@RequiredArgsConstructor
public class GlobalController {

	private final GlobalService globalService;
	
	@GetMapping("/searchReviews")
	public ResponseEntity<ApiResponse<ReviewResponse>> findAllReviewsByKeyword(@RequestParam(name="keyword" , defaultValue = "") String keyword
																			  , @RequestParam(name="page" , defaultValue = "1") int page
																			  , @RequestParam(name="tagNo", defaultValue = "0") Long tagNo
																			  , @RequestParam(name="regionNo", defaultValue= "0") Long regionNo){

		
		ReviewResponse response = globalService.findAllReviewsByKeyword(keyword, page, tagNo, regionNo);
		
		return ApiResponse.ok(response, "리뷰 조회에 성공하였습니다.");
	}
	
	@GetMapping("/searchPlaces")
	public ResponseEntity<ApiResponse<PlaceResponse>> findAllPlacesByKeyword(@RequestParam(name="keyword" , defaultValue = "") String keyword
																			, @RequestParam(name="page" , defaultValue = "1") int page
																			, @RequestParam(name="tagNo", defaultValue = "0") Long tagNo
																			, @RequestParam(name="regionNo", defaultValue = "0") Long regionNo){
		PlaceResponse response = globalService.findAllPlacesByKeyword(keyword, page, tagNo, regionNo);

		return ApiResponse.ok(response, "맛집 조회에 성공하였습니다.");
	}
	
	@GetMapping("/tags")
	public ResponseEntity<ApiResponse<List<TagDTO>>> findAllTags(){
		
		List<TagDTO> tags = globalService.findAllTags();
		
		return ApiResponse.ok(tags, "태그 조회 성공");
		
	}
	
	@GetMapping("/regions")
	public ResponseEntity<ApiResponse<List<RegionDTO>>> findAllRegions(){
		
		List<RegionDTO> regions = globalService.findAllRegions();
		
		return ApiResponse.ok(regions, "지역 조회 성공");
	}
}
