package com.kh.foodreport.domain.admin.member.model.dto;

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
public class AdminMemberPlaceDTO {
	
	private Long restaurantNo;
	private String businessNo;
	private String nickname;
	private String memberId;
	private String role;
	private String restaurantName;
	private String address;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String profileImage;
}
