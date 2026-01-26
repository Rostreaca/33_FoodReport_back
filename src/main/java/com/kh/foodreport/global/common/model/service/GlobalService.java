package com.kh.foodreport.global.common.model.service;

import java.util.List;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.global.common.model.dto.BoardResponse;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

public interface GlobalService {

	BoardResponse findAllByKeyword(String keyword);

	List<ReviewDTO> findAllReviewsByKeyword(String keyword, int offset);

	List<PlaceDTO> findAllPlacesByKeyword(String keyword, int offset);

	List<TagDTO> findAllTags();

}
