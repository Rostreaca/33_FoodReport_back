package com.kh.foodreport.domain.notice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.notice.model.dto.NoticeResponse;
import com.kh.foodreport.domain.notice.model.service.NoticeService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<NoticeResponse>> findAllNotices(@RequestParam(name="page", defaultValue = "1") int page){
		
		NoticeResponse response = noticeService.findAllNotices(page);
		
		return ApiResponse.ok(response, null);
	}
	
}
