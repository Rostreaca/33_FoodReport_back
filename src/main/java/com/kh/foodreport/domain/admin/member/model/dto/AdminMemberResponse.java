package com.kh.foodreport.domain.admin.member.model.dto;

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
public class AdminMemberResponse {
	
	private List<AdminMemberDTO> member;
	private PageInfo pageInfo;
}
