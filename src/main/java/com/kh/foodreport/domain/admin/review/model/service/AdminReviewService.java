package com.kh.foodreport.domain.admin.review.model.service;

import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewResponse;

public interface AdminReviewService {
	
	AdminReviewResponse findAllReviews(int page);
}
