package com.kh.foodreport.domain.member.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MemberImage {

	private Long ImageNo;
	private String originName;
	private String changeName;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refMemberNo;
}
