package com.kh.foodreport.domain.admin.notice.model.dto;

import java.util.Date;

import com.kh.foodreport.domain.admin.notice.model.vo.AdminNoticeImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminNoticeDTO {
	
	private Long noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private int viewCount;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refMemberNo;
	private String noticeImageUrl;
	
}
