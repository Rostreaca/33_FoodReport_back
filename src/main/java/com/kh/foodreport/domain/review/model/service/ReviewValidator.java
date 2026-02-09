package com.kh.foodreport.domain.review.model.service;

import org.springframework.stereotype.Component;

import com.kh.foodreport.domain.review.model.dao.ReviewMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.global.exception.PageNotFoundException;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewValidator {

	private final ReviewMapper reviewMapper;
	
	public void validateReview(ReviewDTO review) {
		
		GlobalValidator.checkNull(review, "데이터가 존재하지 않습니다. 다시 시도해주세요.");
		GlobalValidator.checkBlank(review.getReviewTitle(),"게시글 제목은 비어있을 수 없습니다.");
		GlobalValidator.checkBlank(review.getReviewContent(),"게시글 내용은 비어있을 수 없습니다.");	
	}
	
	public void validateReply(ReviewReplyDTO reply) {

		GlobalValidator.checkNull(reply, "데이터가 존재하지 않습니다. 다시 시도해주세요.");
		
		GlobalValidator.checkBlank(reply.getReplyContent(), "댓글 내용은 비어있을 수 없습니다.");
		
	}
	
	public void validateReply(Long reviewNo, ReviewReplyDTO reply) {

		GlobalValidator.validateNo(reviewNo, "유효하지 않은 게시글 번호입니다.");
		
		int count = reviewMapper.countByReviewNo(reviewNo);
		
		if(count == 0) {
			throw new PageNotFoundException("게시글이 존재하지 않습니다.");
		}
		
		validateReply(reply);
	}
}
