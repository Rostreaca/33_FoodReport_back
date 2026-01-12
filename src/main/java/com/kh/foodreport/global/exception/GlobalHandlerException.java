package com.kh.foodreport.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.foodreport.global.common.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

	/* 공통 응답 포맷 */
	private ResponseEntity<ApiResponse<Object>> createErrorResponseEntity(Exception e, HttpStatus status) {
		return ApiResponse.failure(e.getMessage(), status);
	}
	
	// 잘못된 상태 전달시
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalStateException e) {
		log.error("잘못된 상태 : {}", e.getMessage());
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 공지사항 못만들었을 때 예외 
	@ExceptionHandler(NoticeCreationException.class)
	public ResponseEntity<ApiResponse<Object>> handleNoticeCreationException(NoticeCreationException e) {
		log.error("잘못된 상태 : {}", e.getMessage());
		return createErrorResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 파일 업로드 예외
	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<ApiResponse<Object>> handleFileUploadException(FileUploadException e) {
		log.error("잘못된 상태 : {}", e.getMessage());
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}

}
