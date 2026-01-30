package com.kh.foodreport.global.common.model.service;

import java.util.List;

import com.kh.foodreport.domain.place.model.dto.PlaceResponse;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

public interface GlobalService {

	ReviewResponse findAllReviewsByKeyword(String keyword, int page, Long tagNo);

	PlaceResponse findAllPlacesByKeyword(String keyword, int page, Long tagNo);

	List<TagDTO> findAllTags();

}
