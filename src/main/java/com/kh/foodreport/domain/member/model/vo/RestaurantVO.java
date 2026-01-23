package com.kh.foodreport.domain.member.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
public class RestaurantVO {
	private Long restaurantNo;
	private String businessNo;
	private String restaurantName;
	private String address;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refMemberNo;
}
