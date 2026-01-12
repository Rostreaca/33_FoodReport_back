package com.kh.foodreport.domain.admin.notice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.service.AdminNoticeService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/admin/notices")
@RequiredArgsConstructor
public class AdminNoticeController {

	private final AdminNoticeService noticeService;
	
	@PostMapping // 공지사항 등록
	public ResponseEntity<ApiResponse<String>> saveNotice(@ModelAttribute AdminNoticeDTO notice
										,@RequestParam(name="file") MultipartFile file) {
		
		noticeService.saveNotice(notice, file);
		
		return ApiResponse.created("공지사항 등록에 성공하였습니다");
	}

}
