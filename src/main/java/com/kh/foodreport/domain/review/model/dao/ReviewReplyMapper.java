package com.kh.foodreport.domain.review.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewReply;

@Mapper
public interface ReviewReplyMapper {

	int updateReply(ReviewReplyDTO reviewReply);

	int deleteReply(ReviewReply reviewReply);

}
