package com.kh.foodreport.domain.member.model.dto;

import java.util.Date;

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
public class RestaurantDTO {
	
	private Long restaurantNo;
	private String restaurantName;
	private String address;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refMemberNo;

	
}
