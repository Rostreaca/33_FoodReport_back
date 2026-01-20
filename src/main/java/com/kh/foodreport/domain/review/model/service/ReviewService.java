package com.kh.foodreport.domain.review.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;

public interface ReviewService {

	public void saveReview(ReviewDTO review, List<MultipartFile> images);

	public ReviewResponse findAllReviews(int page, Map<String, Object> params);

	public ReviewDTO findByReviewNo(Long reviewNo);

	public void updateReview(Long reviewNo, ReviewDTO review, List<MultipartFile> images);

	public void deleteReview(Long reviewNo);

	public void saveReply(Long reviewNo, ReviewReplyDTO reply);

	public void saveLike(Long reviewNo, Long memberNo);
	
	
}
