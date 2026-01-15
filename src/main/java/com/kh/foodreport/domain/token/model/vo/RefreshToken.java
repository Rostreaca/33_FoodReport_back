package com.kh.foodreport.domain.token.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {
	private String token;
	private String memberNo;
	private Long expiration;

}
