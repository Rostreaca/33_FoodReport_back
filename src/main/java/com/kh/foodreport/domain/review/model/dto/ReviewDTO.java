package com.kh.foodreport.domain.review.model.dto;

import java.util.Date;
import java.util.List;

import com.kh.foodreport.global.tag.model.dto.TagDTO;

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
public class ReviewDTO {

	private Long reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private Long memberNo;
	private String reviewWriter;
	private String profileImage;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int likes;
	private int viewCount;
	private String status;
	
	private List<TagDTO> tags;
	private List<ReviewImageDTO> reviewImages;
	private List<ReviewReplyDTO> reviewReplies;
	
}
