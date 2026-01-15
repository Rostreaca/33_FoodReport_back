package com.kh.foodreport.domain.review.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;

public interface ReviewService {

	public void saveReview(ReviewDTO review, List<MultipartFile> images);

	public ReviewResponse findAllReviews(int page, Map<String, Object> params);

	public ReviewDTO findByReviewNo(Long reviewNo);
	
}
