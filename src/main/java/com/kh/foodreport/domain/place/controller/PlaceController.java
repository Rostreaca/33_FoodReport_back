package com.kh.foodreport.domain.place.controller;

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
import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
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
																  , @RequestParam(name="order", defaultValue = "createDate") String order
																  , @RequestParam(name="tagNo" , defaultValue = "0") Long tagNo
																  , @RequestParam(name="regionNo", defaultValue = "0") Long regionNo){
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("regionNo", regionNo);
		params.put("tagNo", tagNo);
		params.put("keyword", keyword);
		params.put("order", order);
		
		PlaceResponse response = placeService.findAllPlaces(page, params);
		
		return ApiResponse.ok(response, "전체 조회에 성공했습니다.");
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> savePlace(@ModelAttribute PlaceDTO place
													 , @RequestParam(name = "tagNums", required = false) List<Long> tagNums
													 , @RequestParam(name="images", required = false) List<MultipartFile> images
													 , @AuthenticationPrincipal CustomUserDetails user
													 , @RequestParam(name="regionNo", required = false) Long regionNo){
		
		place.setPlaceWriter(String.valueOf(user.getMemberNo()));
		
		placeService.savePlace(place, tagNums, images, regionNo);
		
		return ApiResponse.created("맛집 게시글 작성 성공");
	}
	
	@GetMapping("/{placeNo}")
	public ResponseEntity<ApiResponse<PlaceDTO>> findPlaceByPlaceNo(@PathVariable(name="placeNo") Long placeNo){
		
		PlaceDTO place = placeService.findPlaceByPlaceNo(placeNo);
		
		return ApiResponse.ok(place, "상세 조회에 성공했습니다.");
	}
	
	@PutMapping("/{placeNo}")
	public ResponseEntity<ApiResponse<Void>> updatePlace(@PathVariable(name = "placeNo") Long placeNo
													   , @ModelAttribute PlaceDTO place
													   , @RequestParam(name = "tagNums", required = false) List<Long> tagNums
													   , @RequestParam(name = "images", required = false) List<MultipartFile> images
													   , @RequestParam(name="regionNo", required=false) Long regionNo
													   , @AuthenticationPrincipal CustomUserDetails user){
		
		place.setPlaceNo(placeNo);
		
		place.setPlaceWriter(String.valueOf(user.getMemberNo()));
		
		placeService.updatePlace(place, tagNums, images, regionNo);
		
		return ApiResponse.ok(null, "맛집 게시글 수정에 성공했습니다.");
		
	}
	
	@DeleteMapping("/{placeNo}")
	public ResponseEntity<ApiResponse<Void>> deletePlace(@PathVariable(name = "placeNo") Long placeNo){
		
		placeService.deletePlace(placeNo);
		
		return ApiResponse.ok(null, "맛집 게시글 삭제에 성공했습니다.");
	}
	
	@PostMapping("/{placeNo}/replies")
	public ResponseEntity<ApiResponse<Void>> saveReply(@PathVariable(name = "placeNo") Long placeNo, @RequestBody PlaceReplyDTO reply, @AuthenticationPrincipal CustomUserDetails user){
		
		reply.setReplyWriter(String.valueOf(user.getMemberNo()));
		
		placeService.saveReply(placeNo, reply);
		
		return ApiResponse.created("댓글 작성에 성공했습니다.");
		
	}
	
	@PostMapping("/{placeNo}/likes")
	public ResponseEntity<ApiResponse<Void>> saveLike(@PathVariable(name="placeNo") Long placeNo, @AuthenticationPrincipal CustomUserDetails user){
		
		placeService.saveLike(placeNo, user.getMemberNo());
		
		return ApiResponse.created("게시글에 좋아요를 누르셨습니다.");
		
	}
	
	@DeleteMapping("/{placeNo}/likes")
	public ResponseEntity<ApiResponse<Void>> deleteLike(@PathVariable(name="placeNo") Long placeNo, @AuthenticationPrincipal CustomUserDetails user){
		placeService.deleteLike(placeNo, user.getMemberNo());
		
		return ApiResponse.ok(null, "좋아요를 취소하셨습니다.");
	}
	
}
