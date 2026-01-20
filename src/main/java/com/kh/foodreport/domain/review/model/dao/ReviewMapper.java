package com.kh.foodreport.domain.review.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewImageDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;
import com.kh.foodreport.domain.review.model.vo.ReviewLike;
import com.kh.foodreport.domain.review.model.vo.ReviewReply;
import com.kh.foodreport.global.tag.Tag;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

@Mapper
public interface ReviewMapper {

	public int saveReview(ReviewDTO review);

	public int saveImage(ReviewImage image);

	public int countByReviews(Map<String, Object> params);

	public List<ReviewDTO> findAllReviews(Map<String, Object> params);

	public ReviewDTO findReviewByReviewNo(Long reviewNo);

	public void updateViewCount(Long reviewNo);

	public int updateReview(ReviewDTO review);

	public int deleteImage(Long imageNo);

	public List<ReviewImageDTO> findImagesByReviewNo(Long reviewNo);

	public int deleteReview(Long reviewNo);

	public int deleteReplies(Long reviewNo);

	public int saveReply(ReviewReply replyVO);

	public int saveLike(ReviewLike reviewLike);

	public int countLikeByMember(ReviewLike reviewLike);

	public int deleteLike(ReviewLike reviewLike);

	public List<ReviewReplyDTO> findRepliesByReviewNo(Long reviewNo);

	public List<TagDTO> findTagByReviewNo(Long reviewNo);

	
}
