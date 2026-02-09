package com.kh.foodreport.domain.review.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewReply;
import com.kh.foodreport.domain.review.model.vo.ReviewReplyLike;

@Mapper
public interface ReviewReplyMapper {

	int updateReply(ReviewReplyDTO reviewReply);

	int deleteReply(ReviewReply reviewReply);

	int countReplyLikeByMember(ReviewReplyLike reviewReplyLike);

	int saveReplyLike(ReviewReplyLike reviewReplyLike);

	int deleteReplyLike(ReviewReplyLike reviewReplyLike);

	int countByReplyNo(Long replyNo);

}
