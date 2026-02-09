package com.kh.foodreport.domain.review.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;

public interface ReviewService {

	public void saveReview(ReviewDTO review, List<Long> tagNums, List<MultipartFile> images, Long regionNo);

	public ReviewResponse findAllReviews(int page, Map<String, Object> params);

	public ReviewDTO findReviewByReviewNo(Long reviewNo);

	public void updateReview(ReviewDTO review, List<Long> tagNums, List<MultipartFile> images);

	public void deleteReview(Long reviewNo);

	public void saveReply(Long reviewNo, ReviewReplyDTO reply);

	public void saveLike(Long reviewNo, Long memberNo);

	public void deleteLike(Long reviewNo, Long memberNo);
	
	
}
