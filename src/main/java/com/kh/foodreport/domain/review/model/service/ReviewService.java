package com.kh.foodreport.domain.review.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;

public interface ReviewService {

	public void saveReview(ReviewDTO review, List<MultipartFile> images);
	
}
