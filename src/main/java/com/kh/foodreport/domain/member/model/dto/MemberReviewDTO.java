package com.kh.foodreport.domain.member.model.dto;

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
public class MemberReviewDTO {
	
	private Long reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private String createDate;
	private String status;
	private String viewCount;
	private String updateDate;
	private String deleteDate;
	private String nickname;
	private int likes; 

}
