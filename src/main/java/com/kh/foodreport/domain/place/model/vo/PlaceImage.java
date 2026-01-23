package com.kh.foodreport.domain.place.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceImage {
	
	private Long ImageNo;
	private String originName;
	private String changeName;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refPlaceNo;
	private int imageLevel;
	
}
