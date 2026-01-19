package com.kh.foodreport.domain.admin.review.model.dto;

import java.util.List;

import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagDTO;
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
public class AdminReviewResponse {
	
	private List<AdminReviewDTO> adminReviews;
	private PageInfo pages;
}
