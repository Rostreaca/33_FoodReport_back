package com.kh.foodreport.domain.place.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Place {

	private Long placeNo;
	private String placeTitle;
	private String placeContent;
	private String placeWriter;
	private Long memberNo;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int likes;
	private int viewCount;
	private String status;
	
}
