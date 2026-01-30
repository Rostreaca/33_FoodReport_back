package com.kh.foodreport.domain.notice.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Notice {
	private Long noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
}
