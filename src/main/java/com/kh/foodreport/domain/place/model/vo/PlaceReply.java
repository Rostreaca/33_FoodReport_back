package com.kh.foodreport.domain.place.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceReply {

	private Long replyNo;
	private String replyContent;
	private String replyWriter;
	private Long memberNo;
	private int likes;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private Long refPlaceNo;
}
