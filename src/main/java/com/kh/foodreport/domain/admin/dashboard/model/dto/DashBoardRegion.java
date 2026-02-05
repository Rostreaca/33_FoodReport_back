package com.kh.foodreport.domain.admin.dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DashBoardRegion {
	
    private Long regionNo;
    private String regionName;
    private Long reviewCount;
    private Long placeCount;
    private Long totalCount;
}
