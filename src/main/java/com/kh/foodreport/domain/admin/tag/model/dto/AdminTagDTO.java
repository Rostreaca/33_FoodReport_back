package com.kh.foodreport.domain.admin.tag.model.dto;

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
public class AdminTagDTO {
	private Long tagNo;
	private String tagTitle;
	private String tagContent;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	
}
