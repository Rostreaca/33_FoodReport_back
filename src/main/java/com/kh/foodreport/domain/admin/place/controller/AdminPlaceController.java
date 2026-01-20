package com.kh.foodreport.domain.admin.place.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceResponse;
import com.kh.foodreport.domain.admin.place.model.service.AdminPlaceService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/places")
@Slf4j
public class AdminPlaceController {
	
	private final AdminPlaceService placeService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<AdminPlaceResponse>> findAllPlace(@RequestParam(name="page", defaultValue ="1")int page) {
		
		AdminPlaceResponse response = placeService.findAllPlace(page);
		
		return ApiResponse.ok(response, "업장 전체 조회 성공!");
	}
	
}
