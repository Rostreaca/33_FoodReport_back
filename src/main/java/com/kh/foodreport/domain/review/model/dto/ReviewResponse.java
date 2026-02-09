package com.kh.foodreport.domain.review.model.dto;

import java.util.List;

import com.kh.foodreport.global.util.PageInfo;

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
public class ReviewResponse{

	private List<ReviewDTO> reviews;
	private PageInfo pageInfo;
	
}
