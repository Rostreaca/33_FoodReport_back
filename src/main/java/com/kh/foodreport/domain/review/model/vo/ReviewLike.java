package com.kh.foodreport.domain.review.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewLike {

	private Long reviewNo;
	private Long memberNo;
	
}
