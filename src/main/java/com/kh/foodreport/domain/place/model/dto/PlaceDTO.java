package com.kh.foodreport.domain.place.model.dto;

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
public class PlaceDTO {
	private Long placeNo;
	private String placeTitle;
	private String placeContent;
	private String placeWriter;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int likes;
	private int viewCount;
	private String status;
	
	
}
