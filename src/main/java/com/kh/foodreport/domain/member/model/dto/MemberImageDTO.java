package com.kh.foodreport.domain.member.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberImageDTO {
	
	private Long imageNo;
	private String originName;
	private String changeName;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refMemberNo;

}
