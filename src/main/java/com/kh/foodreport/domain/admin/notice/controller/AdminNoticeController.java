package com.kh.foodreport.domain.admin.notice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeResponse;
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
		
		return ApiResponse.created("공지사항 등록에 성공하였습니다.");
	}

	@GetMapping // 전체 공지사항 조회
	public ResponseEntity<ApiResponse<AdminNoticeResponse>> findAllNotices(@RequestParam(name="page", defaultValue = "1") int page) {
		
		AdminNoticeResponse response = noticeService.findAllNotices(page);
		
		return ApiResponse.ok(response,"공지사항 조회에 성공하였습니다.");
	}
	
	
	@GetMapping("/keyword") // 공지사항 제목 키워드로 조회
	public ResponseEntity<ApiResponse<AdminNoticeResponse>> findByNoticeTitle(@RequestParam(name="page", defaultValue = "1") int page
																		     ,@RequestParam(name="noticeTitle")String noticeTitle) {
		
		AdminNoticeResponse response = noticeService.findByNoticeTitle(page,noticeTitle);
		
		return ApiResponse.ok(response,"공지사항 조회에 성공하였습니다.");
	}
	
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<ApiResponse<Object>> deleteNotice(@PathVariable(name="noticeNo") Long noticeNo) {
		
		noticeService.deleteNotice(noticeNo);
		
		return ApiResponse.noContent();
	}
	
}
