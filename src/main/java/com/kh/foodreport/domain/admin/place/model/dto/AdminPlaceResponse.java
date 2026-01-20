package com.kh.foodreport.domain.admin.place.model.dto;

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
public class AdminPlaceResponse {
	private List<AdminPlaceDTO> adminPlace;
	private PageInfo pageInfo;
}
