package com.kh.foodreport.domain.review.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewReplyLike {
	private Long reviewNo;
	private Long memberNo;
	
	public static ReviewReplyLike createReviewLike(Long reviewNo, Long memberNo) {
		
		return ReviewReplyLike.builder()
						 .reviewNo(reviewNo)
						 .memberNo(memberNo)
						 .build();
	}
	
}
