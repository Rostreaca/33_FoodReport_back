package com.kh.foodreport.domain.admin.place.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceResponse;
import com.kh.foodreport.domain.admin.place.model.service.AdminPlaceService;
import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
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
	
	@GetMapping("/keyword")
	public ResponseEntity<ApiResponse<AdminPlaceResponse>> findByPlaceTitle(@RequestParam(name="placeTitle")String placeTitle,
																			@RequestParam(name="page") int page) {
		
		AdminPlaceResponse response = placeService.findByPlaceTitle(page, placeTitle);
		
		return ApiResponse.ok(response, "업장 제목 조회 성공!");
	}
	
	@DeleteMapping("/{placeNo}")
	public ResponseEntity<ApiResponse<Void>> deletePlace(@PathVariable(name="placeNo")Long placeNo) {
		
		placeService.deletePlace(placeNo);
		
		return ApiResponse.noContent();
	}
	
	@PutMapping("/{placeNo}")
	public ResponseEntity<ApiResponse<Void>> updatePlace(@PathVariable(name="placeNo")Long placeNo) {
		
		placeService.updatePlace(placeNo);
		
		return ApiResponse.ok(null, "업장 업데이트에 성공하였습니다.");
	}
	
}
