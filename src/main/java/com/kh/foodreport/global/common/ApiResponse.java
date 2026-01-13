package com.kh.foodreport.global.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse<T> {

	private boolean success;
	private String message;
	private Object data;

	// 200ok
	public static <T> ResponseEntity<ApiResponse<T>> ok(Object data, String message) {
		return ResponseEntity.ok(new ApiResponse<T>(true, message, data));
	}

	// 201created(메세지와 데이터)
	public static <T> ResponseEntity<ApiResponse<T>> created(String message, Object data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<T>(true, message, data));
	}

	// 201 201created(메세지만)
	public static <T> ResponseEntity<ApiResponse<T>> created(String message) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<T>(true, message, null));
	}

	// 204
	public static <T> ResponseEntity<ApiResponse<T>> noContent() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<T>(true, "삭제 완료", null));
	}

	// 실패 응답
	public static <T> ResponseEntity<ApiResponse<T>> failure(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(new ApiResponse<T>(false, message, null));
	}
}
