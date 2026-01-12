package com.kh.foodreport.domain.review.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewImage {

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
