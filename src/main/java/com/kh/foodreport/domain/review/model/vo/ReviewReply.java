package com.kh.foodreport.domain.review.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewReply {
	
	private Long replyNo;
	private String replyContent;
	private String replyWriter;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private Long refReviewNo;
	
}
