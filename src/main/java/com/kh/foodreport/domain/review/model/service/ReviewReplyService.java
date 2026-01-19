package com.kh.foodreport.domain.review.model.service;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;

public interface ReviewReplyService {

	void updateReply(Long replyNo, ReviewReplyDTO reviewReply, CustomUserDetails user);

	void deleteReply(Long replyNo, CustomUserDetails user);

}
