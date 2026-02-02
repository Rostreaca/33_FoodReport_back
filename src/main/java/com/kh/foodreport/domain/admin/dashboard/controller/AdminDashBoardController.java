package com.kh.foodreport.domain.admin.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.admin.dashboard.model.service.AdminDashBoardService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/dashboards")
public class AdminDashBoardController {
	
	private final AdminDashBoardService boardService;
	
	public ResponseEntity<ApiResponse<?>> getDashBoard() {
		return ApiResponse.ok(boardService.getDashBoard(), "조회 성공!");
	}
	

}
