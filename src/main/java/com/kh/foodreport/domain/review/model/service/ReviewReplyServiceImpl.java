package com.kh.foodreport.domain.review.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.review.model.dao.ReviewReplyMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewReply;
import com.kh.foodreport.global.exception.ReplyDeleteException;
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
	public void updateReply(Long replyNo, ReviewReplyDTO reviewReply, Long memberNo) {
		
		GlobalValidator.validateNo(replyNo, "존재하지 않는 댓글입니다.");
		
		reviewReply.setReplyNo(replyNo);
		
		reviewReply.setReplyWriter(String.valueOf(memberNo));
		
		int result = reviewReplyMapper.updateReply(reviewReply);
		
		if(result == 0) {
			throw new ReplyUpdateException("댓글 수정에 실패했습니다.");
		}
		
	}

	@Override
	public void deleteReply(Long replyNo, Long memberNo) {

		GlobalValidator.validateNo(replyNo, "존재하지 않는 댓글입니다.");
		
		ReviewReply reviewReply = ReviewReply.builder().replyNo(replyNo).replyWriter(String.valueOf(memberNo)).build();
		
		int result = reviewReplyMapper.deleteReply(reviewReply);
		
		if(result == 0) {
			throw new ReplyDeleteException("댓글 삭제에 실패했습니다.");
		}
		
		
	}
	
	
	
	
}
