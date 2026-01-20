package com.kh.foodreport.domain.review.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.service.ReviewReplyService;
import com.kh.foodreport.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/reviews/replies")
@RequiredArgsConstructor
public class ReviewReplyController {

	private final ReviewReplyService reviewReplyService;
	
	@PutMapping("/{replyNo}")
	public ResponseEntity<ApiResponse<Void>> updateReply(@PathVariable(name="replyNo") Long replyNo,@RequestBody ReviewReplyDTO reviewReply
													   , @AuthenticationPrincipal CustomUserDetails user){
		
		reviewReplyService.updateReply(replyNo ,reviewReply, user.getMemberNo());
		
		return ApiResponse.ok(null, "댓글 수정에 성공하셨습니다.");
	}
	
	@DeleteMapping("/{replyNo}")
	public ResponseEntity<ApiResponse<Void>> deleteReply(@PathVariable(name="replyNo") Long replyNo, @AuthenticationPrincipal CustomUserDetails user){
		
		reviewReplyService.deleteReply(replyNo, user.getMemberNo());
		
		return ApiResponse.ok(null, "댓글 삭제에 성공하셨습니다.");
	}
	
	@PostMapping("/{replyNo}/likes")
	public ResponseEntity<ApiResponse<Void>> saveReplyLike(@PathVariable(name = "replyNo") Long replyNo, @AuthenticationPrincipal CustomUserDetails user){
		
		return ApiResponse.created("댓글 좋아요 등록에 성공하셨습니다.");
		
	}
	
	
}
