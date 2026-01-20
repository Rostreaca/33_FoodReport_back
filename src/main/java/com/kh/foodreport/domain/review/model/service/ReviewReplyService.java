package com.kh.foodreport.domain.review.model.service;

import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;

public interface ReviewReplyService {

	void updateReply(Long replyNo, ReviewReplyDTO reviewReply, Long memberNo);

	void deleteReply(Long replyNo, Long memberNo);

}
