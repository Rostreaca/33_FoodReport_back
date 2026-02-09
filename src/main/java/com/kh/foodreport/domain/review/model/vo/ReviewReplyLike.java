package com.kh.foodreport.domain.review.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewReplyLike {
	private Long replyNo;
	private Long memberNo;
	
	public static ReviewReplyLike createReviewReplyLike(Long replyNo, Long memberNo) {
		
		return ReviewReplyLike.builder()
						 .replyNo(replyNo)
						 .memberNo(memberNo)
						 .build();
	}
	
}
