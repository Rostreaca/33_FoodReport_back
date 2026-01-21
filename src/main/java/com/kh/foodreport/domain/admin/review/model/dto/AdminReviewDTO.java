package com.kh.foodreport.domain.admin.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminReviewDTO {

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
