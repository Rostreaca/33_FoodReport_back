package com.kh.foodreport.domain.admin.review.model.service;

import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewResponse;

public interface AdminReviewService {
	
	AdminReviewResponse findAllReviews(int page);
	
	AdminReviewResponse findByReviewTitle(int page, String reviewTitle);
	
	void deleteReview(Long reviewNo);
	
	void updateReview(Long reviewNo);
}
