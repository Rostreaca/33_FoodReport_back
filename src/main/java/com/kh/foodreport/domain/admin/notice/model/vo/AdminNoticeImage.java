package com.kh.foodreport.domain.admin.notice.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AdminNoticeImage {

	private Long imageNo;
	private String originName;
	private String changeName;
	private String status; 
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refNoticeNo;
	
}
