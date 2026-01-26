package com.kh.foodreport.domain.place.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceLike {
	private Long placeNo;
	private Long memberNo;
	
	public static PlaceLike createPlaceLike(Long placeNo, Long memberNo) {
		
		return PlaceLike.builder()
						.placeNo(placeNo)
						.memberNo(memberNo)
						.build();
	}
}
