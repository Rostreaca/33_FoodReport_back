package com.kh.foodreport.global.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.foodreport.global.common.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {
	
	private ResponseEntity<Map<String, String>> createResponseEntity(RuntimeException e, HttpStatus status){
		Map<String, String> error = new HashMap();
		error.put("error-message", e.getMessage());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<Map<String, String>> handleAuth(CustomAuthenticationException e){
		return createResponseEntity(e, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handlerUsernameNotFound(UsernameNotFoundException e) {
		Map<String, String> error = new HashMap();
		error.put("error-message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(EmailDuplicateException.class)
	public ResponseEntity<?> handlerDuplicateEmail(EmailDuplicateException e) {
		Map<String, String> error = new HashMap();
		error.put("error-message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

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
	
	// 리뷰 생성 실패 예외
	@ExceptionHandler(ReviewCreationException.class)
	public ResponseEntity<ApiResponse<Object>> handleReviewCreationException(ReviewCreationException e){
		log.error("잘못된 상태 : {}", e.getMessage());
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 파일 업로드 예외
	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<ApiResponse<Object>> handleFileUploadException(FileUploadException e) {
		log.error("잘못된 상태 : {}", e.getMessage());
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}

	// 페이징 처리 예외
	@ExceptionHandler(PageNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handlePageNotFoundException(PageNotFoundException e) {
		log.error("잘못된 상태 : {}", e.getMessage());
		return createErrorResponseEntity(e, HttpStatus.NOT_FOUND);
	}
}
