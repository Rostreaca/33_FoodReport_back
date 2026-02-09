package com.kh.foodreport.domain.admin.member.model.dto;

import java.util.Date;
import java.util.List;

import com.kh.foodreport.global.util.PageInfo;

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
public class AdminMemberPlaceResponse {

	private List<AdminMemberPlaceDTO> restaurant;
	private PageInfo pageInfo;
}
