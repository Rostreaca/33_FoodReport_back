package com.kh.foodreport.domain.admin.review.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewDTO;

@Mapper
public interface AdminReviewMapper {
	
	int countByReviews();

	List<AdminReviewDTO> findAllReviews(Map<String, Object> pages);
	
	int countByReviewTitle(String reviewTitle);
	
	List<AdminReviewDTO> findByReviewTitle(Map<String, Object> pages);
	
	int deleteReview(Long reviewNo);
}
