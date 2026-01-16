package com.kh.foodreport.domain.admin.tag.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagDTO;
import com.kh.foodreport.domain.admin.tag.model.service.AdminTagService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/admin/tags")
public class AdminTagController {
	
	private final AdminTagService tagService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> createTag(@RequestBody AdminTagDTO tag) {
		
		tagService.saveTag(tag);
		
		return ApiResponse.created("태그가 생성되었습니다.");
	}
	
}
