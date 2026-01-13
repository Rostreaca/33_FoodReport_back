package com.kh.foodreport.domain.review.model.dto;

import java.util.Date;

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
public class ReviewImageDTO {
	
	private Long ImageNo;
	private String originName;
	private String changeName;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refReviewNo;
	private int imageLevel;
	
}
