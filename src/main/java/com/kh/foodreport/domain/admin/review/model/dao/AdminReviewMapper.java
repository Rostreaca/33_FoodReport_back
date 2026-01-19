package com.kh.foodreport.domain.admin.review.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewDTO;

@Mapper
public interface AdminReviewMapper {
	
	int countByReviews();

	AdminReviewDTO findAllReviews(Map<String, Object> pages);
}
