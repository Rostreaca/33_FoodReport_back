package com.kh.foodreport.domain.review.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.review.model.dao.ReviewReplyMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.global.exception.CustomAuthenticationException;
import com.kh.foodreport.global.exception.ReplyUpdateException;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewReplyServiceImpl implements ReviewReplyService{

	private final ReviewReplyMapper reviewReplyMapper;

	@Override
	public void updateReply(Long replyNo, ReviewReplyDTO reviewReply, CustomUserDetails user) {
		
		GlobalValidator.validateNo(replyNo, "존재하지 않는 댓글입니다.");
		
		reviewReply.setReplyNo(replyNo);
		
		if(user == null) {
			throw new CustomAuthenticationException("로그인이 필요한 작업입니다.");
		}
		
		reviewReply.setReplyWriter(String.valueOf(user.getMemberNo()));
		
		int result = reviewReplyMapper.updateReply(reviewReply);
		
		if(result == 0) {
			throw new ReplyUpdateException("댓글 수정에 실패했습니다.");
		}
		
	}
	
	
	
	
}
