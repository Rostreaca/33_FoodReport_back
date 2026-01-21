package com.kh.foodreport.domain.admin.place.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminPlaceDTO {
	
	private Long placeNo;
	private String placeTitle;
	private String placeContent;
	private Date createDate;
	private int viewCount;
	private String status;
	private Date updateDate;
	private Date deleteDate;
	private String nickname;
	
}
