package com.kh.foodreport.domain.place.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceReplyLike {
	private Long replyNo;
	private Long memberNo;
	
	public static PlaceReplyLike createPlaceReplyLike(Long replyNo, Long memberNo) {
		
		return PlaceReplyLike.builder()
						 .replyNo(replyNo)
						 .memberNo(memberNo)
						 .build();
	}
}
