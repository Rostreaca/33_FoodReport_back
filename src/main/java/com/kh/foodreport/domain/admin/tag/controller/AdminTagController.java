package com.kh.foodreport.domain.admin.tag.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagResponse;
import com.kh.foodreport.domain.admin.tag.model.service.AdminTagService;
import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.global.common.ApiResponse;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/admin/tags")
public class AdminTagController {
	
	private final AdminTagService tagService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> createTag(@RequestBody TagDTO tag) {
		
		tagService.saveTag(tag);
		
		return ApiResponse.created("태그가 생성되었습니다.");
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<AdminTagResponse>> findAllTag(@RequestParam(name="page", defaultValue = "1")int page) {
		
		AdminTagResponse tags = tagService.findAllTag(page);
		
		return ApiResponse.ok(tags, "태그 조회 성공하였습니다.");
	}
	
	@PutMapping("/{tagNo}")
	public ResponseEntity<ApiResponse<Void>> updateTag(@PathVariable(name="tagNo")Long tagNo 
												   ,@RequestBody TagDTO tag) {
		
		tagService.updateTag(tagNo,tag);
		
		return ApiResponse.ok(null, "태그 업데이트에 성공하였습니다.");
	}
	
	@DeleteMapping("/{tagNo}")
	public ResponseEntity<ApiResponse<Void>> deleteTag(@PathVariable(name="tagNo")Long tagNo) {
	
		tagService.deleteTag(tagNo);
		
		return ApiResponse.noContent();
	}
}
