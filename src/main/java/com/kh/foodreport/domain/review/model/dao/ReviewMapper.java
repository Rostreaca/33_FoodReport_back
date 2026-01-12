package com.kh.foodreport.domain.review.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;

@Mapper
public interface ReviewMapper {

	public int saveReview(ReviewDTO review);

	public int saveImage(ReviewImage image);
}
