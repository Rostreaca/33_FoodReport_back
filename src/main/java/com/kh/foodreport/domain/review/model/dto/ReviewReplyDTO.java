package com.kh.foodreport.domain.review.model.dto;

import java.util.Date;
import java.util.List;

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
public class ReviewReplyDTO {

	private Long replyNo;
	private String replyContent;
	private String replyWriter;
	private Long memberNo;
	private String profileImage;
	private int likes;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private Long refReviewNo;
	
	private List<Long> likeMembers;
}
