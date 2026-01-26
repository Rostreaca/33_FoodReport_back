package com.kh.foodreport.global.common.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

@Mapper
public interface GlobalMapper {

	List<ReviewDTO> findAllReviews(Map<String, Object> params);

	List<PlaceDTO> findAllPlaces(Map<String, Object> params);

	List<TagDTO> findAllTags();

}
