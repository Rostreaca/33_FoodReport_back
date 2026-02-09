package com.kh.foodreport.global.region;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Region {
	
	private Long regionNo;
	private String regionName;
	
}
