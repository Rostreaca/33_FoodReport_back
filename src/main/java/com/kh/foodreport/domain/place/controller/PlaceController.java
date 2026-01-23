package com.kh.foodreport.domain.place.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceResponse;
import com.kh.foodreport.domain.place.model.service.PlaceService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/places")
@RestController
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<PlaceResponse>> findAllPlaces(@RequestParam(name="page", defaultValue = "1") int page
																  , @RequestParam(name="keyword", defaultValue = "") String keyword
																  , @RequestParam(name="order", defaultValue = "createDate") String order){
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("keyword", keyword);
		params.put("order", order);
		
		PlaceResponse response = placeService.findAllPlaces(page, params);
		
		return ApiResponse.ok(response, "전체 조회에 성공했습니다.");
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> savePlace(@ModelAttribute PlaceDTO place
													 , @RequestParam(name = "tagNums") List<Long> tagNums
													 , @RequestParam(name="images", required = false) List<MultipartFile> images
													 , @AuthenticationPrincipal CustomUserDetails user){
		
		place.setPlaceWriter(String.valueOf(user.getMemberNo()));
		
		placeService.savePlace(place, tagNums, images);
		
		return ApiResponse.created("맛집 게시글 작성 성공");
	}
	
	@GetMapping("/{placeNo}")
	public ResponseEntity<ApiResponse<PlaceDTO>> findPlaceByPlaceNo(@PathVariable(name="placeNo") Long placeNo){
		
		PlaceDTO place = placeService.findPlaceByPlaceNo(placeNo);
		
		return ApiResponse.ok(place, "상세 조회에 성공했습니다.");
	}
	
}
