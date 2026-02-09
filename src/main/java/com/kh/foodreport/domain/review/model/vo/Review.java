package com.kh.foodreport.domain.review.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Review {
	
	private Long reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private Long memberNo;
	private String reviewWriter;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int likes;
	private int viewCount;
	private String status;
	
}
