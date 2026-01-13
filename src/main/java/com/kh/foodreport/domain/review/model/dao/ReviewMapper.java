package com.kh.foodreport.domain.review.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;

@Mapper
public interface ReviewMapper {

	public int saveReview(ReviewDTO review);

	public int saveImage(ReviewImage image);

	public int countByReviews(Map<String, Object> params);

	public List<ReviewDTO> findAllReviews(Map<String, Object> params);

}
