package com.kh.foodreport.global.common.model.dto;

import java.util.List;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;

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
public class BoardResponse {

	private List<ReviewDTO> reviews;
	private List<PlaceDTO> places;
}
